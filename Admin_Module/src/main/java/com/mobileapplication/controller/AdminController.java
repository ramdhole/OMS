package com.mobileapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobileapplication.dto.CategoryDto;
import com.mobileapplication.dto.CustomerDTO;
import com.mobileapplication.dto.MobileDTO;
import com.mobileapplication.dto.OrderDTO;
import com.mobileapplication.entity.Admin;
import com.mobileapplication.service.AdminService;
import com.mobileapplication.service.AdminServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
    private AdminService adminService;

    @PostMapping("/category/add")
   // @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(adminService.addCategory(categoryDto), HttpStatus.OK);
    }
    
    @PostMapping("/mobile/add")
    // @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<MobileDTO> addMobile(@Valid @RequestBody MobileDTO mobile) {
    	return new ResponseEntity<>(adminService.addMobile(mobile), HttpStatus.OK);
    }

    @GetMapping("/category/get/all")
   // @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return new ResponseEntity<>(adminService.getAllCategories(), HttpStatus.OK);
    }

    @DeleteMapping("/category/delete/{categoryId}")
   // @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<CategoryDto> deleteCategoryById(@PathVariable("categoryId") int categoryId){
        return new ResponseEntity<>(adminService.deleteCategoryById(categoryId), HttpStatus.OK);
    }

    @PutMapping("/category/update")
   // @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto category) {
        return new ResponseEntity<>(adminService.updateCategory(category), HttpStatus.OK);
    }

    @GetMapping("/category/getMobilesBycatName/{categoryName}")
   // @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<List<MobileDTO>> getMobilesByCategoryName(@PathVariable("categoryName") String categoryName){
        return new ResponseEntity<>(adminService.getMobilesByCategoryName(categoryName), HttpStatus.OK);
    }

    @GetMapping("/allCustomer")
    //@PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<List<CustomerDTO>> allCustomer() {
        return new ResponseEntity<>(adminService.allCustomer(), HttpStatus.OK);
    }

    @GetMapping("/getAll/orders")
   // @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return new ResponseEntity<>(adminService.getAllOrders(), HttpStatus.OK);
    }
    
    @GetMapping("/getAdmin/{userName}")
    public ResponseEntity<Admin> getAdmin(@PathVariable("userName") String userName) {
    	return new ResponseEntity<>(adminService.getAdmin(userName), HttpStatus.OK);
    }
    
    
}