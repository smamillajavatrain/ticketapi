package com.sss.virtual.tech.ticketapi.service;

import java.util.List;
import java.util.Map;

import com.sss.virtual.tech.ticketapi.model.Ticket;
import com.sss.virtual.tech.ticketapi.model.User;

/**
 * 
 * @author smamilla
 *
 */

public interface TrainService {

	public Ticket purchaseTicket(User user);

	public Ticket getReceipt(String email);

	public List<Map<String, String>> getUsersBySection(String section);

	public String removeUser(String email);

	public Ticket modifySeat(String email, String newSection);
	
//	################################DB Services Starts from here####################################
	public Ticket dbPurchaseTicket(User user);

	public Ticket dbGetReceipt(String email);

	public List<Map<String, String>> getDBUsersBySection(String section);

	public String removeDbUser(String email);

	public Ticket dbmodifySeat(String email, String newSection);
}