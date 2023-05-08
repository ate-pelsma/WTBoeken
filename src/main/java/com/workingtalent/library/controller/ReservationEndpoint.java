package com.workingtalent.library.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping("/save")
	public ResponseEntity<?> saveReservation(@AuthenticationPrincipal User user)
	{
		Reservation newReservation = reservationService.saveReservation(user);
		return ResponseEntity.ok(newReservation);
	}
}
