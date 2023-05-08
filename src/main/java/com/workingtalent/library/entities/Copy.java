package com.workingtalent.library.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Copy {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int copyNumber;

    @JsonIgnore
    @ManyToOne(optional = false)
    private Book book;

    @OneToMany(mappedBy = "copy")
    private List<Loan> loans;
    
    private boolean loaned;
    private boolean inactive;

    public Copy(){

    }

    public Copy(int copyNumber, Book book){
        this.copyNumber = copyNumber;
        this.book = book;
    }

    public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

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
