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
public class TrainDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trainNumber;
    private String departureTime;
    private String arrivalTime;
    private String availableSections; // Example: "A,B"

    // Getters and Setters
    public Long getId() { return id; }
    public String getTrainNumber() { return trainNumber; }
    public void setTrainNumber(String trainNumber) { this.trainNumber = trainNumber; }
    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }
    public String getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime; }
    public String getAvailableSections() { return availableSections; }
    public void setAvailableSections(String availableSections) { this.availableSections = availableSections; }
}