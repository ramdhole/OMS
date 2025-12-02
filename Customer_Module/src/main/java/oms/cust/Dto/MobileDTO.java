package com.cg.mobileStore.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MobileDTO {
	
	private int mobileId;
	@NotBlank(message = "mobileName name is needed")
	private String mobileName;

	@Min(value = 0, message = "mobileCost must be greater than 0")
	private Float mobileCost;

	@NotBlank(message = "modelNumber name is needed")
	private String modelNumber;
	
	@NotBlank(message = "companyName name is needed")
	private String companyName;

	private int cameraPixcel;
	private int mobileRAM;

	private int battery;
	
	private String details;

}
