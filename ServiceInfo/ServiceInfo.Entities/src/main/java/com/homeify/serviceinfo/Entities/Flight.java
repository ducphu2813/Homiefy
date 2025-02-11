package com.homeify.serviceinfo.Entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Flight {

    private String id;
    private String flightNumber;
    private String flightInfoId;
    private String airline;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String gateNumber;
    private Double economyPrice;
    private Double businessPrice;
    private Integer availableSeats;
    private Integer availableBusinessSeats;
    private Integer availableEconomySeats;
    private String status;
    private LocalDateTime createdAt;
}
