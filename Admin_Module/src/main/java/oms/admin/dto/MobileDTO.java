package com.mobileapplication.dto;

import lombok.Data;

@Data
public class MobileDTO {
	
	private int mobileId;
	private String mobileName;

	private String modelNumber;

	private String companyName;
	private int cameraPixcel;
	private int mobileRam;
	private int mobileBattery;

	private Float mobileCost;

	private String imagePath;

	private CategoryDto category;

	private String details;
}
