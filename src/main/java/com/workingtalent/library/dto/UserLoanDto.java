package com.workingtalent.library.dto;

import java.time.LocalDate;

public class UserLoanDto {
    private String image;
    private String title;
    private String author;
    private String isbn;
    private LocalDate startDate;
    private LocalDate endDate;

    public UserLoanDto(){}

    public UserLoanDto(String image, String title, String author, String isbn, LocalDate startDate) {
        this.image = image;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.startDate = startDate;
    }

    public UserLoanDto(String image, String title, String author, String isbn, LocalDate startDate, LocalDate endDate) {
        this.image = image;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
