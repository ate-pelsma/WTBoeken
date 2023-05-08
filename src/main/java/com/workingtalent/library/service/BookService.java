package com.workingtalent.library.service;

import com.workingtalent.library.entities.Book;
import com.workingtalent.library.entities.Copy;
import com.workingtalent.library.repository.IBookRepository;
import com.workingtalent.library.repository.ICopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private IBookRepository bookRepo;

    @Autowired
    private ICopyRepository copyRepository;

    public Book saveBook(Book book){
        bookRepo.save(book);
        createCopiesForNewBook(book);
        return book;
    }

    public void createCopiesForNewBook(Book book){
        for(int i = 1; i <= book.getStock(); i++) {
            Copy copy = new Copy(i, book);
            copyRepository.save(copy);
            addCopyToBookList(copy, book);
        }
    }

    private void addCopyToBookList(Copy copy, Book book) {
        try{
            List<Copy> currentList = book.getCopies();
            currentList.add(copy);
            book.setCopies(currentList);
        } catch (NullPointerException e) {
            List<Copy> newList = new ArrayList<>();
            newList.add(copy);
        }
    }

    public Book updateBook(Book savedBook, Book book){

        if(book.getTitle() != null){
            savedBook.setTitle(book.getTitle());
        }
        if(book.getImage() != null){
            savedBook.setImage(book.getImage());
        }
        if(book.getIsbn() != null){
            savedBook.setIsbn(book.getIsbn());
        }
        if(book.getAuthor() != null){
            savedBook.setIsbn(book.getAuthor());
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

    public void deleteBook(long id) {
        bookRepo.deleteById(id);
    }

    public Iterable<Copy> findAllCopies(long id) {
        Book book = bookRepo.findById(id).get();
        return copyRepository.findCopyByBook(book);
    }
}
