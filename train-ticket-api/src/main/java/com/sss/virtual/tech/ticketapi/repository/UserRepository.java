/**
 * 
 */
package com.sss.virtual.tech.ticketapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sss.virtual.tech.ticketapi.model.User;

/**
 * @author DELL
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    void deleteByEmail(String email);
    List<User> findByRoleIgnoreCase(String role);
}