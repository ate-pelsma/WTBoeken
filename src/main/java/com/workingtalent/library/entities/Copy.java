package com.workingtalent.library.entities;

import jakarta.persistence.*;

@Entity
public class Copy {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int copyNumber;

    @ManyToOne
    private Book book;

    private boolean loaned;
    private boolean inactive;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(int copyNumber) {
        this.copyNumber = copyNumber;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public boolean isLoaned() {
        return loaned;
    }

    public void setLoaned(boolean loaned) {
        this.loaned = loaned;
    }

    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }
}
