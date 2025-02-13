package com.homeify.serviceinfo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "flightinfos")
public class FlightInfoModel {

    @Id
    private String id;
    private String departureAirportId;
    private String arrivalAirportId;
    private String createdAt;
    private String status;
}
