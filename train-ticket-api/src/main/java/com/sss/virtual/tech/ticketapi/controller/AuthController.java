/**
 * 
 */
package com.sss.virtual.tech.ticketapi.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sss.virtual.tech.ticketapi.model.User;
import com.sss.virtual.tech.ticketapi.service.JWTService;
import com.sss.virtual.tech.ticketapi.service.UserService;

/**
 * @author DELL
 *
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

	@Autowired
	private UserService userService;
	private final JWTService jwtService;

	public AuthController(JWTService jwtService) {
		this.jwtService = jwtService;
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user) {

		return new ResponseEntity<>(userService.register(user), HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		String token = userService.verify(user).toString();
		if (token.equals("fail")) {
			return ResponseEntity.status(401).body("Invalid credentials");
		}

		return ResponseEntity.ok(token);
	}

	@GetMapping("/user-details")
	public ResponseEntity<?> getUserDetails(@RequestHeader("Authorization") String token) {
		String username = jwtService.extractUsername(token.substring(7));
		return ResponseEntity.ok(username);
	}

	@PreAuthorize("hasRole('admin')")
	@GetMapping("/admin/users")
	public ResponseEntity<?> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@PreAuthorize("hasRole('admin')")
	@DeleteMapping("/admin/users/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
		return ResponseEntity.ok("User deleted successfully.");
	}

//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/trainees")
//    public ResponseEntity<List<User>> getAllTrainees(@RequestHeader("Authorization") String token) {
//        // Check if the user has the ADMIN role (or any other roles as per your requirement).
//        // This is done by the @PreAuthorize annotation, so we don't need extra logic here.
//
//        // Fetch all trainees from the userService
//        List<User> trainees = userService.getAllUsersByRole("TRAINEE");
//
//        // Return the list of trainees as the response
//        return ResponseEntity.ok(trainees);
//    }

	@PreAuthorize("hasRole('trainee')")
	@GetMapping("/trainee/profile")
	public ResponseEntity<?> getTraineeProfile(@RequestHeader("Authorization") String token) {
		String username = jwtService.extractUsername(token.substring(7));
		User trainee = userService.getUserByEmail(username);
		return ResponseEntity.ok(trainee);
	}

	// Trainee can only edit their own profile
//    @PreAuthorize("hasRole('trainee')")
	@PutMapping("/trainee/profile")
	public ResponseEntity<?> editTraineeProfile(@RequestHeader("Authorization") String token, @RequestBody User user) {
		String currentUserEmail = jwtService.extractUsername(token.substring(7));
		if (!user.getEmail().equals(currentUserEmail)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can only edit your own profile.");
		}
		User updatedUser = userService.saveUser(user, currentUserEmail);
		return ResponseEntity.ok(updatedUser);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN)
				.body("Access Denied: You do not have permission to access this role.");
	}

	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> body) {
		String email = body.get("email");
		try {
			userService.sendPasswordResetEmail(email);
			return ResponseEntity.ok("Password reset link sent to your email.");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: " + e.getMessage());
		}
	}

	@PostMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> body) {
		String token = body.get("token");
		String newPassword = body.get("password");
		try {
			userService.resetPassword(token, newPassword);
			return ResponseEntity.ok("Password has been successfully reset.");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: " + e.getMessage());
		}
	}
}