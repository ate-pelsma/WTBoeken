package com.workingtalent.library.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workingtalent.library.entities.Book;
import com.workingtalent.library.entities.Reservation;
import com.workingtalent.library.entities.User;
import com.workingtalent.library.service.ReservationService;


@RestController
@RequestMapping("/reservations")
public class ReservationEndpoint {

	@Autowired
	private ReservationService reservationService;
	
	@PostMapping("/save")
	public ResponseEntity<?> saveReservation(@AuthenticationPrincipal User user)
	{
		System.out.println(user);
		Reservation newReservation = reservationService.save(user);
		return ResponseEntity.ok(newReservation);
	}
}
