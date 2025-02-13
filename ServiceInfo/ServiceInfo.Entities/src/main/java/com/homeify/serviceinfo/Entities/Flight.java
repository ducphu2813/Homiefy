package com.homeify.serviceinfo.Entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Flight {

    private String id;
    private String flightNumber;
    private FlightInfo flightInfo;
    private String airline;
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
