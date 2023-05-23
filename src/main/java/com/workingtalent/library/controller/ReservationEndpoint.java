package com.workingtalent.library.controller;

import com.workingtalent.library.dto.ReservationDto;
import com.workingtalent.library.entities.Reservation;
import com.workingtalent.library.entities.User;
import com.workingtalent.library.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

	@PutMapping("/update/cancel/{id}")
	public ResponseEntity<Reservation> cancelReservation(@RequestBody Reservation reservation){
		return new ResponseEntity<Reservation>(reservationService.cancelReservation(reservation.getId()), HttpStatus.OK);
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
