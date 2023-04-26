package com.workingtalent.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workingtalent.library.service.ReservationService;

@RestController
public class ReservationEndPoint {

	@Autowired
	private ReservationService service;
	
	@GetMapping("/ReservationAanmaken")
	public void maakEenNieuwe() 
	{
		System.out.println("We zitten in het endpoint.");
		service.opslaan();
	}
	
}
