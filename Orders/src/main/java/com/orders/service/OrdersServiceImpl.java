package com.orders.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.HashSet;

import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import com.orders.dto.CartDto;

import com.orders.dto.CustomerDto;

import com.orders.dto.MobilesDto;

import com.orders.dto.OrdersDto;

import com.orders.entity.Orders;

import com.orders.exception.CartException;

import com.orders.exception.MobilesException;

import com.orders.exception.OrdersException;

import com.orders.exception.UsersException;

import com.orders.repository.IOrderRepository;

@Service

public class OrdersServiceImpl implements IOrderService {

	@Autowired

	private IOrderRepository iorderRepository;

	@Autowired

	RestTemplate restTemplate;

	@Autowired

	private ModelMapper modelMapper;

	private final String mobUrl = "http://localhost:9990/mobile";

	private final String cusUrl = "http://localhost:9991/customer";

	private final String cartUrl = "http://localhost:9993/cart";

//for add a order

	@Override

	public OrdersDto addOrder(OrdersDto ordersDto) {

		Orders newOrder = new Orders();

		newOrder.setOrderDate(LocalDate.now());

		newOrder.setDispachDate(LocalDate.now());

		// Retrieve customer information using customerId

		CustomerDto customerDto = restTemplate.getForObject(cusUrl + "/getById/" + ordersDto.getCustId(),
				CustomerDto.class);

		if (customerDto == null) {

			throw new UsersException("Customer not found for customerEmail: " + ordersDto.getCustId());

		}

		newOrder.setCustId(ordersDto.getCustId());

		iorderRepository.save(newOrder);

		List<Integer> mobilesIds = ordersDto.getMobileId();

		Set<MobilesDto> mobileList = new HashSet<>();

		float totalCost = 0.0f;

		int qty = 0;

		for (Integer mobileId : mobilesIds) {

			MobilesDto optMobile = restTemplate.getForObject(mobUrl + "/get/" + mobileId, MobilesDto.class);

			if (optMobile == null) {

				throw new MobilesException("Mobile with id " + mobileId + " does not exist.");

			}

			totalCost += optMobile.getMobileCost();

			qty++;

			mobileList.add(optMobile);

		}

		newOrder.setMobileId(mobilesIds);

		newOrder.setCost(ordersDto.getCost());

		newOrder.setTotalCost(totalCost);

		newOrder.setQuantity(qty);

		newOrder.setOrderStatus("ORDER PLACED");

		iorderRepository.save(newOrder);

		OrdersDto odered = modelMapper.map(newOrder, OrdersDto.class);

		return odered;

	}

	// for update order

	@Override

	public OrdersDto updateOrder(OrdersDto ordersDto) {

		Optional<Orders> optOrders = this.iorderRepository.findById(ordersDto.getOrderId());

		if (!optOrders.isPresent()) {

			throw new OrdersException("Orders id " + ordersDto.getOrderId() + " does not exists !");

		}

		Orders orders = optOrders.get();

		List<Integer> mobilesIds = ordersDto.getMobileId();

		Set<MobilesDto> mobilelist = new HashSet<MobilesDto>();

		float totalCost = 0.0f;

		int qty = 0;

		for (Integer mobileId : mobilesIds) {

			MobilesDto optMobile = restTemplate.getForObject(mobUrl + "/get/" + mobileId, MobilesDto.class);

			if (optMobile == null) {

				throw new MobilesException("Mobile id " + mobileId + " does not exists !");

			}

			MobilesDto mobile = optMobile;

			totalCost = totalCost + mobile.getMobileCost();

			qty = qty + 1;

			mobilelist.add(mobile);

		}

		orders.setMobileId(mobilesIds);

		orders.setCost(totalCost);

		orders.setTotalCost(totalCost);

		orders.setQuantity(qty);

		iorderRepository.save(orders);

		OrdersDto odered = modelMapper.map(orders, OrdersDto.class);

		return odered;

	}

	// for cancel order

	@Override

	public OrdersDto cancelOrder(int orderId) {

		Optional<Orders> optOrder = iorderRepository.findById(orderId);

		if (!optOrder.isPresent())

			throw new OrdersException("Order id does not exists to delete !");

//		int save = iorderRepository.updateOrder(orderId, "ORDER CANCELED");

		Orders order1 = optOrder.get();

		iorderRepository.delete(optOrder.get());

		OrdersDto odered = modelMapper.map(order1, OrdersDto.class);

		return odered;

	}

	// get a order by id

	@Override

	public OrdersDto getOrderById(int orderId) {

		Optional<Orders> optOrders = this.iorderRepository.findById(orderId);

		if (!optOrders.isPresent()) {

			throw new OrdersException("Orders id " + orderId + " does not exists !");

		}

		Orders orders = optOrders.get();

		OrdersDto odered = modelMapper.map(orders, OrdersDto.class);

		return odered;

	}

	// get all order

	@Override

	public List<OrdersDto> getAllOrders() {

		List<Orders> order = iorderRepository.findAll();

		return Arrays.asList(modelMapper.map(order, OrdersDto[].class));

	}

	// placedOrderFromCart

	@Override

	public OrdersDto placedOrderFromCart(int cartId) {

		Orders newOrder = new Orders();

		newOrder.setOrderDate(LocalDate.now());

		newOrder.setDispachDate(LocalDate.now());

		CartDto cartDTO = restTemplate.getForObject(cartUrl + "/getByCartId" + "/" + cartId, CartDto.class);

		if (cartDTO == null)

			throw new CartException("cart id does not exists to delete ");

		List<Integer> mobsInCart = new ArrayList<>();

		for (Entry<Integer, Integer> entry : cartDTO.getMobileId().entrySet()) {
			int quantity = cartDTO.getMobileId().get(entry.getKey());
			for (int i = 0; i < quantity; i++) {
				mobsInCart.add(entry.getKey());
			}
		}

		Orders order = new Orders();
		order.setMobileId(mobsInCart);
		order.setCustId(cartDTO.getCustId());

		return this.addOrder(modelMapper.map(order, OrdersDto.class));

	}

	@Override

	public List<OrdersDto> getOrderByCustomerId(Integer customerId) {

		CustomerDto customerDto = restTemplate.getForObject(cusUrl + "/getById/" + customerId, CustomerDto.class);

		if (customerDto == null) {
			throw new UsersException("CustomerId not found:" + customerId);
		}

		List<Orders> userOrders = iorderRepository.findOrderByCustId(customerId);

		return Arrays.asList(modelMapper.map(userOrders, OrdersDto[].class));

	}

	@Override
	public OrdersDto addOrderByCustMob(Integer customerId, Integer mobId) {
		List<Integer> mobIdList = new ArrayList<>();

		mobIdList.add(mobId);
		Orders order = new Orders();
		order.setCustId(customerId);
		order.setMobileId(mobIdList);

		return this.addOrder(modelMapper.map(order, OrdersDto.class));
	}

	@Override
	public List<MobilesDto> getAllMobiles(int custId) {
		List<Orders> orders = iorderRepository.findOrderByCustId(custId);

		
		List<Integer> mobIds = orders.stream().flatMap(order -> order.getMobileId().stream())
				.collect(Collectors.toList());
		
		if (mobIds.isEmpty())
			throw new MobilesException("Mobiles Not Found InOrder !!");

		List<MobilesDto> mobiles = new ArrayList<>();

		for (Integer id : mobIds) {
			MobilesDto mobile = restTemplate.getForObject(mobUrl + "/get/" + id, MobilesDto.class);
			mobiles.add(mobile);
		}

		return mobiles;
	}

}