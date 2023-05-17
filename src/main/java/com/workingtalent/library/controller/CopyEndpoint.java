package com.workingtalent.library.controller;

import com.workingtalent.library.entities.Copy;
import com.workingtalent.library.service.CopyService;
import com.workingtalent.library.dto.CopyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/{id}")
    public Optional<CopyDto> findById(@PathVariable long id){
        return copyService.findAndCreateCopyDto(id);
    }
}
