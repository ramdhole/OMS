package com.orders.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate orderDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dispachDate;
	private int quantity;

	private Float cost;

	private Float totalCost;
	private String orderStatus;
	private List<Integer> mobileId = new ArrayList<>();
	private int custId;
	


}

