package com.workingtalent.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workingtalent.library.entities.Tag;
import com.workingtalent.library.repository.ITagRepository;

@Service
public class TagService {
	
	@Autowired
	private ITagRepository tagRepo;
	
	public void saveTag(Tag tag) {
		tagRepo.save(tag);
	}
	
	public Iterable<Tag> findAll() {
		return tagRepo.findAll();
	}

}
