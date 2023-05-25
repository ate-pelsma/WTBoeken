package com.workingtalent.library.controller;

import com.workingtalent.library.dto.UserDto;
import com.workingtalent.library.dto.UserLoanDto;
import com.workingtalent.library.dto.UserReservationDto;
import com.workingtalent.library.entities.User;
import com.workingtalent.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(maxAge=3600)
@RestController
@RequestMapping("/user")
public class UserEndpoint {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	public Iterable<User> findAll() {
		return userService.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<User> findById(@PathVariable long id) {
		return userService.findById(id);
	}
	
	@GetMapping("/self")
	public UserDto findSelf(@AuthenticationPrincipal User user) {
		return userService.findSelf(user);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Object> saveUser(@RequestBody User user) {
		userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/admin/{userid}")
	public void updateUserAdmin(@PathVariable long userid, @RequestBody User user) {
		User oldUser = userService.findUser(userid).get();
		userService.updateUserAdmin(user, oldUser);
	}
	
	@PutMapping("/update")
	public void updateUser(@RequestBody UserDto userDto) {
		User oldUser = userService.findUser(userDto.getId()).get();
		userService.updateUser(userDto, oldUser);
	}
	
	@GetMapping("/inactive/{userid}")
	public ResponseEntity<?> inactiveUser(@PathVariable long userid, @AuthenticationPrincipal User loggedInUser) {
		User user = userService.findUser(userid).get();
		if(loggedInUser.getId() != user.getId()){
			return ResponseEntity.ok(userService.inactiveUser(user));
		} else throw new IllegalArgumentException("You can't inactivate your own account");
	}

	@GetMapping("/reservations")
	public List<UserReservationDto> getPendingReservationsForUser(@AuthenticationPrincipal User user){
		return userService.getPendingReservationsForUser(user);
	}

	@GetMapping("/loans")
	public List<UserLoanDto> getLoansForUser(@AuthenticationPrincipal User user){
		return userService.getLoansForUser(user);
	}
}
