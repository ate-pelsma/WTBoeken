package com.workingtalent.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workingtalent.library.repository.IReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private IReservationRepository repo;
	
	public void opslaan() 
	{
		System.out.println("We zitten in de service.");
	}
	
}
