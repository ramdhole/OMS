package com.mobileapplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mobileapplication.controller.AdminController;
import com.mobileapplication.dto.CategoryDto;
import com.mobileapplication.dto.CustomerDTO;
import com.mobileapplication.dto.MobileDTO;
import com.mobileapplication.dto.OrderDTO;
import com.mobileapplication.service.AdminServiceImpl;

public class AdminCtrlTest {

    @Mock
    private AdminServiceImpl adminService;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddCategory() {
        CategoryDto categoryDto = new CategoryDto();
        when(adminService.addCategory(categoryDto)).thenReturn(categoryDto);

        ResponseEntity<CategoryDto> response = adminController.addCategory(categoryDto);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryDto, response.getBody());
    }

    @Test
    public void testAddMobile() {
        MobileDTO mobileDTO = new MobileDTO();
        when(adminService.addMobile(mobileDTO)).thenReturn(mobileDTO);

        ResponseEntity<MobileDTO> response = adminController.addMobile(mobileDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mobileDTO, response.getBody());
    }

    @Test
    public void testGetAllCategories() {
        List<CategoryDto> categories = Arrays.asList(new CategoryDto(), new CategoryDto());
        when(adminService.getAllCategories()).thenReturn(categories);

        ResponseEntity<List<CategoryDto>> response = adminController.getAllCategories();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categories, response.getBody());
    }

    @Test
    public void testDeleteCategoryById() {
        int categoryId = 1;
        CategoryDto categoryDto = new CategoryDto();
        when(adminService.deleteCategoryById(categoryId)).thenReturn(categoryDto);

        ResponseEntity<CategoryDto> response = adminController.deleteCategoryById(categoryId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryDto, response.getBody());
    }

    @Test
    public void testUpdateCategory() {
        CategoryDto categoryDto = new CategoryDto();
        when(adminService.updateCategory(categoryDto)).thenReturn(categoryDto);

        ResponseEntity<CategoryDto> response = adminController.updateCategory(categoryDto);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryDto, response.getBody());
    }

    @Test
    public void testGetMobilesByCategoryName() {
        String categoryName = "Smartphones";
        List<MobileDTO> mobiles = Arrays.asList(new MobileDTO(), new MobileDTO());
        when(adminService.getMobilesByCategoryName(categoryName)).thenReturn(mobiles);

        ResponseEntity<List<MobileDTO>> response = adminController.getMobilesByCategoryName(categoryName);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mobiles, response.getBody());
    }

    @Test
    public void testAllCustomer() {
        List<CustomerDTO> customers = Arrays.asList(new CustomerDTO(), new CustomerDTO());
        when(adminService.allCustomer()).thenReturn(customers);

        ResponseEntity<List<CustomerDTO>> response = adminController.allCustomer();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customers, response.getBody());
    }

    @Test
    public void testGetAllOrders() {
        List<OrderDTO> orders = Arrays.asList(new OrderDTO(), new OrderDTO());
        when(adminService.getAllOrders()).thenReturn(orders);

        ResponseEntity<List<OrderDTO>> response = adminController.getAllOrders();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orders, response.getBody());
    }
    
}

