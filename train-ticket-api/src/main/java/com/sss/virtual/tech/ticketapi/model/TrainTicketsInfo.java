/**
 * 
 */
package com.sss.virtual.tech.ticketapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author DELL
 *
 */
@Entity
public class TrainTicketsInfo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String trainNumber = "123";
	private int availableTickets=25;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the trainNumber
	 */
	public String getTrainNumber() {
		return trainNumber;
	}
	/**
	 * @param trainNumber the trainNumber to set
	 */
	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}
	/**
	 * @return the availableTickets
	 */
	public int getAvailableTickets() {
		return availableTickets;
	}
	/**
	 * @param availableTickets the availableTickets to set
	 */
	public void setAvailableTickets(int availableTickets) {
		this.availableTickets = availableTickets;
	}
}