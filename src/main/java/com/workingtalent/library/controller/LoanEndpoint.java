package com.workingtalent.library.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.workingtalent.library.entities.Loan;
import com.workingtalent.library.service.LoanService;

@RestController
public class LoanEndpoint {

	@Autowired
	private LoanService loanService;
	
	@GetMapping("/loan/save/{userid}/{copyid}")
	public void saveLoan(@PathVariable long userid, @PathVariable long copyid) 
	{
		Loan loan = new Loan();
		loan.setStartDate(LocalDate.now());
		loanService.saveLoan(loan, userid, copyid);
	}
}
