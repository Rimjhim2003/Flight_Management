package com.coforge.training.flightmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coforge.training.flightmanagement.model.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {
	
}