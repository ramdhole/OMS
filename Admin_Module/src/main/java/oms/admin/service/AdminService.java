package com.mobileapplication.service;

import java.util.List;

import com.mobileapplication.dto.CategoryDto;
import com.mobileapplication.dto.CustomerDTO;
import com.mobileapplication.dto.MobileDTO;
import com.mobileapplication.dto.OrderDTO;
import com.mobileapplication.entity.Admin;

import jakarta.validation.Valid;

public interface AdminService {
	CategoryDto addCategory(@Valid CategoryDto categoryDto);

	List<CategoryDto> getAllCategories();

	CategoryDto deleteCategoryById(int categoryId);

	CategoryDto updateCategory(@Valid CategoryDto category);

	List<MobileDTO> getMobilesByCategoryName(String categoryName);

	List<CustomerDTO> allCustomer();

	List<OrderDTO> getAllOrders();

	MobileDTO addMobile(@Valid MobileDTO mobile);

	Admin getAdmin(String userName);
}
