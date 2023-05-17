package com.workingtalent.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workingtalent.library.dto.ReservationDto;
import com.workingtalent.library.entities.Reservation;
import com.workingtalent.library.entities.User;
import com.workingtalent.library.service.ReservationService;

@RestController
@RequestMapping("/reservation")
public class ReservationEndpoint {
	@Autowired
	private ReservationService reservationService;
	
	@PostMapping("/save")
	public ResponseEntity<?> saveReservation(@RequestBody Long bookId, @AuthenticationPrincipal User user)
	{
		Reservation newReservation = reservationService.saveReservation(bookId, user);
		return ResponseEntity.ok(newReservation);
	}
	
	@GetMapping("/all")
	public List<ReservationDto> findAll() {
		return reservationService.findAll();
	}
	
	@GetMapping("/{id}")
	public ReservationDto findById(@PathVariable long id) {
		return reservationService.findReservation(id);
	}
}
