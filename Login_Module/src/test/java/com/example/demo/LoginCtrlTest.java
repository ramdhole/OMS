package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.oms.controller.LoginCtrl;
import com.oms.dto.CustomerDTO;
import com.oms.entity.JWTResponse;
import com.oms.service.LoginServ;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginCtrlTest {

	@Test
	void contextLoads() {
	}
    private MockMvc mockMvc;

    @Mock
    private LoginServ loginServMock;

    @InjectMocks
    private LoginCtrl loginCtrl;

    @SuppressWarnings("deprecation")
	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(loginCtrl).build();
    }

    @Test
    public void testSignIn() throws Exception {
        // Given
        CustomerDTO customer = new CustomerDTO();
        customer.setEmailId("testuser");
        customer.setPassword("testpassword");

        JWTResponse mockResponse = new JWTResponse("token123");

        when(loginServMock.signIn(customer)).thenReturn(mockResponse);

        // When
        mockMvc.perform(post("/login/signIn")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"testuser\",\"password\":\"testpassword\"}"))
                .andExpect(status().isOk());

        // Then
        // Add assertions here if necessary
    }

    @Test
    public void testAdmin() throws Exception {
        // Given
        String username = "admin";
        String password = "admin123";

        JWTResponse mockResponse = new JWTResponse("admin-token");

        when(loginServMock.signInAdmin(username, password)).thenReturn(mockResponse);

        // When
        mockMvc.perform(get("/login/admin")
                .param("userName", username)
                .param("password", password))
                .andExpect(status().isOk());

        // Then
        // Add assertions here if necessary
    }
}
