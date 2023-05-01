package com.workingtalent.library.service;

import com.workingtalent.library.entities.Book;
import com.workingtalent.library.entities.Copy;
import com.workingtalent.library.repository.IBookRepository;
import com.workingtalent.library.repository.ICopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CopyService {

    @Autowired
    ICopyRepository copyRepository;

    @Autowired
    IBookRepository bookRepository;

    public Copy saveCopy(Copy copy, long id){
        Book book = bookRepository.findById(id).get();
        copy.setBook(book);
        copyRepository.save(copy);
        return copy;
    }

    public Iterable<Copy> findAll(){
        return copyRepository.findAll();
    }

    public Copy findById(long id) {
        Copy copy = copyRepository.findById(id).get();
        return copy;
    }

    public Copy updateCopy(Copy copy, long id){

        Copy existingCopy = findById(id);
        if(copy.isLoaned()){
            existingCopy.setLoaned(copy.isLoaned());
        }
        if(copy.isInactive()){
            existingCopy.setLoaned(copy.isInactive());
        }

        copyRepository.save(existingCopy);
        return existingCopy;
    }
}
