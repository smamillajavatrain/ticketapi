/**
 * 
 */
package com.sss.virtual.tech.ticketapi.service;

import java.util.List;
import java.util.Map;

import com.sss.virtual.tech.ticketapi.model.User;

/**
 * @author DELL
 *
 */
public interface UserService {
	
	public User register(User user);
	public List<User> getAllUsersByRole(String role);
	public Map<String, String> verify(User user);
	public User getUserByEmail(String email);
	public List<User> getAllUsers();
	public void deleteUser(Long userId);
	public User saveUser(User user, String currentUserEmail);
	public void sendPasswordResetEmail(String email);
	public void resetPassword(String token, String newPassword);

}
