/**
 * 
 */
package com.sss.virtual.tech.ticketapi.service;

import org.springframework.stereotype.Service;

/**
 * @author DELL
 *
 */
@Service
public class ExternalService {
	public String callExternalApi() {
		// Simulate a failure or slow response
		if (Math.random() > 0.5) {
			throw new RuntimeException("External service failed");
		}
		return "External Service Success";
	}
}
