package com.orders;
 
import com.orders.controller.OrdersController;
import com.orders.dto.OrdersDto;
import com.orders.service.IOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 
import java.util.ArrayList;
import java.util.List;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
 
class OrdersControllerTest {
 
    @Mock
    private IOrderService iOrderService;
 
    @InjectMocks
    private OrdersController ordersController;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void addOrder() {
        OrdersDto ordersDto = new OrdersDto();
        when(iOrderService.addOrder(any(OrdersDto.class))).thenReturn(ordersDto);
 
        ResponseEntity<OrdersDto> response = ordersController.addOrder(ordersDto);
 
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ordersDto, response.getBody());
    }
 
    @Test
    void updateOrder() {
        OrdersDto ordersDto = new OrdersDto();
        when(iOrderService.updateOrder(any(OrdersDto.class))).thenReturn(ordersDto);
 
        ResponseEntity<OrdersDto> response = ordersController.updateOrder(ordersDto);
 
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ordersDto, response.getBody());
    }
 
    @Test
    void getOrderById() {
        OrdersDto ordersDto = new OrdersDto();
        when(iOrderService.getOrderById(anyInt())).thenReturn(ordersDto);
 
        ResponseEntity<OrdersDto> response = ordersController.getOrderById(1);
 
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ordersDto, response.getBody());
    }
 
    @Test
    void cancelOrder() {
        OrdersDto ordersDto = new OrdersDto();
        when(iOrderService.cancelOrder(anyInt())).thenReturn(ordersDto);
 
        ResponseEntity<OrdersDto> response = ordersController.cancelOrder(1);
 
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ordersDto, response.getBody());
    }
 
    @Test
    void getAllOrders() {
        List<OrdersDto> ordersDtoList = new ArrayList<>();
        when(iOrderService.getAllOrders()).thenReturn(ordersDtoList);
 
        ResponseEntity<List<OrdersDto>> response = ordersController.getAllOrders();
 
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ordersDtoList, response.getBody());
    }
 
    @Test
    void placedOrderFromCart() {
        OrdersDto ordersDto = new OrdersDto();
        when(iOrderService.placedOrderFromCart(anyInt())).thenReturn(ordersDto);
 
        ResponseEntity<OrdersDto> response = ordersController.placedOrderFromCart(1);
 
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ordersDto, response.getBody());
    }
 
    @Test
    void getOrderByCustomerId() {
        List<OrdersDto> ordersDtoList = new ArrayList<>();
        when(iOrderService.getOrderByCustomerId(anyInt())).thenReturn(ordersDtoList);
 
        ResponseEntity<List<OrdersDto>> response = ordersController.getOrderByCustomerId(1);
 
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ordersDtoList, response.getBody());
    }
 
    @Test
    void addOrderByCustMob() {
        OrdersDto ordersDto = new OrdersDto();
        when(iOrderService.addOrderByCustMob(anyInt(), anyInt())).thenReturn(ordersDto);

        ResponseEntity<OrdersDto> response = ordersController.addOrder(1, 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ordersDto, response.getBody());
    }
}

