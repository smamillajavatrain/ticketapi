package com.sss.virtual.tech.ticketapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * 
 * @author smamilla
 *
 */
@Entity
public class Ticket {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromStation="London";
    private String toStation="France";
    
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    private double pricePaid=20.0;
    private String seatNumber;
    private String section;
    @ManyToOne
    private TrainDetails trainDetails;
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
	 * @return the fromStation
	 */
	public String getFromStation() {
		return fromStation;
	}
	/**
	 * @param fromStation the fromStation to set
	 */
	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}
	/**
	 * @return the toStation
	 */
	public String getToStation() {
		return toStation;
	}
	/**
	 * @param toStation the toStation to set
	 */
	public void setToStation(String toStation) {
		this.toStation = toStation;
	}
	
	/**
	 * @return the pricePaid
	 */
	public double getPricePaid() {
		return pricePaid;
	}
	/**
	 * @param pricePaid the pricePaid to set
	 */
	public void setPricePaid(double pricePaid) {
		this.pricePaid = pricePaid;
	}
	/**
	 * @return the seatNumber
	 */
	public String getSeatNumber() {
		return seatNumber;
	}
	/**
	 * @param seatNumber the seatNumber to set
	 */
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	public TrainDetails getTrainDetails() { return trainDetails; }
	public void setTrainDetails(TrainDetails trainDetails) { this.trainDetails = trainDetails; }
}