package com.cg.mobileStore.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mobileStore.Dto.CartDTO;
import com.cg.mobileStore.Dto.CustomerDTO;
import com.cg.mobileStore.Dto.MobileDTO;
import com.cg.mobileStore.Dto.OrderDTO;
import com.cg.mobileStore.Entity.Customer;
import com.cg.mobileStore.Service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/register")
	public ResponseEntity<CustomerDTO> addCustomer(@RequestBody Customer customer) {
		return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.OK);
	}
	
	@GetMapping("/getByEmail/{email}")
	public ResponseEntity<CustomerDTO> getCustomerByEmailId(@PathVariable String email){
		
		return new ResponseEntity<>(customerService.getCustByEmail(email), HttpStatus.OK);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable int id){
		
		return new ResponseEntity<>(customerService.getCustById(id), HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<CustomerDTO>> getAll(){
		
		return new ResponseEntity<>(customerService.getAll(), HttpStatus.OK);
	}

	@PutMapping("/updateAddress/{emailId}")
	public ResponseEntity<CustomerDTO> updateCustomerAddress(@PathVariable String emailId, @RequestBody String address){

		return new ResponseEntity<>(customerService.updateAddress(emailId, address), HttpStatus.OK);
	}

	@GetMapping("getCart/{id}")
	public ResponseEntity<CartDTO> getCartByCustomerId(@PathVariable int id){

		return new ResponseEntity<>(customerService.getCartByCustomerId(id), HttpStatus.OK);
	}

	@GetMapping("/mobiles/{mobileName}")
	public ResponseEntity<List<MobileDTO>> findMobileByMobileName(@PathVariable String mobileName){

		return new ResponseEntity<>(customerService.findMobileByName(mobileName), HttpStatus.OK);
	}

	@PostMapping("/addMobile/{customerId}")
	public ResponseEntity<CartDTO> addMobileToCartBycustomerId(@PathVariable Integer customerId, @RequestParam Integer mobileId){
		
		return new ResponseEntity<>(customerService.addMobileToCart(customerId, mobileId), HttpStatus.OK);
	}

	@GetMapping("/getOrder/{custId}")
	public ResponseEntity<OrderDTO> getOrderByCustomerId(@PathVariable Integer custId) {
		
		return new ResponseEntity<>(customerService.getOrderByCustId(custId), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{custId}")
	public ResponseEntity<String> deleteById(@PathVariable int custId){
			customerService.deleteCustomer(custId);
		return new ResponseEntity<>("", HttpStatus.OK); 
	}
	
	@DeleteMapping("/mobile/{mobileId}/{cartId}")
	public ResponseEntity<CartDTO> removeMobilefromCartByIds(@PathVariable("mobileId") Integer mobileId,
			@PathVariable("cartId") Integer cartId) {

		return new ResponseEntity<>(customerService.removeMobilefromCartByIds(mobileId, cartId), HttpStatus.OK);
	}
	
	@GetMapping("/getAllOrder/{custId}")
	public ResponseEntity<List<OrderDTO>> getAllOrder(@PathVariable("custId") Integer custId){
		
		return new ResponseEntity<>(customerService.getAllOrder(custId), HttpStatus.OK);
	}
	

}
