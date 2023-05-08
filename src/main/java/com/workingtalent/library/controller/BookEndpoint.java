package com.workingtalent.library.controller;

import com.workingtalent.library.entities.Book;
import com.workingtalent.library.entities.Copy;
import com.workingtalent.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/{id}/copy/all")
    public Iterable<Copy> findAllCopies(@PathVariable long id){
        return bookService.findAllCopies(id);
    }

    @GetMapping("/count/{id}")
    public long getCopyCount(@PathVariable long id){
        return bookService.getCopyCount(id);
    }
}
