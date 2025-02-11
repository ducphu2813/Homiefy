package com.homeify.serviceinfo.Mapper;

import com.homeify.serviceinfo.Entities.Airport;
import com.homeify.serviceinfo.Model.AirportModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AirportMapper {

    //chuyển từ AirportModel sang Airport
    Airport toAirport(AirportModel airportModel);

    //chuyển từ Airport sang AirportModel
    AirportModel toAirportModel(Airport airport);

    //chuyển từ List<AirportModel> sang List<Airport>
    List<Airport> toAirport(List<AirportModel> airportModel);

    //chuyển từ List<Airport> sang List<AirportModel>
    List<AirportModel> toAirportModel(List<Airport> airport);
}
