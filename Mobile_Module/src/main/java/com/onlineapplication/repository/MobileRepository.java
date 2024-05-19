package com.onlineapplication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineapplication.entity.Mobile;
import com.onlineapplication.entity.Category;


public interface MobileRepository extends JpaRepository<Mobile, Long> {

	public List<Mobile> findByCategoryCategoryName(String categoryName);

	public List<Mobile> findByCompanyNameAndMobileNameAndModelNumber(String companyName, String mobileName,
			String modelNumber);

	public List<Mobile> findByMobileRam(int mobileRam);

	public List<Mobile> findByCameraPixcelAndMobileRamAndMobileBattery(int cameraPixcel, int mobileRam,
			int mobileBattery);

	public List<Mobile> findByMobileCostLessThan(Float mobileCost);

	public List<Mobile> findByMobileCostGreaterThan(Float mobileCost);

	public List<Mobile> findByCategoryCategoryId(int categoryId);

	public Optional<Mobile> findByAvailableQuantity(int availableQuantity);

	public Optional<Mobile> findByModelNumber(String modelNumber);;

	public List<Mobile> findByIgnoreCaseCompanyNameContaining(String companyName);
	
	public List<Mobile> findByIgnoreCaseMobileNameContaining(String companyName);
	
	void deleteByCategory(Category category);

}