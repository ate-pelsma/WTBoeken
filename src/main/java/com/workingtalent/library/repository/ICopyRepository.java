package com.workingtalent.library.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.workingtalent.library.entities.Book;
import com.workingtalent.library.entities.Copy;

@Component
public interface ICopyRepository extends CrudRepository<Copy, Long> {

    // @Query("select count(c) from Copy c where c.book = ?1 and c.inactive != true")
    long countByBookAndInactiveIsFalse(Book book);
    
    boolean existsByBookAndInactiveIsFalse(Book book);
    
    // Iterable<Copy> findCopyByBook(Book book);
    List<Copy> findByBook(Book book);
}
