package com.workingtalent.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.workingtalent.library.entities.Loan;

@Component
public interface ILoanRepository extends CrudRepository<Loan, Long>{

}
