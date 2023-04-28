package com.workingtalent.library.controller;

import com.workingtalent.library.entities.Book;
import com.workingtalent.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
public class BookEndpoint {

    @Autowired
    private BookService bookService;

    @PostMapping("/book/save")
    public void saveBook(@RequestBody Book book){
        bookService.saveBook(book);
    }

    @GetMapping("/book/all")
    public Iterable<Book> findAll(){
        return bookService.findAll();
    }
}
