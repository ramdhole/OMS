package com.mobileapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mobileapplication.dto.CategoryDto;
import com.mobileapplication.dto.CustomerDTO;
import com.mobileapplication.dto.MobileDTO;
import com.mobileapplication.dto.OrderDTO;
import com.mobileapplication.entity.Admin;
import com.mobileapplication.repository.AdminRepository;

import jakarta.validation.Valid;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRepository repository;

	@Autowired
	private RestTemplate restTemplate;
	
	private final String urlMob = "http://localhost:9990/mobile";
	private final String urlCat = "http://localhost:9990/category";
	private final String urlCust = "http://localhost:9991/customer";
	private final String urlOrder = "http://localhost:9993/order";

	public CategoryDto addCategory(@Valid CategoryDto categoryDto) {
		ResponseEntity<CategoryDto> response = restTemplate.postForEntity(urlCat+"/addCatogery",
				categoryDto, CategoryDto.class);
		return response.getBody();
	}

	public List<CategoryDto> getAllCategories() {
		ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(urlCat+"/getAll",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<CategoryDto>>() {
				});
		return response.getBody();
	}

	public CategoryDto deleteCategoryById(int categoryId) {
		ResponseEntity<CategoryDto> response = restTemplate.exchange(
				urlCat+"/deleteById/"+categoryId, HttpMethod.DELETE, null, CategoryDto.class,
				categoryId);
		return response.getBody();
	}

	public CategoryDto updateCategory(@Valid CategoryDto category) {
		restTemplate.put(urlCat+"/updateCat", category);
		return category;
	}

	public List<MobileDTO> getMobilesByCategoryName(String categoryName){
		ResponseEntity<List<MobileDTO>> response = restTemplate.exchange(
				urlMob+"/get/byCategoryName/"+categoryName, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<MobileDTO>>() {
				}, categoryName);
		return response.getBody();
	}

	public List<CustomerDTO> allCustomer(){
		ResponseEntity<List<CustomerDTO>> response = restTemplate.exchange(urlCust+"/getAll",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerDTO>>() {
				});
		return response.getBody();
	}

	public List<OrderDTO> getAllOrders() {
		ResponseEntity<List<OrderDTO>> response = restTemplate.exchange(urlOrder+"/getAll",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderDTO>>() {
				});
		return response.getBody();
	}

	public MobileDTO addMobile(@Valid MobileDTO mobile) {
		ResponseEntity<MobileDTO> response = restTemplate.postForEntity(urlMob+"/add",
				mobile, MobileDTO.class);
		return response.getBody();
	}

	public Admin getAdmin(String userName) {
		
		Optional<Admin> admin = repository.findByAdminName(userName);
		if(!admin.isPresent())
			throw new AdminNotFoundException("Admin Not found"); 
		
		return admin.get();
	}


	
}