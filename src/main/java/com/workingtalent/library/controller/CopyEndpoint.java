package com.workingtalent.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workingtalent.library.entities.Copy;
import com.workingtalent.library.service.CopyService;

@RestController
@RequestMapping("/copy")
public class CopyEndpoint {

    @Autowired
    CopyService copyService;

    @PostMapping("/save/{bookId}")
    public ResponseEntity<Copy> saveCopy(@RequestBody Copy copy, @PathVariable long bookId) {
        return new ResponseEntity<>(copyService.saveCopy(copy, bookId), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Copy> updateCopy(@RequestBody Copy copy, @PathVariable long id){
        return new ResponseEntity<>(copyService.updateCopy(copy, id), HttpStatus.OK);
    }

    @PutMapping("/inactive/{id}")
    public ResponseEntity<Copy> toggleActiveCopy(@PathVariable long id){
        return new ResponseEntity<Copy>(copyService.toggleActiveCopy(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCopy(@PathVariable long id){
        copyService.deleteCopy(id);
    }

    @GetMapping("/all")
    public Iterable<Copy> findAll(){
        return copyService.findAll();
    }
}
