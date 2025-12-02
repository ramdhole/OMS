package com.oms.service;

import java.util.List;

import com.oms.dto.CartDto;
import com.oms.dto.MobileDto;
import com.oms.entity.Cart;



public interface ICartService {

	public CartDto updateCart(Cart cart);

	void deleteCartById(int Id);

	public List<CartDto> getAllCarts();

	public CartDto removeMobilefromCartById(Integer mobileId, Integer cartId);

	public CartDto addMobileToCartBycustomerId(Integer mobileId, Integer custId);

	public CartDto getCartByCustId(Integer id);

	public CartDto create(int custId);

	public List<MobileDto> getAllMobile(int custId);

	public CartDto getCartByCartId(Integer cartId);


	
}
