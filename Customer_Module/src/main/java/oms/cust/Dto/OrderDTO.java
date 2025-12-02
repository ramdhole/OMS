package com.cg.mobileStore.Dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class OrderDTO {

	private int orderId;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@FutureOrPresent
	private LocalDate orderDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@FutureOrPresent
	private LocalDate dispachDate;
	private int quantity;

	@Min(value = 0, message = "Cost should not be negative")
	private Float cost;

	@Min(value = 0, message = "totalCost should not be negative")
	private Float totalCost;
	private String orderStatus;

    private Set<MobileDTO> mobiles =  new HashSet<MobileDTO>();


}
