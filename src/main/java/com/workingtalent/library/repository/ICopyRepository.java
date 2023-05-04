package com.workingtalent.library.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.workingtalent.library.entities.Book;
import com.workingtalent.library.entities.Copy;

@Component
public interface ICopyRepository extends CrudRepository<Copy, Long> {

    @Query("select count(c) from Copy c where c.book = ?1 and c.inactive != true")
    long bookCopyCount(Book book);
}
