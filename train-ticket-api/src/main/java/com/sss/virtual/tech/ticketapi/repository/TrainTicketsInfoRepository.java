/**
 * 
 */
package com.sss.virtual.tech.ticketapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sss.virtual.tech.ticketapi.model.TrainTicketsInfo;

/**
 * @author DELL
 *
 */
public interface TrainTicketsInfoRepository extends JpaRepository<TrainTicketsInfo, Long> {
}
