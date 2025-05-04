package com.sss.virtual.tech.ticketapi.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        // Get the user roles
        String role = authentication.getAuthorities().toString();

        // Redirect based on the role
        if (role.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin/dashboard"); // Redirect to Admin Dashboard
        } else if (role.contains("ROLE_TRAINEE")) {
            response.sendRedirect("/trainee/dashboard"); // Redirect to Trainee Dashboard
        } else {
            response.sendRedirect("/"); // Default fallback if role is not found
        }
    }
}
