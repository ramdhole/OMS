package com.oms.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MobileDto {
	private int mobileId;
	private String mobileName;
	private Float mobileCost;
	private LocalDate mfDate;
	private String modelNumber;
	private String companyName;

	private int cameraPixcel;
	private int mobileRAM;

	private int battery;
	private String imagePath;
	
	private String details;

}
