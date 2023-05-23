package com.workingtalent.library.service;

import com.workingtalent.library.dto.LoanDto;
import com.workingtalent.library.entities.Book;
import com.workingtalent.library.entities.Copy;
import com.workingtalent.library.entities.Loan;
import com.workingtalent.library.entities.Reservation;
import com.workingtalent.library.entities.ReservationStatus;
import com.workingtalent.library.entities.User;
import com.workingtalent.library.repository.ICopyRepository;
import com.workingtalent.library.repository.ILoanRepository;
import com.workingtalent.library.repository.IReservationRepository;
import com.workingtalent.library.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {

	@Autowired
	private ILoanRepository loanRepo;
	
	@Autowired
	private IUserRepository userRepo;
	
	@Autowired
	private ICopyRepository copyRepo;
	
	@Autowired
	private IReservationRepository resRepo;
	
	public void saveLoanFromReservation(long reservationid, long copyid) {
		Reservation reservation = resRepo.findById(reservationid).orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
		Copy copy = copyRepo.findById(copyid).orElseThrow(() -> new IllegalArgumentException("Copy not found"));
		List<Loan> copyLoans = copy.getLoans();
		User user = reservation.getUser();
		List<Loan> userLoans = user.getLoans();
		
		Loan loan = new Loan(copy, user);
		
		copyLoans.add(loan);
		copy.setLoans(copyLoans);
		copy.setLoaned(true);
		copyRepo.save(copy);

		userLoans.add(loan);
		user.setLoans(userLoans);
		userRepo.save(user);
		
		reservation.setStatus(ReservationStatus.ACCEPTED);
		
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
	
	public LoanDto findLoan(long id) {
		Loan loan = loanRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Loan not found"));
		return convertToDto(loan);
	}
	
	public List<LoanDto> findAll() {
		Iterable<Loan> loans = loanRepo.findAll();
		List<LoanDto> loanDtos = new ArrayList<>();
		for (Loan loan : loans) {
			loanDtos.add(convertToDto(loan));
		}
		return loanDtos;
	}
	
	public List<LoanDto> findAllFromUser(long userid) {
		User user = userRepo.findById(userid).orElseThrow(() -> new IllegalArgumentException("User not found"));
		List<Loan> loans = user.getLoans();
		List<LoanDto> loanDtos = new ArrayList<>();
		for (Loan loan : loans) {
			loanDtos.add(convertToDto(loan));
		}
		return loanDtos;
	}
	
	public void returnBook(long id) {
		Loan loan = loanRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Loan not found"));
		loan.setEndDate(LocalDate.now());
		Copy copy = loan.getCopy();
		copy.setLoaned(false);
		copyRepo.save(copy);
		loanRepo.save(loan);
	}
	
	private LoanDto convertToDto(Loan loan) {
		LoanDto loanDto = new LoanDto();
		Book book = loan.getCopy().getBook();
		loanDto.setId(loan.getId());
		loanDto.setStartDate(loan.getStartDate());
		loanDto.setEndDate(loan.getEndDate());
		loanDto.setUserid(loan.getUser().getId());
		loanDto.setUserName(loan.getUser().getName());
		loanDto.setCopyid(loan.getCopy().getId());
		loanDto.setCopyNumber(loan.getCopy().getCopyNumber());
		loanDto.setBookid(book.getId());
		loanDto.setBookTitle(book.getTitle());
		loanDto.setBookAuthor(book.getAuthor());
		loanDto.setBookIsbn(book.getIsbn());
		return loanDto;
	}

}
