package com.workingtalent.library.service;

import com.workingtalent.library.controller.AuthenticationEndPoint;
import com.workingtalent.library.dto.UserLoanDto;
import com.workingtalent.library.dto.UserReservationDto;
import com.workingtalent.library.entities.*;
import com.workingtalent.library.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.workingtalent.library.dto.UserDto;
import com.workingtalent.library.entities.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	
	@Autowired
	private IUserRepository userRepo;
	
	@Autowired
	public AuthenticationEndPoint autenticationEndPoint;

	public Iterable<User> findAll() {
		return userRepo.findAll();
	}
	
	public Optional<User> findUser(long id) {
		return userRepo.findById(id);
	}

	public void saveUser(User user) {
		user.setPassword(autenticationEndPoint.passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
	}
	
	public void updateUser(UserDto user, User oldUser) {
		System.out.println(user.getPassword());
		if (!user.getName().equals("")) oldUser.setName((user.getName()));
		if (!user.getPassword().equals("")) oldUser.setPassword(autenticationEndPoint.passwordEncoder.encode(user.getPassword()));
		if (!user.getUsername().equals("")) oldUser.setUsername((user.getUsername()));
		userRepo.save(oldUser);
	}
	
	public void updateUserAdmin(User user, User oldUser) {
		if (!user.getName().equals("")) oldUser.setName(user.getName());
		if (!user.getPassword().equals("")) oldUser.setPassword(autenticationEndPoint.passwordEncoder.encode(user.getPassword()));
		if (!user.getUsername().equals("")) oldUser.setUsername(user.getUsername());
		if (!user.getPermissions().equals("")) oldUser.setPermissions(user.getPermissions());
		userRepo.save(oldUser);
	}

	public User inactiveUser(User user) {
		user.setName("");
		//user.setUsername("");
		user.setPassword("");
		user.setPermissions("ROLE_USER");
		userRepo.save(user);
		return user;
	}

	public Optional<User> findById(long id) {
		return userRepo.findById(id);
	}

	public UserDto findSelf(User user) {
		UserDto userDto = convertToDto(user);
		return userDto;
	}
	
	public UserDto convertToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setPassword(user.getPassword());
		userDto.setUsername(user.getUsername());
		return userDto;
	}

    public List<UserReservationDto> getPendingReservationsForUser(User user) {
		User realUser = userRepo.findById(user.getId()).get();
		Iterable<Reservation> reservationList = realUser.getReservations();
		List<UserReservationDto> userReservations = new ArrayList<>();
		for(Reservation res : reservationList){
			if(res.getStatus() == ReservationStatus.PENDING){
				Book book = res.getBook();
				UserReservationDto reservationDto = new UserReservationDto(res.getId(), res.getReqDate(), book.getTitle(), book.getAuthor(), book.getIsbn(), book.getId());
				userReservations.add(reservationDto);
			}
		}
		return userReservations;
    }

	public List<UserLoanDto> getLoansForUser(User user) {
		User realUser = userRepo.findById(user.getId()).get();
		Iterable<Loan> loanList = realUser.getLoans();
		List<UserLoanDto> userLoans = new ArrayList<>();
		for(Loan loan : loanList){
			Book book = loan.getCopy().getBook();
			UserLoanDto loanDto;
			if(loan.getEndDate() == null){
				loanDto = new UserLoanDto(book.getImage(), book.getTitle(), book.getAuthor(), book.getIsbn(), loan.getStartDate());
			} else {
				loanDto = new UserLoanDto(book.getImage(), book.getTitle(), book.getAuthor(), book.getIsbn(), loan.getStartDate(), loan.getEndDate());
			}
			userLoans.add(loanDto);
		}
		return userLoans;
	}
}