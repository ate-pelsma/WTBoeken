package com.workingtalent.library.service;

import com.workingtalent.library.entities.Book;
import com.workingtalent.library.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private IBookRepository bookRepo;

    public void saveBook(Book book){
        bookRepo.save(book);
    }

    public Iterable<Book> findAll() {
        return bookRepo.findAll();
    }
}
