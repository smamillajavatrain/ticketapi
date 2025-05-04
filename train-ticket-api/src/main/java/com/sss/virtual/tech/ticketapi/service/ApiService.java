/**
 * 
 */
package com.sss.virtual.tech.ticketapi.service;

import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

/**
 * @author DELL
 *
 */
@Service
public class ApiService {

	private final ExternalService externalService;

	public ApiService(ExternalService externalService) {
		this.externalService = externalService;
	}

	@CircuitBreaker(name = "externalServiceCB", fallbackMethod = "fallbackResponse")
	public String getData() {
		return externalService.callExternalApi();
	}

	public String fallbackResponse(Throwable t) {
		return "Fallback response due to error: " + t.getMessage();
	}
}
