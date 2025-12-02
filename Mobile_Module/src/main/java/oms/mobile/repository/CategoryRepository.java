package com.onlineapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineapplication.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	public Category findByCategoryName(String categoryName);

}
