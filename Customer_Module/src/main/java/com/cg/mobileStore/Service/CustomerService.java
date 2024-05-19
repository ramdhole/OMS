package com.cg.mobileStore.Service;

import java.util.List;

import com.cg.mobileStore.Dto.CartDTO;
import com.cg.mobileStore.Dto.CustomerDTO;
import com.cg.mobileStore.Dto.MobileDTO;
import com.cg.mobileStore.Dto.OrderDTO;
import com.cg.mobileStore.Entity.Customer;

public interface CustomerService {

	public CustomerDTO addCustomer(Customer customer);

	public CustomerDTO updateAddress(String emailId, String address);

	public List<MobileDTO> findMobileByName(String mobileName);

	public CartDTO addMobileToCart(Integer customerId, Integer mobileId);

	public OrderDTO getOrderByCustId(Integer custId);

	public CustomerDTO getCustByEmail(String email);

	public List<CustomerDTO> getAll();
	
	public void deleteCustomer(int custId);

	public CartDTO removeMobilefromCartByIds(Integer mobileId, Integer cartId);

	public CartDTO getCartByCustomerId(int id);

	public CustomerDTO getCustById(int id);

	public List<OrderDTO> getAllOrder(Integer custId);

}
