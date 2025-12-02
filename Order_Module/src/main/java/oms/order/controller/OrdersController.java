package com.orders.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.orders.dto.MobilesDto;
import com.orders.dto.OrdersDto;
import com.orders.service.IOrderService;

@RestController
@RequestMapping("/order")
public class OrdersController {
	@Autowired
	private IOrderService iOrderService;

	@PostMapping("/add")
	public ResponseEntity<OrdersDto> addOrder(@RequestBody OrdersDto OrdersDto) {
		return new ResponseEntity<>(iOrderService.addOrder(OrdersDto), HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<OrdersDto> updateOrder(@RequestBody OrdersDto ordersDto) {
		return new ResponseEntity<>(iOrderService.updateOrder(ordersDto), HttpStatus.OK);
	}

	@GetMapping("/get/{orderId}")
	public ResponseEntity<OrdersDto> getOrderById(@PathVariable("orderId") Integer orderId) {
		return new ResponseEntity<>(iOrderService.getOrderById(orderId), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{orderId}")
	public ResponseEntity<OrdersDto> cancelOrder(@PathVariable("orderId") Integer orderId) {
		return new ResponseEntity<>(this.iOrderService.cancelOrder(orderId), HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<OrdersDto>> getAllOrders() {
		return new ResponseEntity<>(iOrderService.getAllOrders(), HttpStatus.OK);
	}

	@GetMapping("placedOrderFromCart/{cartId}")
	public ResponseEntity<OrdersDto> placedOrderFromCart(@PathVariable("cartId") Integer cartId) {
		return new ResponseEntity<>(iOrderService.placedOrderFromCart(cartId), HttpStatus.OK);
	}

	@GetMapping("/getByCustomer/{customerId}")
	public ResponseEntity<List<OrdersDto>> getOrderByCustomerId(@PathVariable Integer customerId) {
		return new ResponseEntity<>(iOrderService.getOrderByCustomerId(customerId), HttpStatus.OK);
	}
	
	@GetMapping("/addOrder/{customerId}/{mobId}")
	public ResponseEntity<OrdersDto> addOrder(@PathVariable Integer customerId, @PathVariable Integer mobId) {
		return new ResponseEntity<>(iOrderService.addOrderByCustMob(customerId, mobId), HttpStatus.OK);
	}
	
	@GetMapping("/getAllMobile/order/custId/{custId}")
	public ResponseEntity<List<MobilesDto>> getAllMobile(@PathVariable Integer custId){
		return new ResponseEntity<>(iOrderService.getAllMobiles(custId), HttpStatus.OK);
	}
	
}

