package com.cg.mobileStore;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
 
import com.cg.mobileStore.Dto.CartDTO;
import com.cg.mobileStore.Dto.CustomerDTO;
import com.cg.mobileStore.Dto.MobileDTO;
import com.cg.mobileStore.Dto.OrderDTO;
import com.cg.mobileStore.Entity.Customer;
import com.cg.mobileStore.Exception.*;
import com.cg.mobileStore.Repository.CustomerRepository;
import com.cg.mobileStore.Service.CustomerServiceImpl;
 
@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
 
    @Mock
    private CustomerRepository customerRepository;
 
    @Mock
    private ModelMapper modelMapper;
 
    @Mock
    private RestTemplate restTemplate;
 
    @Mock
    private BCryptPasswordEncoder encoder;
 
    @InjectMocks
    private CustomerServiceImpl customerService;
 
    // Initialize test data
    private Customer customer;
    private CartDTO cartDto;
    private OrderDTO orderDto;
    private List<MobileDTO> mobileList;
 
    @BeforeEach
    void setUp() {
        // Initialize test customer
        customer = new Customer();
        customer.setEmailId("test@example.com");
        customer.setMobileNumber("1234567890");
        customer.setPassword("password");
        customer.setCustomerId(1);
 
        // Initialize test cart DTO
        cartDto = new CartDTO();
        cartDto.setCartId(1);
 
        // Initialize test order DTO
        orderDto = new OrderDTO();
        orderDto.setOrderId(1);
 
        // Initialize test mobile DTO list
        mobileList = new ArrayList<>();
        mobileList.add(new MobileDTO());
    }
 
    @Test
    void testAddCustomer_Success() {
        // Mock repository behavior
        when(customerRepository.findByEmailId(customer.getEmailId())).thenReturn(Optional.empty());
        when(customerRepository.findByMobileNumber(customer.getMobileNumber())).thenReturn(Optional.empty());
        when(customerRepository.save(customer)).thenReturn(customer);
 
        // Mock encoder
        when(encoder.encode(customer.getPassword())).thenReturn(customer.getPassword());
 
        // Mock restTemplate
        when(restTemplate.getForObject(anyString(), eq(CartDTO.class))).thenReturn(cartDto);
 
        // Mock modelMapper
        when(modelMapper.map(customer, CustomerDTO.class)).thenReturn(new CustomerDTO());
 
        // Test method
        assertDoesNotThrow(() -> customerService.addCustomer(customer));
    }
    
    @Test
    void testDeleteCustomer_Success() {
        // Given
        int customerId = 1;
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
 
        // When
        customerService.deleteCustomer(customerId);
 
        // Then
        verify(customerRepository, times(1)).deleteById(customerId);
    }
 
    @Test
    void testDeleteCustomer_CustomerNotFound() {
        // Given
        int customerId = 1;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
 
        // When/Then
        assertThrows(CustomerNotFoundException.class, () -> customerService.deleteCustomer(customerId));
    }
 
    @Test
    void testRemoveMobilefromCartByIds_Success() {
        // Given
        int mobileId = 1;
        int cartId = 1;
        when(restTemplate.getForObject(anyString(), eq(CartDTO.class))).thenReturn(cartDto);
 
        // When
        CartDTO result = customerService.removeMobilefromCartByIds(mobileId, cartId);
 
        // Then
        assertNotNull(result);
        assertEquals(cartDto, result);
    }
 
    @Test
    void testRemoveMobilefromCartByIds_CartNotFound() {
        // Given
        int mobileId = 1;
        int cartId = 1;
        when(restTemplate.getForObject(anyString(), eq(CartDTO.class))).thenReturn(null);
 
        // When/Then
        assertThrows(CartNotFoundException.class, () -> customerService.removeMobilefromCartByIds(mobileId, cartId));
    }
 
    @Test
    void testGetCustById_Success() {
        // Given
        int customerId = 1;
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(modelMapper.map(any(), eq(CustomerDTO.class))).thenReturn(new CustomerDTO());
 
        // When
        CustomerDTO result = customerService.getCustById(customerId);
 
        // Then
        assertNotNull(result);
    }
 
    @Test
    void testGetCustById_CustomerNotFound() {
        // Given
        int customerId = 1;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
 
        // When/Then
        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustById(customerId));
    }
 
 
    @Test
    void testGetAllOrder_CustomerNotFound() {
        // Given
        int customerId = 1;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
 
        // When/Then
        assertThrows(CustomerNotFoundException.class, () -> customerService.getAllOrder(customerId));
    }
    @Test
    void testUpdateAddress_Success() {
        // Given
        String emailId = "test@example.com";
        String address = "New Address";
        when(customerRepository.findByEmailId(emailId)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        when(modelMapper.map(any(Customer.class), eq(CustomerDTO.class))).thenReturn(new CustomerDTO());
 
        // When
        CustomerDTO result = customerService.updateAddress(emailId, address);
 
        // Then
        assertNotNull(result);
        assertEquals(address, customer.getAddress());
    }
 
    @Test
    void testUpdateAddress_CustomerNotFound() {
        // Given
        String emailId = "test@example.com";
        String address = "New Address";
        when(customerRepository.findByEmailId(emailId)).thenReturn(Optional.empty());
 
        // When/Then
        assertThrows(CustomerNotFoundException.class, () -> customerService.updateAddress(emailId, address));
    }
 
    @Test
    void testGetCartByCustomerId_Success() {
        // Given
        int customerId = 1;
        when(restTemplate.getForObject(anyString(), eq(CartDTO.class))).thenReturn(cartDto);
 
        // When
        CartDTO result = customerService.getCartByCustomerId(customerId);
 
        // Then
        assertNotNull(result);
    }
 
    @Test
    void testGetCartByCustomerId_CartNotFound() {
        // Given
        int customerId = 1;
        when(restTemplate.getForObject(anyString(), eq(CartDTO.class))).thenReturn(null);
 
        // When/Then
        assertThrows(CartNotFoundException.class, () -> customerService.getCartByCustomerId(customerId));
    }
 
    @Test
    void testFindMobileByName_Success() {
        // Given
        String mobileName = "Mobile";
        when(restTemplate.getForObject(anyString(), eq(List.class))).thenReturn(mobileList);
 
        // When
        List<MobileDTO> result = customerService.findMobileByName(mobileName);
 
        // Then
        assertNotNull(result);
    }
 
    @Test
    void testFindMobileByName_MobileNotFound() {
        // Given
        String mobileName = "Mobile";
        when(restTemplate.getForObject(anyString(), eq(List.class))).thenReturn(null);
 
        // When/Then
        assertThrows(MobileNotFoundException.class, () -> customerService.findMobileByName(mobileName));
    }
 
    @Test
    void testAddMobileToCart_Success() {
        // Given
        int customerId = 1;
        int mobileId = 1;
        when(restTemplate.getForObject(anyString(), eq(CartDTO.class))).thenReturn(cartDto);
 
        // When
        CartDTO result = customerService.addMobileToCart(customerId, mobileId);
 
        // Then
        assertNotNull(result);
    }
 
    @Test
    void testAddMobileToCart_MobileNotAdded() {
        // Given
        int customerId = 1;
        int mobileId = 1;
        when(restTemplate.getForObject(anyString(), eq(CartDTO.class))).thenReturn(null);
 
        // When/Then
        assertThrows(CartNotFoundException.class, () -> customerService.addMobileToCart(customerId, mobileId));
    }
 
 
    @Test
    void testGetCustByEmail_CustomerNotFound() {
        // Given
        String email = "test@example.com";
        when(customerRepository.findByEmailId(email)).thenReturn(Optional.empty());
 
        // When/Then
        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustByEmail(email));
    }
 
  
 
    @Test
    void testGetAll_CustomerNotFound() {
        // Given
        when(customerRepository.findAll()).thenReturn(new ArrayList<>());
 
        // When/Then
        assertThrows(CustomerNotFoundException.class, () -> customerService.getAll());
    }
 
 
 
 
}