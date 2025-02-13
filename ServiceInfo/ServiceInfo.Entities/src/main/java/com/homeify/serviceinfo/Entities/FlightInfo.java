package com.homeify.serviceinfo.Entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FlightInfo {

    private String id;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private LocalDateTime createdAt;
    private String status;
}
