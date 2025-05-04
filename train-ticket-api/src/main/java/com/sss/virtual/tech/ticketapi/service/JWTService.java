package com.sss.virtual.tech.ticketapi.service;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface JWTService {

	public String generateToken(String username, String role);
	public String extractRole(String token);
	public String extractUsername(String token);
	public Claims extractAllClaims(String token);
	public boolean validateToken(String token, UserDetails userDetails);
}
