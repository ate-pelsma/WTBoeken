package com.workingtalent.library.service;

import com.workingtalent.library.dto.CopyDto;
import com.workingtalent.library.entities.Book;
import com.workingtalent.library.entities.Copy;
import com.workingtalent.library.repository.IBookRepository;
import com.workingtalent.library.repository.ICopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<CopyDto> findAndCreateCopyDto(long id) {
        return findCopy(id).map(copy -> {
            CopyDto copyDto = new CopyDto();
            copyDto.setCopyId(copy.getId());
            copyDto.setCopyNumber(copy.getCopyNumber());
            copyDto.setBookId(copy.getBook().getId());
            copyDto.setBookTitle(copy.getBook().getTitle());
            copyDto.setBookAuthor(copy.getBook().getAuthor());
            copyDto.setBookIsbn(copy.getBook().getIsbn());

            if(copy.isLoaned()){
                String activeUser = copy.getLoans().get(0).getUser().getName();
                copyDto.setActiveLoanName(activeUser);
            } else {
                copyDto.setActiveLoanName("");
            }

            return copyDto;
        });
    }
}
