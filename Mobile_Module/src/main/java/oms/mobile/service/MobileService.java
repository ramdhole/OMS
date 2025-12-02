package oms.mobile.service;

import java.util.List;

import oms.mobile.dto.MobileDTO;
import oms.mobile.entity.Category;
import oms.mobile.entity.Mobile;

public interface MobileService {
	
	public MobileDTO addMobile(Mobile mobile) ;
	
	public MobileDTO updateMobile(Mobile mobile);
	
	public MobileDTO deleteMobile(Long mobileId);
	
	public MobileDTO showMobileById(Long mobileId);
	
	public List<MobileDTO> showAllMobiles();
	
	public List<MobileDTO> findByMobileRam(int mobileRam);
	
	public List<MobileDTO> findByMobileCostLessThan(Float price);

	public List<MobileDTO> findByMobileCostGreaterThan(Float price);

	public List<MobileDTO> findMobilesBycategory(String categoryName);

	public List<MobileDTO> searchMob(String searchTxt);
	
	
	
	
	
	

}
