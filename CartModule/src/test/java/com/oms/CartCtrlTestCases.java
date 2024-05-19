package com.oms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oms.controller.CartController;
import com.oms.dto.CartDto;
import com.oms.dto.MobileDto;
import com.oms.entity.Cart;
import com.oms.service.ICartService;

public class CartCtrlTestCases {

    @Test
    public void testAddCart() {
        ICartService cartServiceMock = mock(ICartService.class);
        CartController controller = new CartController();
        controller.setCartService(cartServiceMock);

        int customerId = 1;
        CartDto expectedCartDto = new CartDto(); // create expected CartDto

        when(cartServiceMock.create(customerId)).thenReturn(expectedCartDto);

        ResponseEntity<CartDto> response = controller.addCart(customerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCartDto, response.getBody());
    }

    @Test
    public void testAddMobileToCartByCustomerId() {
        ICartService cartServiceMock = mock(ICartService.class);
        CartController controller = new CartController();
        controller.setCartService(cartServiceMock);

        int mobileId = 1;
        int customerId = 1;
        CartDto expectedCartDto = new CartDto(); // create expected CartDto

        when(cartServiceMock.addMobileToCartBycustomerId(mobileId, customerId)).thenReturn(expectedCartDto);

        ResponseEntity<CartDto> response = controller.addMobileToCartBycustomerId(mobileId, customerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCartDto, response.getBody());
    }

    @Test
    public void testGetAllMobile() {
        ICartService cartServiceMock = mock(ICartService.class);
        CartController controller = new CartController();
        controller.setCartService(cartServiceMock);

        int customerId = 1;
        List<MobileDto> expectedMobileList = new ArrayList<>(); // create expected MobileDto list

        when(cartServiceMock.getAllMobile(customerId)).thenReturn(expectedMobileList);

        ResponseEntity<List<MobileDto>> response = controller.getAllMobile(customerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedMobileList, response.getBody());
    }
    
    @Test
    public void testGetCustById_Success() {
        // Mock dependencies
        ICartService cartServiceMock = mock(ICartService.class);

        // Create instance of CartController and set mocked dependency
        CartController cartController = new CartController();
        cartController.setCartService(cartServiceMock);

        // Prepare test data
        int custId = 1;
        CartDto cartDto = new CartDto();

        // Setup mock behavior
        when(cartServiceMock.getCartByCustId(custId)).thenReturn(cartDto);

        // Call the method under test
        ResponseEntity<CartDto> response = cartController.getCustById(custId);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cartDto, response.getBody());
    }

    @Test
    public void testGetCartById_Success() {
        // Mock dependencies
        ICartService cartServiceMock = mock(ICartService.class);

        // Create instance of CartController and set mocked dependency
        CartController cartController = new CartController();
        cartController.setCartService(cartServiceMock);

        // Prepare test data
        int cartId = 1;
        CartDto cartDto = new CartDto();

        // Setup mock behavior
        when(cartServiceMock.getCartByCartId(cartId)).thenReturn(cartDto);

        // Call the method under test
        ResponseEntity<CartDto> response = cartController.getCartById(cartId);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cartDto, response.getBody());
    }

    @Test
    public void testGetAllCarts_Success() {
        // Mock dependencies
        ICartService cartServiceMock = mock(ICartService.class);

        // Create instance of CartController and set mocked dependency
        CartController cartController = new CartController();
        cartController.setCartService(cartServiceMock);

        // Prepare test data
        List<CartDto> cartDtos = new ArrayList<>();

        // Setup mock behavior
        when(cartServiceMock.getAllCarts()).thenReturn(cartDtos);

        // Call the method under test
        ResponseEntity<List<CartDto>> response = cartController.getAllCarts();

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cartDtos, response.getBody());
    }

    @Test
    public void testRemoveMobilefromCartById_Success() {
        // Mock dependencies
        ICartService cartServiceMock = mock(ICartService.class);

        // Create instance of CartController and set mocked dependency
        CartController cartController = new CartController();
        cartController.setCartService(cartServiceMock);

        // Prepare test data
        int mobileId = 1;
        int cartId = 1;
        CartDto cartDto = new CartDto();

        // Setup mock behavior
        when(cartServiceMock.removeMobilefromCartById(mobileId, cartId)).thenReturn(cartDto);

        // Call the method under test
        ResponseEntity<CartDto> response = cartController.removeMobilefromCartById(mobileId, cartId);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cartDto, response.getBody());
    }

    @Test
    public void testCartServiceUpdate_Success() {
        // Mock dependencies
        ICartService cartServiceMock = mock(ICartService.class);

        // Create instance of CartController and set mocked dependency
        CartController cartController = new CartController();
        cartController.setCartService(cartServiceMock);

        // Prepare test data
        Cart cart = new Cart();
        CartDto cartDto = new CartDto();

        // Setup mock behavior
        when(cartServiceMock.updateCart(cart)).thenReturn(cartDto);

        // Call the method under test
        ResponseEntity<CartDto> response = cartController.CartServiceUpdate(cart);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cartDto, response.getBody());
    }

    @Test
    public void testDeleteCartById_Success() {
        // Mock dependencies
        ICartService cartServiceMock = mock(ICartService.class);

        // Create instance of CartController and set mocked dependency
        CartController cartController = new CartController();
        cartController.setCartService(cartServiceMock);

        // Prepare test data
        int cartId = 1;

        // Call the method under test
        ResponseEntity<HttpStatus> response = cartController.deleteCartById(cartId);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    
}
