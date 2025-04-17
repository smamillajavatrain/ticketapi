package com.sss.virtual.tech.ticketapi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.sss.virtual.tech.ticketapi.model.User;
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

    @PostMapping("/purchase")
    public Ticket purchaseTicket(@RequestBody User user) {
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
}