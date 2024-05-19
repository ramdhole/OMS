package com.oms.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDto {
	private int cartId;
	private int custId;
	private Float totalCost;
	private int quantity;
	private Map<Integer, Integer> mobileId = new HashMap<>();
}
