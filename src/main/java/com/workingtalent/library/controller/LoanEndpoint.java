package com.workingtalent.library.controller;

import com.workingtalent.library.dto.LoanDto;
import com.workingtalent.library.service.LoanService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan")
public class LoanEndpoint {

	@Autowired
	private LoanService loanService;
	
	@PostMapping("/save/reservation/{reservationId}/{copyId}")
	public void saveLoanFromReservation(@PathVariable long reservationId, @PathVariable long copyId) 
	{
		loanService.saveLoanFromReservation(reservationId, copyId);
	}

	@GetMapping("/save/{copyId}/{userId}")
	public void saveLoanDirectlyFromBook(@PathVariable long copyId, @PathVariable long userId){
		loanService.saveLoanDirectlyFromBook(userId, copyId);
	}
	
	@GetMapping("/all")
	public List<LoanDto> findAll() {
		return loanService.findAll();
	}
	
	@GetMapping("/{id}")
	public LoanDto findById(@PathVariable long id) {
		return loanService.findLoan(id);
	}
	
	@GetMapping("/user/{userid}")
	public List<LoanDto> findAllFromUser(@PathVariable long userid) {
		return loanService.findAllFromUser(userid);
	}
}
