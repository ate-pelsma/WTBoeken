package com.workingtalent.library.controller;

import com.workingtalent.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookEndpoint {

    @Autowired
    private BookService bookService;

    @GetMapping("/book")
    public void createBook(){
        System.out.println("We are in the endpoint");
        bookService.createBook();
    }
}
