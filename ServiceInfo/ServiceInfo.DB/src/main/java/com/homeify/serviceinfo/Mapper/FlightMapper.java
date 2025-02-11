package com.homeify.serviceinfo.Mapper;

import com.homeify.serviceinfo.Entities.Flight;
import com.homeify.serviceinfo.Model.FlightModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface FlightMapper {

    //chuyển từ FlightModel sang Flight
    Flight toFlight(FlightModel flightModel);

    //chuyển từ Flight sang FlightModel
    FlightModel toFlightModel(Flight flight);

    //chuyển từ List<FlightModel> sang List<Flight>
    List<Flight> toFlight(List<FlightModel> flightModel);

    //chuyển từ List<Flight> sang List<FlightModel>
    List<FlightModel> toFlightModel(List<Flight> flight);
}
