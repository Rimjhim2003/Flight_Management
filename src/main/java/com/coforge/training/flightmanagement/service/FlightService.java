package com.coforge.training.flightmanagement.service;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coforge.training.flightmanagement.dto.FlightRequest;
import com.coforge.training.flightmanagement.model.Flight;
import com.coforge.training.flightmanagement.model.Seat;
import com.coforge.training.flightmanagement.repository.FlightRepository;
import com.coforge.training.flightmanagement.repository.SeatRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {
	@Autowired
    private  FlightRepository flightRepository;
	
	@Autowired
    private SeatRepository seatRepository;

    public Flight saveFlight(FlightRequest flightRequest) {
        // Save flight
        Flight flight = Flight.builder()
                .flightNumber(flightRequest.getFlightNumber())
                .airline(flightRequest.getAirline())
                .departureAirport(flightRequest.getDepartureAirport())
                .arrivalAirport(flightRequest.getArrivalAirport())
                .departureTime(flightRequest.getDepartureTime())
                .arrivalTime(flightRequest.getArrivalTime())
                .price(flightRequest.getPrice())
                .availableSeats(flightRequest.getAvailableSeats())
                .build();

        Flight savedFlight = flightRepository.save(flight);

        // Auto-create seats
        for (int i = 1; i <= flightRequest.getAvailableSeats(); i++) {
            Seat seat = Seat.builder()
                    .seatNumber("S" + i)
                    .seatClass(flightRequest.getCabin())
                    .isBooked(false)
                    .flight(savedFlight)
                    .build();
            seatRepository.save(seat);
        }

        return savedFlight;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}