package com.workingtalent.library.controller;

import com.workingtalent.library.entities.Book;
import com.workingtalent.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookEndpoint {

    @Autowired
    private BookService bookService;

    @GetMapping("/createbook/{title}")
    public void createBook(@PathVariable("title") String title){
        Book book = new Book();
        book.setTitle(title);
        bookService.createBook(book);
    }

    @GetMapping("/allbooks")
    public Iterable<Book> findAll(){
        return bookService.findAll();
    }
}
