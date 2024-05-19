package com.onlineapplication.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineapplication.dto.MobileDTO;
import com.onlineapplication.entity.Category;
import com.onlineapplication.entity.Mobile;
import com.onlineapplication.exception.CategoryException;
import com.onlineapplication.exception.MobileNotFoundException;
import com.onlineapplication.exception.MobileNotSavedException;
import com.onlineapplication.exception.MobilesException;
import com.onlineapplication.repository.CategoryRepository;
import com.onlineapplication.repository.MobileRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MobileServiceImpl implements MobileService {

	@Autowired
	private MobileRepository mobileRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public MobileDTO addMobile(Mobile mobile) {
		System.out.println(mobile);
		Category findByCategoryName = categoryRepository.findByCategoryName(mobile.getCategory().getCategoryName());
		if (findByCategoryName == null) {
			
			// defaults category set
			
			Category category = categoryRepository.save(mobile.getCategory());
			if(category == null)
				throw new CategoryException("CategoryName " + mobile.getCategory().getCategoryName() + " not exists !");
			mobile.setCategory(category);
		} else {
			mobile.setCategory(findByCategoryName);
		}
		
//		Checking if mobile is already available then increase  the quantity
		Optional<Mobile> oMobile = mobileRepository.findByModelNumber(mobile.getModelNumber());
		
		if(oMobile.isPresent()) {
			mobile.setMobileId(oMobile.get().getMobileId());
			mobile.setAvailableQuantity(mobile.getAvailableQuantity()+oMobile.get().getAvailableQuantity());
		}
		
		Mobile savedMobile = mobileRepository.save(mobile);
		if(savedMobile == null)
			throw new MobileNotSavedException("Mobile not saved due to internal problem");
		
		log.info("Mobile Added Successfully !!");
		return modelMapper.map(savedMobile, MobileDTO.class);
		
		
	}

	@Override
	public MobileDTO updateMobile(Mobile mobile) {

		Category findByCategoryName = categoryRepository.findByCategoryName(mobile.getCategory().getCategoryName());

		if (findByCategoryName == null) {
			throw new CategoryException("CategoryName " + mobile.getCategory().getCategoryName() + " not exists !");

		}

		Optional<Mobile> optMobiles = this.mobileRepository.findById(mobile.getMobileId());
		if (!optMobiles.isPresent())
			throw new MobilesException("Mobile id " + mobile.getMobileId() + " does not exists to update !");

		log.info("Mobile Updated Successfully !!");
		return modelMapper.map(mobileRepository.save(mobile), MobileDTO.class);

	}

	@Override
	public MobileDTO deleteMobile(Long mobileId) {

		Optional<Mobile> optMobile = this.mobileRepository.findById(mobileId);
		if (!optMobile.isPresent())
			throw new MobileNotFoundException("Mobiles id " + mobileId + " does not exists to delete !");
		this.mobileRepository.deleteByCategory(optMobile.get().getCategory());;		

		log.info("Mobile Deleted Successfully !!");
		return modelMapper.map(optMobile.get(), MobileDTO.class);

	}

	@Override
	public MobileDTO showMobileById(Long mobileId) {

		Optional<Mobile> optMobiles = mobileRepository.findById(mobileId);
		if (!optMobiles.isPresent()) {
			throw new MobileNotFoundException("Mobile id not found: " + mobileId);
		}
		
		log.info("Mobile Displayed Successfully  with id !!"+mobileId);
		return modelMapper.map(optMobiles.get(), MobileDTO.class);

	}

	@Override
	public List<MobileDTO> showAllMobiles() {

		List<Mobile> mobileList = mobileRepository.findAll();
		if(mobileList.isEmpty())
			throw new MobileNotFoundException("No mobile found");
		
		log.info("All Mobiles Displayed Successfully !!");
		return Arrays.asList(modelMapper.map(mobileList, MobileDTO[].class));
	}

	@Override
	public List<MobileDTO> findByMobileRam(int mobileRam) {

		List<Mobile> mobileList = mobileRepository.findByMobileRam(mobileRam);
		if(mobileList.isEmpty())
			throw new MobileNotFoundException("No mobile found with RAM= "+mobileRam);
		
		log.info("Mobile Found Successfully with RAM "+mobileRam);
		return Arrays.asList(modelMapper.map(mobileList, MobileDTO[].class));
	}

	@Override
	public List<MobileDTO> findByMobileCostGreaterThan(Float price) {

		List<Mobile> mobileList = mobileRepository.findByMobileCostGreaterThan(price);
		if(mobileList.isEmpty())
			throw new MobileNotFoundException("No mobile found with price less than "+price);
		
		log.info("Successfully Found Mobile with Cost More Than "+price);
		return Arrays.asList(modelMapper.map(mobileList, MobileDTO[].class));
		
	}

	@Override
	public List<MobileDTO> findByMobileCostLessThan(Float price) {

		List<Mobile> mobileList = mobileRepository.findByMobileCostLessThan(price);
		if(mobileList.isEmpty())
			throw new MobileNotFoundException("No mobile found with price greater than "+price);
		
		log.info("Successfully Found Mobile with Cost Less Than "+price);
		return Arrays.asList(modelMapper.map(mobileList, MobileDTO[].class));
	}
	
	@Override
	public List<MobileDTO> findMobilesBycategory(String categoryName){
		
		List<Mobile> list = mobileRepository.findByCategoryCategoryName(categoryName);
		if(list == null)
			throw new MobileNotFoundException("No mobile found with category "+categoryName+" !");
		
		log.info("Successfully Found Mobile with category "+categoryName);
		return Arrays.asList(modelMapper.map(list, MobileDTO[].class));
	}

	@Override
	public List<MobileDTO> searchMob(String searchTxt) {
		
		List<Mobile> mobiles = new ArrayList<>();
		
		mobiles = mobileRepository.findByIgnoreCaseCompanyNameContaining(searchTxt);
		
		if(mobiles.isEmpty())
			mobiles = mobileRepository.findByIgnoreCaseMobileNameContaining(searchTxt);
		if(mobiles.isEmpty())
			throw new MobileNotFoundException("No Mobiles found !!");
		
		log.info("Successfully Found Mobiles !!");		
		return Arrays.asList(modelMapper.map(mobiles, MobileDTO[].class));
	}

}
