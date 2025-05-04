/**
 * 
 */
package com.sss.virtual.tech.ticketapi;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author DELL
 *
 */
@Component
public class MyTask {

	@Scheduled(fixedRate = 10000)
    public void run() {
//        System.out.println("Running every 10 seconds");
    }
}
