package com.orders.service;
 
import java.util.List;

import com.orders.dto.MobilesDto;
import com.orders.dto.OrdersDto;
 
 
public interface IOrderService {
 
	public OrdersDto addOrder(OrdersDto ordersDto);
 
	public OrdersDto updateOrder(OrdersDto ordersDto) ;
 
	public OrdersDto cancelOrder(int orderId);
 
	public OrdersDto getOrderById(int orderId);
 
	public List<OrdersDto> getAllOrders();
 
	public OrdersDto placedOrderFromCart(int cartId);
 
	public List<OrdersDto> getOrderByCustomerId(Integer customerId) ;

	public OrdersDto addOrderByCustMob(Integer customerId, Integer mobId);

	public List<MobilesDto> getAllMobiles(int carId);
 
}