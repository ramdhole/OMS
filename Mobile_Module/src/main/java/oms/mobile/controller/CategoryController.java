package com.onlineapplication.controller;

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

import com.onlineapplication.dto.CategoryDto;
import com.onlineapplication.service.CategoryServiceImpl;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
	@PostMapping("/addCatogery")
	public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
		return new ResponseEntity<>(categoryServiceImpl.createCategory(categoryDto), HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		return new ResponseEntity<>(categoryServiceImpl.getAllCategories(), HttpStatus.OK);
	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<CategoryDto> deleteById(@PathVariable int id){
		return new ResponseEntity<>(categoryServiceImpl.deleteCategoryById(id), HttpStatus.OK);
	}
	
	@PutMapping("/updateCat")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto){
		return new ResponseEntity<>(categoryServiceImpl.updateCategory(categoryDto), HttpStatus.OK);
	}
	
	

}
