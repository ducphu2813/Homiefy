package com.homeify.serviceinfo.serviceinfoapi.Mapper;

import com.homeify.serviceinfo.Entities.Airport;
import com.homeify.serviceinfo.serviceinfoapi.DTO.Airport.AirportDTO;
import com.homeify.serviceinfo.serviceinfoapi.DTO.Airport.SaveAirportDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirportDTOMapper {

    //chuyển từ SaveAirportDTO sang Airport
    Airport toAirport(SaveAirportDTO saveAirportDTO);

    //chuyển từ Airport sang AirportDTO
    AirportDTO toAirportDTO(Airport airport);

    //chuyển từ AirportDTO sang Airport
    Airport toAirport(AirportDTO airportDTO);

    //chuyển từ List<Airport> sang List<AirportDTO>
    List<AirportDTO> toAirportDTOs(List<Airport> airports);

    //chuyển từ List<AirportDTO> sang List<Airport>
    List<Airport> toAirports(List<AirportDTO> airportDTOs);
}
