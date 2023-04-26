package com.workingtalent.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workingtalent.library.entities.Reservation;
import com.workingtalent.library.repository.IReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private IReservationRepository repo;
	
	public void opslaan(Reservation reservation) 
	{
		repo.save(reservation);
	}
	
}
