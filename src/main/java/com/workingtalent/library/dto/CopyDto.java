package com.workingtalent.library.dto;

public class CopyDto {

	private long id;

    private int copyNumber;
    
	private boolean loaned;
	
    private boolean inactive;
	
	private String bookTitle;

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

	public String getBookTitle() {
		return bookTitle;
	}
	
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
}
