package com.workingtalent.library.dto;

import java.util.List;

public class CopyDto {

    private long id;

    private long copyId;

    private int copyNumber;

    private boolean inactive;

    private long bookId;

    private String bookTitle;
    private String bookAuthor;
    private String bookIsbn;
    private String bookImage;
    private String activeLoanName;

    private List<LoanDto> loanList;

    public CopyDto() {
    }

    public CopyDto(long copyId, int copyNumber, boolean inactive, long bookId, String bookTitle, String bookAuthor, String bookIsbn, String bookImage, List<LoanDto> loanList) {
        this.copyId = copyId;
        this.copyNumber = copyNumber;
        this.inactive = inactive;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookIsbn = bookIsbn;
        this.bookImage = bookImage;
        this.loanList = loanList;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public List<LoanDto> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<LoanDto> loanList) {
        this.loanList = loanList;
    }

    public String getActiveLoanName() {
        return activeLoanName;
    }

    public void setActiveLoanName(String activeLoanName) {
        this.activeLoanName = activeLoanName;
    }

    public long getCopyId() {
        return copyId;
    }

    public void setCopyId(long copyId) {
        this.copyId = copyId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
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
