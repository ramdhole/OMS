package com.mobileapplication;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.mobileapplication.dto.CategoryDto;
import com.mobileapplication.dto.CustomerDTO;
import com.mobileapplication.dto.MobileDTO;
import com.mobileapplication.dto.OrderDTO;
import com.mobileapplication.entity.Admin;
import com.mobileapplication.repository.AdminRepository;
import com.mobileapplication.service.AdminNotFoundException;
import com.mobileapplication.service.AdminServiceImpl;



public class AdminServiceImplTest {

	@Mock
    private RestTemplate restTemplate;

	 @Mock
	    private AdminRepository repository;
	 
    @InjectMocks
    private AdminServiceImpl adminService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddCategory() {
        CategoryDto categoryDto = new CategoryDto();
        ResponseEntity<CategoryDto> responseEntity = new ResponseEntity<>(categoryDto, HttpStatus.OK);
        when(restTemplate.postForEntity("http://localhost:9990/category/addCatogery", categoryDto, CategoryDto.class))
                .thenReturn(responseEntity);

        CategoryDto result = adminService.addCategory(categoryDto);

        assertNotNull(result);
        assertEquals(categoryDto, result);
    }

   

	@Test
    public void testGetAllCategories() {
        List<CategoryDto> categories = Arrays.asList(new CategoryDto(), new CategoryDto());
        ResponseEntity<List<CategoryDto>> responseEntity = new ResponseEntity<>(categories, HttpStatus.OK);
        when(restTemplate.exchange("http://localhost:9990/category/getAll", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<CategoryDto>>() {})).thenReturn(responseEntity);

        List<CategoryDto> result = adminService.getAllCategories();

        assertNotNull(result);
        assertEquals(categories.size(), result.size());
    }

    @Test
    public void testDeleteCategoryById() {
        int categoryId = 1;
        CategoryDto categoryDto = new CategoryDto();
        ResponseEntity<CategoryDto> responseEntity = new ResponseEntity<>(categoryDto, HttpStatus.OK);
        when(restTemplate.exchange("http://localhost:9990/category/deleteById/" + categoryId, HttpMethod.DELETE, null,
                CategoryDto.class, categoryId)).thenReturn(responseEntity);

        CategoryDto result = adminService.deleteCategoryById(categoryId);

        assertNotNull(result);
        assertEquals(categoryDto, result);
    }

    @Test
    public void testUpdateCategory() {
        CategoryDto categoryDto = new CategoryDto();
        restTemplate.put("http://localhost:9990/category/updateCat", categoryDto);
        CategoryDto result = adminService.updateCategory(categoryDto);
        assertEquals(categoryDto, result);
    }

    @Test
    public void testGetMobilesByCategoryName() {
        String categoryName = "Smartphones";
        List<MobileDTO> mobiles = Arrays.asList(new MobileDTO(), new MobileDTO());
        ResponseEntity<List<MobileDTO>> responseEntity = new ResponseEntity<>(mobiles, HttpStatus.OK);
        when(restTemplate.exchange("http://localhost:9990/mobile/get/byCategoryName/" + categoryName, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<MobileDTO>>() {}, categoryName)).thenReturn(responseEntity);

        List<MobileDTO> result = adminService.getMobilesByCategoryName(categoryName);

        assertNotNull(result);
        assertEquals(mobiles.size(), result.size());
    }

    @Test
    public void testAllCustomer() {
        List<CustomerDTO> customers = Arrays.asList(new CustomerDTO(), new CustomerDTO());
        ResponseEntity<List<CustomerDTO>> responseEntity = new ResponseEntity<>(customers, HttpStatus.OK);
        when(restTemplate.exchange("http://localhost:9991/customer/getAll", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<CustomerDTO>>() {})).thenReturn(responseEntity);

        List<CustomerDTO> result = adminService.allCustomer();

        assertNotNull(result);
        assertEquals(customers.size(), result.size());
    }

    @Test
    public void testGetAllOrders() {
        List<OrderDTO> orders = Arrays.asList(new OrderDTO(), new OrderDTO());
        ResponseEntity<List<OrderDTO>> responseEntity = new ResponseEntity<>(orders, HttpStatus.OK);
        when(restTemplate.exchange("http://localhost:9993/order/getAll", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<OrderDTO>>() {})).thenReturn(responseEntity);

        List<OrderDTO> result = adminService.getAllOrders();

        assertNotNull(result);
        assertEquals(orders.size(), result.size());
    }

    @Test
    public void testAddMobile() {
        MobileDTO mobileDTO = new MobileDTO();
        ResponseEntity<MobileDTO> responseEntity = new ResponseEntity<>(mobileDTO, HttpStatus.OK);
        when(restTemplate.postForEntity("http://localhost:9990/mobile/add", mobileDTO, MobileDTO.class))
                .thenReturn(responseEntity);

        MobileDTO result = adminService.addMobile(mobileDTO);

        assertNotNull(result);
        assertEquals(mobileDTO, result);
    }
    
    @Test
    void testGetAdmin_Success() {
        // Arrange
        String userName = "admin1";
        Admin admin = new Admin();
        admin.setAdminName(userName);
        Optional<Admin> optionalAdmin = Optional.of(admin);
        when(repository.findByAdminName(userName)).thenReturn(optionalAdmin).getMock();

        // Act
        Admin result = adminService.getAdmin(userName);

        // Assert
        assertNotNull(result);
        assertEquals(userName, result.getAdminName());
    }

    @Test
    void testGetAdmin_AdminNotFound() {
        // Arrange
        String userName = "nonexistentadmin";
        Optional<Admin> optionalAdmin = Optional.empty();
        when(repository.findByAdminName(userName)).thenReturn(optionalAdmin);

        // Act & Assert
        assertThrows(AdminNotFoundException.class, () -> adminService.getAdmin(userName));
    }
}
