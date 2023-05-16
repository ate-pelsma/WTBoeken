package com.workingtalent.library.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workingtalent.library.dto.ReservationDto;
import com.workingtalent.library.entities.Book;
import com.workingtalent.library.entities.Reservation;
import com.workingtalent.library.entities.User;
import com.workingtalent.library.repository.IReservationRepository;

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
	
	public List<ReservationDto> findAll() {
		Iterable<Reservation> reservations = reservationRepo.findAll();
		List<ReservationDto> reservationDtos = new ArrayList<>();
		for(Reservation reservation: reservations) {
			reservationDtos.add(convertToDto(reservation));
		}
		return reservationDtos;
	}
	
	public ReservationDto findReservation(long id) {
		Reservation reservation = reservationRepo.findById(id).get();
		ReservationDto reservationDto = convertToDto(reservation);
		return reservationDto;
	}

	private ReservationDto convertToDto(Reservation reservation) {
		ReservationDto reservationDto = new ReservationDto();
		reservationDto.setId(reservation.getId());
		reservationDto.setReqDate(reservation.getReqDate());
		reservationDto.setStatus(reservation.getStatus());
		reservationDto.setBookid(reservation.getBook().getId());
		reservationDto.setBookTitle(reservation.getBook().getTitle());
		reservationDto.setUserid(reservation.getUser().getId());
		reservationDto.setUserName(reservation.getUser().getName());
		return reservationDto;
	}
}