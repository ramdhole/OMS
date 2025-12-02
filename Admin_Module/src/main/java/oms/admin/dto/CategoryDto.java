package com.mobileapplication.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CategoryDto {

	private int categoryId;
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Give a valid category name, must contain only alphabets")
	private String categoryName;
	
}
