package com.oms;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.web.client.RestTemplate;

import com.oms.dto.CartDto;
import com.oms.dto.MobileDto;
import com.oms.entity.Cart;
import com.oms.repository.ICartRepository;
import com.oms.service.CartServiceImpl;

public class CartServiceImplTest {

    @Test
    public void testCreate_Success() {
        // Mock dependencies
        ICartRepository cartRepositoryMock = mock(ICartRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);

        // Create instance of CartServiceImpl and set mocked dependencies
        CartServiceImpl cartService = new CartServiceImpl();
        cartService.setCartRepository(cartRepositoryMock);
        cartService.setModelMapper(modelMapperMock);

        // Prepare test data
        int custId = 1;
        Cart cart = new Cart();
        CartDto cartDto = new CartDto();

        // Setup mock behavior
        when(cartRepositoryMock.save(any())).thenReturn(cart);
        when(modelMapperMock.map(any(), eq(CartDto.class))).thenReturn(cartDto);

        // Call the method under test
        CartDto result = cartService.create(custId);

        // Verify the result
        assertNotNull(result);
        assertEquals(cartDto, result);
    }

    @Test
    public void testUpdateCart_Success() {
        // Mock dependencies
        ICartRepository cartRepositoryMock = mock(ICartRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);

        // Create instance of CartServiceImpl and set mocked dependencies
        CartServiceImpl cartService = new CartServiceImpl();
        cartService.setCartRepository(cartRepositoryMock);
        cartService.setModelMapper(modelMapperMock);

        // Prepare test data
        Cart cart = new Cart();
        CartDto cartDto = new CartDto();

        // Setup mock behavior
        when(cartRepositoryMock.findByCustId(anyInt())).thenReturn(Optional.of(cart));
        when(cartRepositoryMock.save(any())).thenReturn(cart);
        when(modelMapperMock.map(any(), eq(CartDto.class))).thenReturn(cartDto);

        // Call the method under test
        CartDto result = cartService.updateCart(cart);

        // Verify the result
        assertNotNull(result);
        assertEquals(cartDto, result);
    }

    @Test
    public void testDeleteCartById_Success() {
        // Mock dependencies
        ICartRepository cartRepositoryMock = mock(ICartRepository.class);

        // Create instance of CartServiceImpl and set mocked dependencies
        CartServiceImpl cartService = new CartServiceImpl();
        cartService.setCartRepository(cartRepositoryMock);

        // Prepare test data
        int cartId = 1;

        // Setup mock behavior
        when(cartRepositoryMock.findById(anyInt())).thenReturn(Optional.of(new Cart()));
        when(cartRepositoryMock.save(any())).thenReturn(new Cart());

        // Call the method under test
        assertDoesNotThrow(() -> cartService.deleteCartById(cartId));
    }

    @Test
    public void testGetCartByCustId_Success() {
        // Mock dependencies
        ICartRepository cartRepositoryMock = mock(ICartRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);

        // Create instance of CartServiceImpl and set mocked dependencies
        CartServiceImpl cartService = new CartServiceImpl();
        cartService.setCartRepository(cartRepositoryMock);
        cartService.setModelMapper(modelMapperMock);

        // Prepare test data
        int custId = 1;
        Cart cart = new Cart();
        CartDto cartDto = new CartDto();

        // Setup mock behavior
        when(cartRepositoryMock.findByCustId(anyInt())).thenReturn(Optional.of(cart));
        when(modelMapperMock.map(any(), eq(CartDto.class))).thenReturn(cartDto);

        // Call the method under test
        CartDto result = cartService.getCartByCustId(custId);

        // Verify the result
        assertNotNull(result);
        assertEquals(cartDto, result);
    }

    @Test
    public void testRemoveMobilefromCartById_Success() {
        // Mock dependencies
        ICartRepository cartRepositoryMock = mock(ICartRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        RestTemplate restTemplateMock = mock(RestTemplate.class);

        // Create instance of CartServiceImpl and set mocked dependencies
        CartServiceImpl cartService = new CartServiceImpl();
        cartService.setCartRepository(cartRepositoryMock);
        cartService.setModelMapper(modelMapperMock);
        cartService.setRestTemplate(restTemplateMock);

        // Prepare test data
        int mobileId = 1;
        int cartId = 1;
        Cart cart = new Cart();
        cart.setMobileId(new HashMap<>());
        cart.getMobileId().put(mobileId, 1);
        CartDto cartDto = new CartDto();

        // Setup mock behavior
        when(cartRepositoryMock.findById(cartId)).thenReturn(Optional.of(cart));
        when(cartRepositoryMock.save(any())).thenReturn(new Cart());
        when(modelMapperMock.map(any(), eq(CartDto.class))).thenReturn(cartDto);

        // Call the method under test
        CartDto result = cartService.removeMobilefromCartById(mobileId, cartId);

        // Verify the result
        assertNotNull(result);
        assertEquals(cartDto, result);
    }

    @Test
    public void testGetAllMobile_Success() {
        // Mock dependencies
        ICartRepository cartRepositoryMock = mock(ICartRepository.class);
        RestTemplate restTemplateMock = mock(RestTemplate.class);

        // Create instance of CartServiceImpl and set mocked dependencies
        CartServiceImpl cartService = new CartServiceImpl();
        cartService.setCartRepository(cartRepositoryMock);
        cartService.setRestTemplate(restTemplateMock);

        // Prepare test data
        int custId = 1;
        Cart cart = new Cart();
        cart.setMobileId(new HashMap<>());

        // Setup mock behavior
        when(cartRepositoryMock.findByCustId(custId)).thenReturn(Optional.of(cart));
        when(restTemplateMock.getForObject(anyString(), eq(MobileDto.class))).thenReturn(new MobileDto());

    }
    
    @Test
    public void testGetCartByCartId_Success() {
        // Mock dependencies
        ICartRepository cartRepositoryMock = mock(ICartRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);

        // Create instance of CartServiceImpl and set mocked dependencies
        CartServiceImpl cartService = new CartServiceImpl();
        cartService.setCartRepository(cartRepositoryMock);
        cartService.setModelMapper(modelMapperMock);

        // Prepare test data
        int cartId = 1;
        Cart cart = new Cart();
        CartDto cartDto = new CartDto();

        // Setup mock behavior
        when(cartRepositoryMock.findById(cartId)).thenReturn(Optional.of(cart));
        when(modelMapperMock.map(cart, CartDto.class)).thenReturn(cartDto);

        // Call the method under test
        CartDto result = cartService.getCartByCartId(cartId);

        // Verify the result
        assertNotNull(result);
        assertEquals(cartDto, result);
    }

 


}
