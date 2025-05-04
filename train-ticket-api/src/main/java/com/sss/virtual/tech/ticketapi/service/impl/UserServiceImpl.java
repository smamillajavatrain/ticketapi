/**
 * 
 */
package com.sss.virtual.tech.ticketapi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sss.virtual.tech.ticketapi.model.User;
import com.sss.virtual.tech.ticketapi.repository.UserRepository;
import com.sss.virtual.tech.ticketapi.service.JWTService;
import com.sss.virtual.tech.ticketapi.service.UserService;

import io.jsonwebtoken.Claims;

/**
 * @author DELL
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserRepository repo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;
    
    @Autowired
    private BCryptPasswordEncoder encoder;
    
    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public List<User> getAllUsersByRole(String role) {
        return repo.findByRoleIgnoreCase(role);
    }


    public Map<String, String> verify(User user) {
        // Authenticate the user
        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            // Fetch user from database
            User dbUser = repo.findByEmail(user.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Generate token
            String token = jwtService.generateToken(user.getEmail(), dbUser.getRole());

            // Create a response map to return both the token and the role
            Map<String, String> response = new HashMap<>();
            response.put("token", token);  // Add token to the response
            response.put("role", dbUser.getRole());  // Add role to the response

            return response;  // Return both token and role
        }

        // Return "fail" if authentication fails
        Map<String, String> response = new HashMap<>();
        response.put("message", "Invalid email or password.");
        return response;
    }


    public User getUserByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUsers() {
        return repo.findAll();  // Retrieve all users from the repository
    }

    public void deleteUser(Long userId) {
    	repo.deleteById(userId);
    }

    // Save user
    public User saveUser(User user, String currentUserEmail) {
        // Check if the user is updating their own profile
        if (user.getEmail().equals(currentUserEmail)) {
            // Update password if it exists
            if (user.getPassword() != null) {
                user.setPassword(encoder.encode(user.getPassword()));
            }
            return repo.save(user);
        } else {
            throw new AccessDeniedException("You can only update your own profile.");
        }
    }

    public void sendPasswordResetEmail(String email) {
        User user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        String token = jwtService.generateToken(user.getEmail(), user.getRole());
        String resetLink = "http://localhost:5173/reset-password/" + token;
//        emailService.sendPasswordResetEmail(email, resetLink);
    }

    public void resetPassword(String token, String newPassword) {
        // Validate and extract claims from the token
        Claims claims = jwtService.extractAllClaims(token);
        String username = claims.getSubject();

        // Fetch the user from the database
        User user = repo.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Set the new password
        user.setPassword(encoder.encode(newPassword));
        repo.save(user);
    }

}
