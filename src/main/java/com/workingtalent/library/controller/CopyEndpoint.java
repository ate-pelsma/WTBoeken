package com.workingtalent.library.controller;

import com.workingtalent.library.entities.Copy;
import com.workingtalent.library.service.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CopyEndpoint {

    @Autowired
    CopyService copyService;

    @PostMapping("copy/save")
    public void saveCopy(@RequestBody Copy copy) {
        copyService.saveCopy(copy);
    }

    @GetMapping("copy/all")
    public Iterable<Copy> findAll(){
        copyService.findAll();
    }
}
