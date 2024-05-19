package com.orders.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDto {

	private int orderId;

	@JsonFormat(pattern = "yyyy-MM-dd")
	// @FutureOrPresent
	private LocalDate orderDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	// @FutureOrPresent
	private LocalDate dispachDate;
	private int quantity;

	// @Min(value = 0, message = "Cost should not be negative")
	private Float cost;

	// @Min(value = 0, message = "totalCost should not be negative")
	private Float totalCost;
	private String orderStatus;

	private List<Integer> mobileId = new ArrayList<>();

	private int custId;

}
