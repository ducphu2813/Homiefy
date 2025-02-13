package com.homeify.serviceinfo.serviceinfoapi.DTO.FlightInfo;

import com.homeify.serviceinfo.Entities.Airport;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FlightInfoDTO {

    private String id;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private LocalDateTime createdAt;
    private String status;
}
