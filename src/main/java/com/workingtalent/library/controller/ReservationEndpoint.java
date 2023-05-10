package com.workingtalent.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workingtalent.library.entities.Reservation;
import com.workingtalent.library.entities.User;
import com.workingtalent.library.service.ReservationService;


@RestController
@RequestMapping("/reservations")
public class ReservationEndpoint {
	@Autowired
	private ReservationService reservationService;
	
	@PostMapping("/save")
	public ResponseEntity<?> saveReservation(@RequestBody Long bookId, @AuthenticationPrincipal User user)
	{
		System.out.println(bookId);
		Reservation newReservation = reservationService.saveReservation(bookId, user);
		return ResponseEntity.ok(newReservation);
	}
}
