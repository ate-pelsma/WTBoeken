package com.workingtalent.library.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workingtalent.library.entities.Book;
import com.workingtalent.library.service.BookService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/book")
public class BookEndpoint {

    @Autowired
    private BookService bookService;

    @PostMapping(value = "/save", consumes = "application/json")
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        return new ResponseEntity<Book>(bookService.saveBook(book), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody Book book){
        Book savedBook = bookService.findById(id).get();
        return new ResponseEntity<Book>(bookService.updateBook(savedBook, book), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable long id){
        bookService.deleteBook(id);
    }

    @GetMapping("/{id}")
    public Optional<Book> findBook(@PathVariable long id){
        return bookService.findById(id);
    }

    @GetMapping("/all")
    public Iterable<Book> findAll(){
        return bookService.findAll();
    }

    @GetMapping("/count/{id}")
    public long getCopyCount(@PathVariable long id){
        return bookService.getCopyCount(id);
    }
}
