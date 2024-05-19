package com.onlineapplication.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mobile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long mobileId;
	
	@NotBlank(message = "mobileName name is needed")
    private String mobileName;
    
	@NotBlank(message = "modelNumber name is needed")
	private String modelNumber;
	
	@NotBlank(message = "companyName name is needed")
    private String companyName;
	private int cameraPixcel;
	private int mobileRam;
	private int mobileBattery;
	
	@Min(value = 0, message = "mobileCost must be greater than 0")
    private Float mobileCost;
    
	@NotBlank(message = "imagePath url is needed")
    private String imagePath;	
	
	@Column(columnDefinition = "TEXT")
	private String details;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="categoryId")
	private Category category;
	
	@NotNull
	private int availableQuantity;
	
	
}
