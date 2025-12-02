package com.cg.mobileStore.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cg.mobileStore.Dto.CartDTO;
import com.cg.mobileStore.Dto.CustomerDTO;
import com.cg.mobileStore.Dto.MobileDTO;
import com.cg.mobileStore.Dto.OrderDTO;
import com.cg.mobileStore.Entity.Customer;
import com.cg.mobileStore.Exception.CartNotCreatedException;
import com.cg.mobileStore.Exception.CartNotFoundException;
import com.cg.mobileStore.Exception.CustomerNotFoundException;
import com.cg.mobileStore.Exception.CustomerNotSavedException;
import com.cg.mobileStore.Exception.MobileNotFoundException;
import com.cg.mobileStore.Repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	private final String cartUrl = "http://localhost:9993/cart";
	private final String orderUrl = "http://localhost:9994/order";
	private final String mobUrl = "http://localhost:9990/mobile";

	
	@Override
	@Transactional
	public CustomerDTO addCustomer(Customer cust) {
		
		if(customerRepository.findByEmailId(cust.getEmailId()).isPresent())
			throw new CustomerNotSavedException("Email ID already registered try to login");
		
		if(customerRepository.findByMobileNumber(cust.getMobileNumber()).isPresent())
			throw new CustomerNotSavedException("Mobile no. already registered try to login");
		
		cust.setPassword(encoder.encode(cust.getPassword()));
		
		Customer savedcust = customerRepository.save(cust);
		
		if(savedcust == null)
			throw new CustomerNotSavedException("Failed to register please try again!");
		
//		Creating cart for customer
		CartDTO cart = restTemplate.getForObject(cartUrl+"/create/"+savedcust.getCustomerId(), CartDTO.class);
		if(cart == null)
			throw new CartNotCreatedException("Unable to register and create cart please try again");
		
//		Setting the cart
		savedcust.setCartId(cart.getCartId());
		
		Customer fCust = customerRepository.save(savedcust);
		
//		checking if data saved successfully
		if(fCust == null)
			throw new CustomerNotSavedException("Failed to register please try again!");
		
		
		return modelMapper.map(savedcust, CustomerDTO.class);
	}

	@Override
	public CustomerDTO updateAddress(String emailId, String address){
		Optional<Customer> optionalCustomer = customerRepository.findByEmailId(emailId);
        if (!optionalCustomer.isPresent()) 
        	throw new CustomerNotFoundException("Customer with emailID " + emailId + " not found");
        
        Customer customer = optionalCustomer.get();
        customer.setAddress(address);
        
        Customer cust = customerRepository.save(customer);
        if(cust == null)
        	throw new CustomerNotSavedException("Failed to update your address please try again!");
        
        return modelMapper.map(cust, CustomerDTO.class);
	}

	@Override
	public CartDTO getCartByCustomerId(int id) {
		
		
		CartDTO cart = restTemplate.getForObject(cartUrl+"/getById/"+id, CartDTO.class);
		if(cart == null)
			throw new CartNotFoundException("Cart not found for the given cutomer!");
		
		return cart;
	}

	@Override
	public List<MobileDTO> findMobileByName(String mobileName) {
		@SuppressWarnings("unchecked")
		List<MobileDTO> mobileList = restTemplate.getForObject(mobUrl+"/getAll", List.class);
		if(mobileList == null)
			throw new MobileNotFoundException("No mobile as "+mobileName);
		
		return mobileList;
	}

	@Override
	public CartDTO addMobileToCart(Integer customerId, Integer mobileId) {
		
		CartDTO cart = restTemplate.getForObject(cartUrl+"/"+customerId+"/"+mobileId, CartDTO.class);
		if(cart == null)
			throw new CartNotFoundException("Mobile not added to cart try again!");
		
		return cart;
	}

	@Override
	public OrderDTO getOrderByCustId(Integer custId) {
		Optional<Customer> cust = customerRepository.findById(custId);
		if(!cust.isPresent())
			throw new CustomerNotFoundException("Customer not found !!");
		
		return restTemplate.getForObject(orderUrl+"/getByCustomer/"+custId, OrderDTO.class);
	}

	@Override
	public CustomerDTO getCustByEmail(String email) {
		Optional<Customer> cust = customerRepository.findByEmailId(email);
		if(!cust.isPresent())
			throw new CustomerNotFoundException("Customer not found with "+email);
		
		return modelMapper.map(cust, CustomerDTO.class);
	}

	@Override
	public List<CustomerDTO> getAll() {
		List<Customer> custList = customerRepository.findAll();
		if(custList.isEmpty())
			throw new CustomerNotFoundException("Customer not found");
		
		return Arrays.asList(modelMapper.map(custList, CustomerDTO[].class));
	}

	@Override
	public void deleteCustomer(int custId) {

		if(!customerRepository.findById(custId).isPresent())
			throw new CustomerNotFoundException("Customer not found!!");
		
		customerRepository.deleteById(custId);
			
	}

	@Override
	public CartDTO removeMobilefromCartByIds(Integer mobileId, Integer cartId) {
		CartDTO cart = restTemplate.getForObject(cartUrl+"/"+mobileId+"/"+cartId, CartDTO.class);
		if(cart == null)
			throw new CartNotFoundException("No cart found with id "+cartId);
		
		return cart;
	}

	@Override
	public CustomerDTO getCustById(int id) {
		Optional<Customer> cust = customerRepository.findById(id);
		
		if(!cust.isPresent())
			throw new CustomerNotFoundException("Customer not found!!");
		
		return modelMapper.map(cust, CustomerDTO.class);
	}

	@Override
	public List<OrderDTO> getAllOrder(Integer custId) {
		Optional<Customer> cust = customerRepository.findById(custId);
		if(!cust.isPresent())
			throw new CustomerNotFoundException("Customer not found !!");
		
		return restTemplate.getForObject(orderUrl+"/getByCustomer/"+custId, List.class);
	}

}
