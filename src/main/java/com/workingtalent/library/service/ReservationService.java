package com.workingtalent.library.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workingtalent.library.entities.Book;
import com.workingtalent.library.entities.Reservation;
import com.workingtalent.library.entities.User;
import com.workingtalent.library.repository.IBookRepository;
import com.workingtalent.library.repository.IReservationRepository;
import com.workingtalent.library.repository.IUserRepository;

@Service
public class ReservationService {

	@Autowired
	private IReservationRepository reservationRepo;
	
	@Autowired
	private BookService bookService;
	
	public Reservation saveReservation(Long bookId, User user) 
	{
		Reservation reservation = new Reservation();
		Optional<Book> bookOpt = bookService.findById(bookId);
		System.out.println(bookOpt);
		if(bookOpt.isPresent()) {
			Book book = bookOpt.get();
			reservation.setBook(book);
			reservation.setUser(user);
			reservation.setReqDate(LocalDate.now());
			return reservationRepo.save(reservation);
		} else {
			throw new IllegalArgumentException("Book not found");
		}
	}
	
	public Optional<Reservation> findReservation(long id) {
		return reservationRepo.findById(id);
	}
}