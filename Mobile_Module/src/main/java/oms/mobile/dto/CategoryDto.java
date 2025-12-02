package com.onlineapplication.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
	
	private int categoryId;
	
	@NotNull(message = "Name of cateogry required")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Give a valid category name, must contain only alphabets")
    private String categoryName;
    
    

}
