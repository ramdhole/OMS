package com.oms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oms.dto.CartDto;
import com.oms.dto.MobileDto;
import com.oms.entity.Cart;
import com.oms.service.ICartService;



@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private ICartService cartService;
	
	@GetMapping("/create/{custId}")
	public ResponseEntity<CartDto> addCart(@PathVariable("custId") int custId) {
		
		return new ResponseEntity<>(cartService.create(custId), HttpStatus.OK);
	}

	@GetMapping("/addMobile/{customerId}/{mobileId}")
	public ResponseEntity<CartDto> addMobileToCartBycustomerId
		(@PathVariable("mobileId") Integer mobileId, @PathVariable("customerId") Integer customerId){
		
		return new ResponseEntity<>(cartService.addMobileToCartBycustomerId(mobileId, customerId), HttpStatus.OK);
	}
	
	@GetMapping("/getAllMobiles/{custId}")
	public ResponseEntity<List<MobileDto>> getAllMobile(@PathVariable("custId") int custId) {
		
		return new ResponseEntity<>(cartService.getAllMobile(custId), HttpStatus.OK);
	}
	
	@GetMapping("/getByCustId/{custId}")
	public ResponseEntity<CartDto> getCustById(@PathVariable("custId") Integer custId){
		
		return new ResponseEntity<>(cartService.getCartByCustId(custId), HttpStatus.OK);
	}

	@GetMapping("/getByCartId/{cartId}")
	public ResponseEntity<CartDto> getCartById(@PathVariable("cartId") Integer cartId){
		
		return new ResponseEntity<>(cartService.getCartByCartId(cartId), HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<CartDto>> getAllCarts() {

		return new ResponseEntity<>(cartService.getAllCarts(), HttpStatus.OK);
	}

	@DeleteMapping("/removeMobile/{mobileId}/{cartId}")
	public ResponseEntity<CartDto> removeMobilefromCartById(@PathVariable("mobileId") Integer mobileId, @PathVariable("cartId") Integer cartId){
		
		return new ResponseEntity<>(cartService.removeMobilefromCartById(mobileId, cartId), HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<CartDto> CartServiceUpdate(@RequestBody Cart cart) {
		
		return new ResponseEntity<>(cartService.updateCart(cart), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteCart/{cartId}")
	public ResponseEntity<HttpStatus> deleteCartById(@PathVariable("cartId") Integer cartId){
		
		cartService.deleteCartById(cartId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	public void setCartService(ICartService cartService) {
        this.cartService = cartService;
    }
	
}
