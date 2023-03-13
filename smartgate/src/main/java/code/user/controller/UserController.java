package code.user.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import code.user.entity.User;
import code.user.repository.UserRepository;

@RestController
@CrossOrigin("*")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	
	
	@PostMapping("/user/create")
	public ResponseEntity<String> registerUser(@RequestBody User newAccount) {
		Optional<User> userOpt=userRepository.findByUsername(newAccount.getUsername());
		boolean present=userOpt.isPresent();
		if(present==false) {
			newAccount.setCreatedTime(LocalDateTime.now());
			userRepository.save(newAccount);
		}
		else {
			return new ResponseEntity<>("{\"created\":\"false\",\"message\":\"The user with the registration number is already exists.\"}", HttpStatus.OK);
		}
		
		return new ResponseEntity<>("{\"created\":\"true\",\"message\":\"New user Registered Successfully.\"}", HttpStatus.OK);
	}
	
	@GetMapping("/user/readall")
	public ResponseEntity<List<User>> readAllUsers() {
		List<User> users=userRepository.findAll();
		
		
		
		return new ResponseEntity<>(users, HttpStatus.OK);

	}
	
//	@PostMapping("/user/exists-id-by-username/{username}")
//	public ResponseEntity<String> existsUserByUsername(@PathVariable String username) {
//		
//		Optional<User> userOpt=userRepository.findByUsername(username);
//		if(userOpt.isEmpty()) {
//			return new ResponseEntity<>("""
//					{
//						"userExist":"false"
//					}
//					""", HttpStatus.OK);
//		}
//		User user=userOpt.get();
//		Long id=user.getId();
//		
//		return new ResponseEntity<>("""
//				{
//					"userExist":true,
//					"username":"%s"
//				}
//				""".formatted(username), HttpStatus.OK);
//	}
	
	@PostMapping("/user/read-id-by-username/{username}")
	public ResponseEntity<User> readUserByUsername(@PathVariable String username) {
		
		Optional<User> userOpt=userRepository.findByUsername(username);
		if(userOpt.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		User user=userOpt.get();
		user.setPassword(null);
				
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PostMapping("/user/update")
	public ResponseEntity<String> updateUser(@RequestBody User inputUser) {
		Optional<User> userOpt=userRepository.findByUsername(inputUser.getUsername());
		boolean present=userOpt.isPresent();
		System.out.println(inputUser.getUsername());
		if(present==true) {
			User user=userOpt.get();
			user.setDateOfBirth(inputUser.getDateOfBirth());
			user.setEmail(inputUser.getEmail());
			user.setExpiryDate(inputUser.getExpiryDate());
			user.setFirstName(inputUser.getFirstName());
			user.setJoiningDate(inputUser.getJoiningDate());
			user.setLastDate(inputUser.getLastDate());
			user.setLastName(inputUser.getLastName());
			user.setMiddleName(inputUser.getMiddleName());
			user.setMobileNumber(inputUser.getMobileNumber());
			user.setRole(inputUser.getRole());
			userRepository.save(user);
//			System.out.println(user.toString());
//			System.out.println("user updated successfully.");
		}
		else {
			return new ResponseEntity<>("""
					{
						"updated":false,
						"message":"The user with the details doesn't exist."
					}
					""", HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>("""
				{
					"updated":true,
					"message":"User Details Updated successfully."
				}
				""", HttpStatus.OK);
		}
}
