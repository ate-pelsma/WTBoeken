package com.workingtalent.library.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workingtalent.library.entities.Copy;
import com.workingtalent.library.entities.Reservation;
import com.workingtalent.library.entities.User;
import com.workingtalent.library.service.ReservationService;

import dto.ReservationDto;


@RestController
@RequestMapping("/reservations")
public class ReservationEndpoint {
	@Autowired
	private ReservationService reservationService;
	
	@PostMapping("/save")
	public ResponseEntity<?> saveReservation(@RequestBody Long bookId, @AuthenticationPrincipal User user)
	{
		Reservation newReservation = reservationService.saveReservation(bookId, user);
		return ResponseEntity.ok(newReservation);
	}
	
    @GetMapping("/{id}")
    public Optional<ReservationDto> findById(@PathVariable long id){
    	return reservationService.findReservation(id).map(reservation -> {
    		ReservationDto reservationDto = new ReservationDto();
    		reservationDto.setId(reservation.getId());
    		reservationDto.setReqDate(reservation.getReqDate());
    		reservationDto.setBook(reservation.getBook());
    		reservationDto.setUsername(reservation.getUser().getName());
    		reservationDto.setUserId(reservation.getUser().getId());
    		return reservationDto;
    	});
    }
}
