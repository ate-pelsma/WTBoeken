package com.workingtalent.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.workingtalent.library.entities.Book;
import com.workingtalent.library.entities.Copy;

@Component
public interface ICopyRepository extends JpaRepository<Copy, Long> {

    @Query("select count(c) from Copy c where c.book = ?1 and c.inactive != true")
    long bookCopyCount(Book book);
    Iterable<Copy> findCopyByBook(Book book);
}
