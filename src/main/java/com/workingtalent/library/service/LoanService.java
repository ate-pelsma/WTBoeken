package com.workingtalent.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workingtalent.library.entities.Loan;
import com.workingtalent.library.repository.ILoanRepository;

@Service
public class LoanService {

	@Autowired
	private ILoanRepository loanRepo;
	
	public void saveLoan(Loan loan) 
	{
		System.out.println("We zitten in service...");
		loanRepo.save(loan);
	}
}
