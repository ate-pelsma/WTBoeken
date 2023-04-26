package com.workingtalent.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.workingtalent.library.entities.Loan;
import com.workingtalent.library.service.LoanService;

@RestController
public class LoanEndpoint {

	@Autowired
	private LoanService loanService;
	
	@GetMapping("/loan/save/{loan}")
	public void saveLoan(@RequestBody Loan loan) 
	{
		loanService.saveLoan(loan);
	}
}
