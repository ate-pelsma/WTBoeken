package com.workingtalent.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workingtalent.library.entities.Copy;
import com.workingtalent.library.entities.Loan;
import com.workingtalent.library.entities.User;
import com.workingtalent.library.repository.ICopyRepository;
import com.workingtalent.library.repository.ILoanRepository;
import com.workingtalent.library.repository.IUserRepository;

@Service
public class LoanService {

	@Autowired
	private ILoanRepository loanRepo;
	
	@Autowired
	private IUserRepository userRepo;
	
	@Autowired
	private ICopyRepository copyRepo;
	
	public void saveLoan(Loan loan, long userid, long copyid) 
	{
		loanRepo.save(loan);
		
		Copy copy = copyRepo.findById(copyid).get();
		List<Loan> copyLoans = copy.getLoans();
		copyLoans.add(loan);
		copy.setLoans(copyLoans);
		copyRepo.save(copy);
		
		User user = userRepo.findById(userid).get();
		List<Loan> userLoans = user.getLoans();
		userLoans.add(loan);
		user.setLoans(userLoans);
		userRepo.save(user);
		

	}
}
