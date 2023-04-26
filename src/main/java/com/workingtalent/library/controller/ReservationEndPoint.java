package com.workingtalent.library.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.workingtalent.library.entities.Reservation;
import com.workingtalent.library.service.ReservationService;

@RestController
public class ReservationEndPoint {

	@Autowired
	private ReservationService service;
	
	@GetMapping("/ReservationAanmaken/{status}")
	public void maakEenNieuwe(@PathVariable("status") int status) 
	{
		Reservation reservering = new Reservation();
		reservering.setReqDate(LocalDateTime.now());
		reservering.setStatus(status);
		service.opslaan(reservering);
	}
	
}
