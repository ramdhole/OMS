package com.onlineapplication.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineapplication.dto.CategoryDto;
import com.onlineapplication.dto.MobileDTO;
import com.onlineapplication.entity.Category;
import com.onlineapplication.entity.Mobile;
import com.onlineapplication.exception.CategoryException;
import com.onlineapplication.repository.CategoryRepository;
import com.onlineapplication.repository.MobileRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private MobileRepository mobileRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		Category category = new Category();
		category.setCategoryName(categoryDto.getCategoryName());
		
		log.info("Category Added Successfully !!");
		return modelMapper.map(categoryRepository.save(category), CategoryDto.class);
	}

	@Override
	public List<String> getAllCategoriesName() {

		List<String> categoryNames = new ArrayList<>();
		List<Category> optCategories = this.categoryRepository.findAll();
		for (Iterator<Category> iterator = optCategories.iterator(); iterator.hasNext();) {
			Category category = (Category) iterator.next();
			categoryNames.add(category.getCategoryName());
		}
		
		log.info("Categories Found Successfully !!");
		return categoryNames;
	}

	@Override
	public CategoryDto getCategoryById(int CategoryId){

		Optional<Category> optCategories = categoryRepository.findById(CategoryId);
		if (!optCategories.isPresent()) {
			throw new CategoryException("Category id is not found: " + CategoryId);
		}
		
		log.info("Category Found Successfully With Given Id!!");
		return modelMapper.map(optCategories.get(), CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto updateCategory) {
		Category category = modelMapper.map(updateCategory, Category.class);
		
		log.info("Category Updated Successfully !!");
		return modelMapper.map(categoryRepository.save(category), CategoryDto.class);
	}

	@Override
	public List<MobileDTO> getMobilesByCategoryId(int categoryId) {

		Optional<Category> optCategories = this.categoryRepository.findById(categoryId);
		if (!optCategories.isPresent())
			throw new CategoryException("Category id does not exists to delete ");

		List<MobileDTO> list = Arrays.asList(modelMapper.map(mobileRepository.findByCategoryCategoryId(optCategories.get().getCategoryId()), MobileDTO[].class));
		
		log.info("All Categories Found Successfully !!");
		return list;
	}

	@Override
	public CategoryDto deleteCategoryById(int categoryId){

		Optional<Category> optCategories = this.categoryRepository.findById(categoryId);
		if (!optCategories.isPresent())
			throw new CategoryException("Category id does not exists to delete ");
		Category category = optCategories.get();
		this.categoryRepository.delete(category);
		
		log.info("Category Deleted Successfully !!");
		return modelMapper.map(category, CategoryDto.class);

	}

	@Override
	public List<CategoryDto> getAllCategories() {

		log.info("Categories Found Successfully !!");
		return Arrays.asList(modelMapper.map(this.categoryRepository.findAll(), CategoryDto[].class));
	}

	@Override
	public List<MobileDTO> getMobilesByCategoryName(String categoryName){

		Category findByCategoryName = categoryRepository.findByCategoryName(categoryName);

		if (findByCategoryName == null) {
			throw new CategoryException("CategoryName " + categoryName + " not exists !");

		}
		Integer categoryId = findByCategoryName.getCategoryId();
		List<Mobile> findbyCategoryList = mobileRepository.findByCategoryCategoryId(categoryId);

		log.info("Mobiles Found Successfully !!");
		return Arrays.asList(modelMapper.map(findbyCategoryList, MobileDTO[].class));

	}

}
