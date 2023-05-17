package com.workingtalent.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workingtalent.library.entities.Book;
import com.workingtalent.library.entities.Copy;
import com.workingtalent.library.repository.IBookRepository;
import com.workingtalent.library.repository.ICopyRepository;

import java.util.Optional;

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

    public Copy toggleActiveCopy(long id) {
        Copy copy = copyRepository.findById(id).get();
        copy.setInactive(!copy.isInactive());
        copyRepository.save(copy);
        return copy;
    }

    public Copy updateCopy(Copy copy, long id){

        Copy existingCopy = findById(id);
        existingCopy.setInactive(copy.isInactive());
        copyRepository.save(existingCopy);
        return existingCopy;
    }

    public void deleteCopy(long id) {
        copyRepository.deleteById(id);
    }

    public Optional<Copy> findCopy(long id) {
        return copyRepository.findById(id);
    }
}
