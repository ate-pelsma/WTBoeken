package com.workingtalent.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.workingtalent.library.entities.Book;

@Component
public interface IBookRepository extends CrudRepository<Book, Long> {
}
