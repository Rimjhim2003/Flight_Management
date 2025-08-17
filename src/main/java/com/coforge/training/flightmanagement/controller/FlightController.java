package com.coforge.training.flightmanagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.coforge.training.flightmanagement.dto.FlightRequest;
import com.coforge.training.flightmanagement.model.Flight;
import com.coforge.training.flightmanagement.service.FlightService;

import java.util.List;

@RestController
@RequestMapping("/flights")

public class FlightController {
	
	@Autowired
    private FlightService flightService;

    @PostMapping
    public Flight createFlight(@RequestBody FlightRequest flightRequest) {
        return flightService.saveFlight(flightRequest);
    }

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    public Flight getFlight(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }
}