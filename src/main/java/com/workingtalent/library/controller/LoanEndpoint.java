package com.workingtalent.library.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workingtalent.library.entities.Copy;
import com.workingtalent.library.entities.Loan;
import com.workingtalent.library.entities.User;
import com.workingtalent.library.service.LoanService;

@RestController
@RequestMapping("/loan")
public class LoanEndpoint {

	@Autowired
	private LoanService loanService;
	
	@PostMapping("/save")
	public void saveLoan(@RequestBody Copy copy, @RequestBody User user) 
	{
		Loan loan = new Loan();
		loan.setStartDate(LocalDate.now());
		loanService.saveLoan(loan, user.getId(), copy.getId());
	}
}
