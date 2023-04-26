package com.workingtalent.library.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.workingtalent.library.entities.Reservation;
import com.workingtalent.library.service.ReservationService;

@RestController
public class ReservationEndpoint {

	@Autowired
	private ReservationService reservationService;
	
	@GetMapping("/reservation/save/{userid}/{bookid}")
	public void saveReservation(@PathVariable long userid, @PathVariable long bookid)
	{
		Reservation reservation = new Reservation();
		reservation.setReqDate(LocalDateTime.now());
		reservationService.saveReservation(reservation, userid, bookid);
	}
	
}
