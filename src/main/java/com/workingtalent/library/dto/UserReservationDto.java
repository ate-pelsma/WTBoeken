package com.workingtalent.library.dto;

import java.time.LocalDate;

public class UserReservationDto {

    private long reservationId;
    private LocalDate date;
    private String bookTitle;
    private String bookAuthor;
    private String bookIsbn;
    private long bookId;

    public UserReservationDto(){}

    public UserReservationDto(long id, LocalDate date, String title, String author, String isbn, long bookId){
        reservationId = id;
        this.date = date;
        bookTitle = title;
        bookAuthor = author;
        bookIsbn = isbn;
        this.bookId = bookId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }
}
