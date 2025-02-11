package com.homeify.serviceinfo.serviceinfoapi.DTO.Airport;

import com.homeify.serviceinfo.Entities.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveAirportDTO {

    private String name;
    private String cityId;
    private String address;
}
