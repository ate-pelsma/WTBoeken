package com.workingtalent.library.service;

import com.workingtalent.library.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private IBookRepository bookRepo;

    public void createBook(){
        System.out.printf("We zitten in boek service");
    }
}
