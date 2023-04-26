package com.workingtalent.library.service;

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
	private IBookRepository bookRepo;
	
	@Autowired
	private IUserRepository userRepo;
	
	public void saveReservation(Reservation reservation, long userid, long bookid) 
	{
		reservationRepo.save(reservation);
		Book book = bookRepo.findById(bookid).get();
		User user = userRepo.findById(userid).get();
		List<Reservation> bookReservations = book.getReservations();
		List<Reservation> userReservations = user.getReservations();
		bookReservations.add(reservation);
		userReservations.add(reservation);
		book.setReservations(bookReservations);
		user.setReservations(userReservations);
		bookRepo.save(book);
		userRepo.save(user);
	}
	
}
