package com.workingtalent.library.controller;

import com.workingtalent.library.entities.Copy;
import com.workingtalent.library.service.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/copy")
public class CopyEndpoint {

    @Autowired
    CopyService copyService;

    @PostMapping("/save/{bookId}")
    public ResponseEntity<Copy> saveCopy(@RequestBody Copy copy, @PathVariable long bookId) {
        return new ResponseEntity<Copy>(copyService.saveCopy(copy, bookId), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Copy> updateCopy(@RequestBody Copy copy, @PathVariable long id){
        return new ResponseEntity<Copy>(copyService.updateCopy(copy, id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Copy> findAll(){
        return copyService.findAll();
    }
}
