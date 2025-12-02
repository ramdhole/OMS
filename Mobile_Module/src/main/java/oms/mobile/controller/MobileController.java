package com.onlineapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineapplication.dto.MobileDTO;
import com.onlineapplication.entity.Mobile;
import com.onlineapplication.service.MobileService;

@RestController
@RequestMapping("/mobile")
//@CrossOrigin(origins = "http://localhost:4200")
public class MobileController {

	@Autowired
	private MobileService mobileService;

	@PostMapping("/add")
	public ResponseEntity<MobileDTO> addMobile(@RequestBody Mobile mobile){
		return new ResponseEntity<>(mobileService.addMobile(mobile), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{mobileId}")
	public ResponseEntity<MobileDTO> deleteMobilebyId(@PathVariable("mobileId") Long mobileId){
		System.out.println("Mobile Deleted");
		return new ResponseEntity<>(mobileService.deleteMobile(mobileId), HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<MobileDTO> updateMobile(@RequestBody Mobile mobiles) {
		return new ResponseEntity<>(mobileService.updateMobile(mobiles), HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<MobileDTO>> getMobiles() {
		return new ResponseEntity<>(mobileService.showAllMobiles(), HttpStatus.OK);

	}

	@GetMapping("/get/{mobileId}")
	public ResponseEntity<MobileDTO> showMobileById(@PathVariable("mobileId") Long mobileId){
		return new ResponseEntity<>(mobileService.showMobileById(mobileId), HttpStatus.OK);
	}

	@GetMapping("/get/ram/filter/{mobileRam}")
	public ResponseEntity<List<MobileDTO>> findMobileByRam(@PathVariable("mobileRam") Integer mobileRam) {
		return new ResponseEntity<>(mobileService.findByMobileRam(mobileRam), HttpStatus.OK);
	}

	@GetMapping("/get/price/filter/upper/{Price}")
	public ResponseEntity<List<MobileDTO>> findByMobileCostGreaterThan(@PathVariable("upperPrice") float price){
		return new ResponseEntity<>(mobileService.findByMobileCostGreaterThan(price), HttpStatus.OK);
	}

	@GetMapping("/get/price/filter/lower/{price}")
	public ResponseEntity<List<MobileDTO>> findByMobileCostLessThan(@PathVariable("lowerprice") float price){
		return new ResponseEntity<>(mobileService.findByMobileCostLessThan(price), HttpStatus.OK);
	}
	
	@GetMapping("/get/byCategoryName/{name}")
	public ResponseEntity<List<MobileDTO>> findByMobileCostLessThan(@PathVariable String name){
		return new ResponseEntity<>(mobileService.findMobilesBycategory(name), HttpStatus.OK);
	}
	
	@GetMapping("/searchMobile/{searchTxt}")
	public ResponseEntity<List<MobileDTO>> searchMobiles(@PathVariable String searchTxt){
		return new  ResponseEntity<>(mobileService.searchMob(searchTxt), HttpStatus.OK);
	}
	
}
