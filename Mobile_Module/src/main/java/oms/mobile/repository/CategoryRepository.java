package oms.mobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import oms.mobile.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	public Category findByCategoryName(String categoryName);

}
