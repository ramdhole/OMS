package oms.mobile.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import oms.mobile.entity.Category;

@Data
public class MobileDTO {

	private int mobileId;
	
	@NotBlank(message = "mobileName name is needed")
	private String mobileName;

	@NotBlank(message = "modelNumber name is needed")
	@Column(unique = true)
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

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "categoryId")
	private Category category;

	private String details;
}
