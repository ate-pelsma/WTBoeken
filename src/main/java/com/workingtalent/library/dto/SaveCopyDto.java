package com.workingtalent.library.dto;

public class SaveCopyDto {

	private int copyNumber;

	private boolean loaned;

	private boolean inactive;

	private long bookId;

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

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

}
