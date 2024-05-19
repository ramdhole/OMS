package com.oms.entity;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;

	@NotNull
	private int custId;
	private Float totalCost = 0F;
	private int quantity = 0;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "cart_mobile_mapping", joinColumns = @JoinColumn(name = "cart_id"))
	@MapKeyColumn(name = "mobile_id_map")
	private Map<Integer, Integer> mobileId = new HashMap<>();

}