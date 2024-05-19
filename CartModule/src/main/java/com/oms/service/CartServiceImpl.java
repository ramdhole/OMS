package com.oms.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.oms.dto.CartDto;
import com.oms.dto.MobileDto;
import com.oms.entity.Cart;
import com.oms.exception.CartNotFoundException;
import com.oms.exception.CartNotSavedException;
import com.oms.exception.MobileNotFoundException;
import com.oms.repository.ICartRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CartServiceImpl implements ICartService {
	
	@Autowired
	private ICartRepository cartRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
    public void setCartRepository(ICartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

	private final String mobUrl = "http://localhost:9990/mobile";
	
	@Override
	public CartDto updateCart(Cart cart) {
		
		Optional<Cart> optCarts = cartRepository.findByCustId(cart.getCartId());
		
		if (!optCarts.isPresent()) 
			throw new CartNotFoundException("Cart not found!!");
		
		cart.setCartId(optCarts.get().getCartId());
		Cart fCart = cartRepository.save(cart);
		
		if(fCart == null)
			throw new CartNotSavedException("Failed to update cart");
		
		log.info("Cart Updated Successfully !!");
		return modelMapper.map(fCart, CartDto.class);
	}

	@Override
	public void deleteCartById(int cartId){
		Optional<Cart> optCarts = this.cartRepository.findById(cartId);
		if (!optCarts.isPresent())
			throw new CartNotFoundException("Card id is not exists to delete !!");
		
		Cart cart = optCarts.get();
		
		cart.setMobileId(null);
		cart.setQuantity(0);
		cart.setTotalCost(0.0f);
		Cart cartsave = cartRepository.save(cart);
		
		if(cartsave==null)
			throw new CartNotSavedException("Failed to delete Cart !!");

		log.info("Cart Deleted Successfully !!");
	}

	@Override
	public CartDto getCartByCustId(Integer id){
		Optional<Cart> cart = cartRepository.findByCustId(id);
		
		if (!cart.isPresent()) {
			throw new CartNotFoundException("CustomerId not found" );
		}
		
		log.info("Cart Found Successfully with Customer Id !!");
		return modelMapper.map(cart, CartDto.class);
	}

	@Override
	public CartDto removeMobilefromCartById(Integer mobileId, Integer cartId){
		
		Optional<Cart> oCart = cartRepository.findById(cartId);
		
		if(!oCart.isPresent())
			throw new CartNotFoundException("Cart not found!!");
		
		Map<Integer, Integer> mobInCart = oCart.get().getMobileId();
		
		if(!mobInCart.containsKey(mobileId))
			throw new MobileNotFoundException("Mobile not Found!!");
		
		mobInCart.remove(mobileId);
		
		Cart cart = oCart.get();
		cart.setMobileId(mobInCart);
		cart.setTotalCost(calCost(mobInCart));
		cart.setQuantity(getQuantity(mobInCart));
		
		Cart fCart = cartRepository.save(cart);
		
		if(fCart == null)
			throw new CartNotSavedException("Failed to remove mobile");
		
		log.info("Mobile Removed Successfully !!");
		return modelMapper.map(fCart, CartDto.class);
	}

	@Override
	public List<CartDto> getAllCarts() {
		
		List<Cart> cartList = cartRepository.findAll();
		
		if(cartList.isEmpty())
			throw new CartNotFoundException("Carts not found!!");
		
		log.info("All Carts Fetched Successfully !!");
		return Arrays.asList(modelMapper.map(cartList, CartDto[].class));
	}

	@Override
	public CartDto addMobileToCartBycustomerId(Integer mobileId, Integer custId){

		 Optional<Cart> ocart= cartRepository.findByCustId(custId);
		
		 if(!ocart.isPresent())
			 throw new CartNotFoundException("Cart not found!!");
		 
		 Cart cart = ocart.get();
		 
//		 Adding the mobile in cart 
		 Map<Integer, Integer> mobilesInCart = cart.getMobileId();
		 
		 mobilesInCart.put(mobileId, 1);
		 cart.setMobileId(mobilesInCart);
		 cart.setQuantity(getQuantity(mobilesInCart));
		 
//		 Setting the cost 
		 cart.setTotalCost(calCost(mobilesInCart));
		 
		 Cart fCart = cartRepository.save(cart);
		 if(fCart == null)
			 throw new CartNotSavedException("Failed to add mobile in Cart!!");
		 
		 log.info("Mobile Added Successfully to Cart!!");
		 return modelMapper.map(fCart, CartDto.class);
	
}

	@Override
	public CartDto create(int custId) {
		Cart cart = new Cart();
		cart.setCustId(custId);
		
		Cart scart = cartRepository.save(cart);
		if(scart == null)
			throw new CartNotSavedException("Failed to create cart!!");
		
		log.info("Cart Created Successfully !!");
		return modelMapper.map(scart, CartDto.class);
	}
	

	@Override
	public List<MobileDto> getAllMobile(int custId) {
		
		Optional<Cart> cart = cartRepository.findByCustId(custId);
		
		if(!cart.isPresent())
			throw new CartNotFoundException("Cart not found!!");
		
		List<MobileDto> mobileList = new ArrayList<>();
		for(Map.Entry<Integer, Integer> entry : cart.get().getMobileId().entrySet()) {
			
			MobileDto mobile = restTemplate.getForObject(mobUrl+"/get/"+entry.getKey(), MobileDto.class);
			mobileList.add(mobile);
		}
		
		if(mobileList.isEmpty())
			throw new MobileNotFoundException("Mobiles not found !!");
		
		log.info("All Mobiles Fetched in Cart !!");
		return mobileList;
	}

	private Float calCost(Map<Integer, Integer> mobilesInCart) {
		
		float cost = 0f;
		
		for(Map.Entry<Integer, Integer> entry : mobilesInCart.entrySet()) {
			MobileDto mobile = restTemplate.getForObject(mobUrl+"/get/"+entry.getKey(), MobileDto.class);
			cost += mobile.getMobileCost()*entry.getValue();
		}
		
		return cost;
	}
	
	private int getQuantity(Map<Integer, Integer> mobilesInCart) {
		
		int quantity = 0;
		
		for(Map.Entry<Integer, Integer> entry : mobilesInCart.entrySet()) {
			quantity += mobilesInCart.get(entry.getKey());
		}
		
		return quantity;
	}

	@Override
	public CartDto getCartByCartId(Integer cartId) {
		
		Optional<Cart> cart = cartRepository.findById(cartId);
		if(cart.isEmpty())
			throw new CartNotFoundException("Cart not found!!");
		
		log.info("Cart Found Successfully !!");
		return modelMapper.map(cart.get(), CartDto.class);
	}
}
