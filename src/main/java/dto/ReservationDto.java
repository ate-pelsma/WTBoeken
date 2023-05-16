package dto;

import java.time.LocalDate;

import com.workingtalent.library.entities.Book;
import com.workingtalent.library.entities.ReservationStatus;
import com.workingtalent.library.entities.User;

public class ReservationDto {
	
	private long id;
	
	private LocalDate reqDate;
	
	private int copyNumber;
	
	private ReservationStatus status;
	
	private String userName;
	
	private Long userId;
	
	public String getUsername() {
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getReqDate() {
		return reqDate;
	}

	public void setReqDate(LocalDate reqDate) {
		this.reqDate = reqDate;
	}

	public int getCopyNumber() {
		return copyNumber;
	}

	public void setCopyNumber(int copyNumber) {
		this.copyNumber = copyNumber;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	private Book book;
}
