package com.sss.virtual.tech.ticketapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 
 * @author smamilla
 *
 */
@SpringBootApplication
@EnableScheduling
public class TicketApiApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(TicketApiApplication.class,args);
	}
	
	 	@Bean
	    public BCryptPasswordEncoder passwordEncoder(){
	    	return new BCryptPasswordEncoder();
	    }
}	