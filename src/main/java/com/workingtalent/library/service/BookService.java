package com.workingtalent.library.service;

import com.workingtalent.library.entities.Book;
import com.workingtalent.library.repository.IBookRepository;
import com.workingtalent.library.repository.ICopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private IBookRepository bookRepo;

    @Autowired
    private ICopyRepository copyRepository;

    public Book saveBook(Book book){
        bookRepo.save(book);
        return book;
    }

    public Book updateBook(Book savedBook, Book book){

        if(book.getTitle() != null){
            savedBook.setTitle(book.getTitle());
        }
        if(book.getImage() != null){
            savedBook.setImage(book.getImage());
        }
        if(book.getISBN() != null){
            savedBook.setISBN(book.getISBN());
        }

        bookRepo.save(savedBook);
        return savedBook;
    }

    public Iterable<Book> findAll() {
        return bookRepo.findAll();
    }

    public Optional<Book> findById(long id) {
        return bookRepo.findById(id);
    }

    public long getCopyCount(long id) {
        Book book = bookRepo.findById(id).get();
        return copyRepository.bookCopyCount(book);
    }
}
