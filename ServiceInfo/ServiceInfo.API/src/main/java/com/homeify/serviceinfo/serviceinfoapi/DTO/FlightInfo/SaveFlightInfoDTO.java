package com.homeify.serviceinfo.serviceinfoapi.DTO.FlightInfo;

import com.homeify.serviceinfo.Entities.Airport;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SaveFlightInfoDTO {

    private String departureAirportId;
    private String arrivalAirportId;
    private LocalDateTime createdAt;
    private String status;
}
