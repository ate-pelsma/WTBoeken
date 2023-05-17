package com.workingtalent.library.service;

import com.workingtalent.library.entities.Copy;
import com.workingtalent.library.entities.Loan;
import com.workingtalent.library.entities.User;
import com.workingtalent.library.repository.ICopyRepository;
import com.workingtalent.library.repository.ILoanRepository;
import com.workingtalent.library.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

	@Autowired
	private ILoanRepository loanRepo;
	
	@Autowired
	private IUserRepository userRepo;
	
	@Autowired
	private ICopyRepository copyRepo;

	public void saveLoan(Loan loan, long userid, long copyid){
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

		loan.setCopy(copy);
		loan.setUser(user);
		loanRepo.save(loan);
	}

	public void saveLoanDirectlyFromBook(long userid, long copyid)
	{
		
		Copy copy = copyRepo.findById(copyid).get();
		List<Loan> copyLoans = copy.getLoans();
		User user = userRepo.findById(userid).get();
		List<Loan> userLoans = user.getLoans();

		Loan loan = new Loan(copy, user);

		copyLoans.add(loan);
		copy.setLoans(copyLoans);
		copy.setLoaned(true);
		copyRepo.save(copy);

		userLoans.add(loan);
		user.setLoans(userLoans);
		userRepo.save(user);

		loanRepo.save(loan);
	}
}
