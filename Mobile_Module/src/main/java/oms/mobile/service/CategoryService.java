package oms.mobile.service;

import java.util.List;

import jakarta.validation.Valid;
import oms.mobile.dto.CategoryDto;
import oms.mobile.dto.MobileDTO;

public interface CategoryService {
	
	public CategoryDto createCategory(@Valid CategoryDto categoryDto);
	
    public List<CategoryDto> getAllCategories();
    
    public CategoryDto getCategoryById(int CategoryId);

	public CategoryDto updateCategory(CategoryDto updateCategory);

	public List<MobileDTO> getMobilesByCategoryId(int categoryId) ;

	public CategoryDto deleteCategoryById(int categoryId) ;
	
	public List<String> getAllCategoriesName();

	public List<MobileDTO> getMobilesByCategoryName(String categoryName);
}
