package com.workingtalent.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.workingtalent.library.entities.Reservation;

@Component
public interface IReservationRepository extends CrudRepository<Reservation, Long>{

}
