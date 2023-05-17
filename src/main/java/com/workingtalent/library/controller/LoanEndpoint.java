package com.workingtalent.library.controller;

import com.workingtalent.library.entities.Copy;
import com.workingtalent.library.entities.Loan;
import com.workingtalent.library.entities.User;
import com.workingtalent.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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

	@GetMapping("/save/{copyId}/{userId}")
	public void saveLoanDirectlyFromBook(@PathVariable long copyId, @PathVariable long userId){
		loanService.saveLoanDirectlyFromBook(userId, copyId);
	}
}
