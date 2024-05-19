package com.orders.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MobilesDto {
	private int mobileId;
	private String mobileName;
	private Float mobileCost;
	private LocalDate mfDate;
	private String modelNumber;
	private String companyName;

	private int cameraPixcel;
	private int mobileRam;

	private int mobileBattery;
	private String imagePath;
	private String details;

}
