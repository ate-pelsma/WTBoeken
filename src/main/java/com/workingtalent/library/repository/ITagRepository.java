package com.workingtalent.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.workingtalent.library.entities.Tag;

@Component
public interface ITagRepository extends CrudRepository<Tag, Long> {

}
