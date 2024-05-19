package com.cg.mobileStore.Dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class CartDTO {

	private int cartId;
	private int custId;
	private Float totalCost;
	private int quantity;
	private Map<Integer, Integer> mobileId = new HashMap<>();
	
}
