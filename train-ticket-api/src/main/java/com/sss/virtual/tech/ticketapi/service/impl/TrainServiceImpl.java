package com.sss.virtual.tech.ticketapi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.sss.virtual.tech.ticketapi.model.Ticket;
import com.sss.virtual.tech.ticketapi.model.User;
import com.sss.virtual.tech.ticketapi.service.TrainService;

/**
 * 
 * @author smamilla
 *
 */

@Service
public class TrainServiceImpl implements TrainService {
	
	 private final Map<String, Ticket> ticketMap = new ConcurrentHashMap<>();
	 private final Queue<String> sectionA = new LinkedList<>();
	 private final Queue<String> sectionB = new LinkedList<>();

		/*
		 * @Autowired private TicketRepository ticketRepository;
		 * 
		 * private AtomicInteger seatCounter = new AtomicInteger(1);
		 */
    
    public Ticket purchaseTicket(User user) {
    	Random rand = new Random();
        if (ticketMap.containsKey(user.getEmail())) {
            return ticketMap.get(user.getEmail());
        }
        String section = sectionA.size() <= sectionB.size() ? "A" : "B";
        if (section.equals("A")) {
            sectionA.add(user.getEmail());
        } else {
            sectionB.add(user.getEmail());
        }
        Ticket ticket = new Ticket();
        ticket.setId(Long.valueOf(rand.nextInt(1000)));
        ticket.setUser(user);
        ticket.setSection(section);
        ticket.setSeatNumber(String.valueOf(rand.nextInt(200)));
        ticketMap.put(user.getEmail(), ticket);
        return ticket;
    }

    public Ticket getReceipt(String email) {
        return ticketMap.get(email);
    }

    public List<Map<String, String>> getUsersBySection(String section) {
        Queue<String> sectionQueue = section.equalsIgnoreCase("A") ? sectionA : sectionB;
        List<Map<String, String>> result = new ArrayList<>();
        for (String email : sectionQueue) {
            Ticket ticket = ticketMap.get(email);
            Map<String, String> userMap = new HashMap<>();
            userMap.put("email", email);
            userMap.put("name", ticket.getUser().getFirstName() + " " + ticket.getUser().getLastName());
            result.add(userMap);
        }
        return result;
    }

    public String removeUser(String email) {
        Ticket removed = ticketMap.remove(email);
        if (removed != null) {
            (removed.getSection().equals("A") ? sectionA : sectionB).remove(email);
            return "User removed successfully.";
        }
        return "User not found.";
    }

    public Ticket modifySeat(String email, String newSection) {
        Ticket ticket = ticketMap.get(email);
        if (ticket == null || (!newSection.equals("A") && !newSection.equals("B"))) return null;

        if (!ticket.getSection().equals(newSection)) {
            (ticket.getSection().equals("A") ? sectionA : sectionB).remove(email);
            (newSection.equals("A") ? sectionA : sectionB).add(email);
            ticket.setSection(newSection);
        }
        return ticket;
    }
}