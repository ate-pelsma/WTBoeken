package com.workingtalent.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.workingtalent.library.entities.Tag;
import com.workingtalent.library.service.TagService;

@RestController
public class TagEndpoint {
	
	@Autowired
	private TagService tagService;
	
	@PostMapping("/tag/save")
	public void saveTag(@RequestBody Tag tag) {
		tagService.saveTag(tag);
	}
	
	@GetMapping("/tag/all")
	public Iterable<Tag> findAll() {
		return tagService.findAll();
	}

}
