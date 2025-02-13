package com.homeify.serviceinfo.serviceinfoapi.Mapper;

import com.homeify.serviceinfo.Entities.Flight;
import com.homeify.serviceinfo.serviceinfoapi.DTO.Flight.FlightDTO;
import com.homeify.serviceinfo.serviceinfoapi.DTO.Flight.SaveFlightDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlightDTOMapper {

    //chuyển từ Flight sang FlightDTO
    FlightDTO toFlightDTO(Flight flight);

    //chuyển từ FlightDTO sang Flight
    Flight toFlight(FlightDTO flightDTO);

    //chuyển từ List<Flight> sang List<FlightDTO>
    List<FlightDTO> toFlightDTOs(List<Flight> flights);

    //chuyển từ List<FlightDTO> sang List<Flight>
    List<Flight> toFlights(List<FlightDTO> flightDTOs);

    //chuyển từ Flight sang SaveFlightDTO
    SaveFlightDTO toSaveFlightDTO(Flight flight);

    //chuyển từ SaveFlightDTO sang Flight
    Flight toFlight(SaveFlightDTO saveFlightDTO);
}
