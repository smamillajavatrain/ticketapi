package com.sss.virtual.tech.ticketapi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sss.virtual.tech.ticketapi.model.Ticket;
import com.sss.virtual.tech.ticketapi.model.TrainDetails;
import com.sss.virtual.tech.ticketapi.model.User;
import com.sss.virtual.tech.ticketapi.repository.TrainDetailsRepository;
import com.sss.virtual.tech.ticketapi.service.ApiService;
import com.sss.virtual.tech.ticketapi.service.TrainService;

/**
 * 
 * @author smamilla
 *
 */
@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    private TrainService trainService;
    
    @Autowired
	private TrainDetailsRepository trainDetailsRepo;
    
    @Value("${com.sss.virtual.tech.ticketapi.persistence.enabled}")
    private Boolean isPersistence;

    @PostMapping("/purchase")
    public Ticket purchaseTicket(@RequestBody User user) {
    	if(isPersistence) {
    		return trainService.dbPurchaseTicket(user);
    	}
        return trainService.purchaseTicket(user);
    }

    @GetMapping("/receipt/{email}")
    public Ticket getReceipt(@PathVariable String email) {
        return trainService.getReceipt(email);
    }

    @GetMapping("/seats/{section}")
    public List<Map<String, String>> getUsersBySection(@PathVariable String section) {
        return trainService.getUsersBySection(section);
    }

    @DeleteMapping("/remove/{email}")
    public String removeUser(@PathVariable String email) {
        return trainService.removeUser(email);
    }

    @PutMapping("/seat")
    public Ticket modifySeat(@RequestParam String email, @RequestParam String newSection) {
        return trainService.modifySeat(email, newSection);
    }
    
    @Autowired
    private ApiService apiService;
    

    @GetMapping("/fetch")
    public String fetchData() {
        return apiService.getData();
    }
    
    @PostMapping("/train")
    public TrainDetails createTrain(@RequestBody TrainDetails details) {
        return trainDetailsRepo.save(details);
    }

    @GetMapping("/trains")
    public List<TrainDetails> getTrains() {
        return trainDetailsRepo.findAll();
    }

	/**
	 * @return the isPersistence
	 */
	public Boolean getIsPersistence() {
		return isPersistence;
	}

	/**
	 * @param isPersistence the isPersistence to set
	 */
	public void setIsPersistence(Boolean isPersistence) {
		this.isPersistence = isPersistence;
	}
}