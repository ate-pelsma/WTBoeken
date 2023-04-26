package com.workingtalent.library.service;

import com.workingtalent.library.entities.Copy;
import com.workingtalent.library.repository.ICopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CopyService {

    @Autowired
    ICopyRepository copyRepository;

    public void saveCopy(Copy copy){
        copyRepository.save(copy);
    }

    public Iterable<Copy> findAll(){
        return copyRepository.findAll();
    }
}
