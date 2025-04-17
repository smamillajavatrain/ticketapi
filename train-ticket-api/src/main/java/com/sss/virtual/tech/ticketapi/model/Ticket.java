package com.sss.virtual.tech.ticketapi.model;

/**
 * 
 * @author smamilla
 *
 */

public class Ticket {

    private Long id;

    private String fromStation="London";
    private String toStation="France";

    private User user;

    private double pricePaid=20.0;
    private String seatNumber;
    private String section;
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
}