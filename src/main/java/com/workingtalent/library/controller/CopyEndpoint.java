package com.workingtalent.library.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.workingtalent.library.dto.CopyDto;
import com.workingtalent.library.dto.SaveCopyDto;
import com.workingtalent.library.entities.Copy;
import com.workingtalent.library.service.CopyService;

@RestController
@RequestMapping("/copy")
public class CopyEndpoint {

    @Autowired
    CopyService copyService;

    @PostMapping("/save")
    public Copy saveCopy(@RequestBody SaveCopyDto saveCopyDto) {
    	Copy copy = new Copy();
    	copy.setCopyNumber(saveCopyDto.getCopyNumber());
    	copy.setInactive(saveCopyDto.isInactive());
    	copy.setLoaned(saveCopyDto.isLoaned());
    	
    	return copyService.saveCopy(copy, saveCopyDto.getBookId());
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
    public Iterable<CopyDto> findAll(){
//		OOP     	
//    	Iterable<Copy> copieen = copyService.findAll();
//        
//        List<CopyDto> copyDtos = new ArrayList<>();
//        
//        for (Copy copy : copieen) {
//        	CopyDto copyDto = new CopyDto();
//        	copyDto.setId(copy.getId());
//        	copyDto.setCopyNumber(copy.getCopyNumber());
//        	copyDto.setInactive(copy.isInactive());
//        	copyDto.setLoaned(copy.isLoaned());
//        	copyDto.setBookTitle(copy.getBook().getTitle());
//        	
//        	copyDtos.add(copyDto);
//        }
//        
//        return copyDtos;
        
    	// Functional programming manier
        return copyService.findAll().stream().map(copy -> {
        	CopyDto copyDto = new CopyDto();
        	copyDto.setId(copy.getId());
        	copyDto.setCopyNumber(copy.getCopyNumber());
        	copyDto.setInactive(copy.isInactive());
        	copyDto.setLoaned(copy.isLoaned());
        	copyDto.setBookTitle(copy.getBook().getTitle());
        	return copyDto;
        }).toList();
    }
}
