package com.workingtalent.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.workingtalent.library.entities.Reservation;

@Component
public interface IReservationRepository extends JpaRepository<Reservation, Long>{

}
