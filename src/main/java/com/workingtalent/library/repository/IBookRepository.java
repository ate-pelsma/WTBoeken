package com.workingtalent.library.repository;

import com.workingtalent.library.entities.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface IBookRepository extends CrudRepository<Book, Long> {
}
