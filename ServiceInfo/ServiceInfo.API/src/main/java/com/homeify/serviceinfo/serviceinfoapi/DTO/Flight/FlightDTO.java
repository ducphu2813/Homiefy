package com.homeify.serviceinfo.serviceinfoapi.DTO.Flight;

import com.homeify.serviceinfo.Entities.FlightInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {

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
