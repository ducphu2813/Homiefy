package com.homeify.serviceinfo.UseCases;

import com.homeify.serviceinfo.Adapter.AirportAdapter;
import com.homeify.serviceinfo.Entities.Airport;

import java.util.List;

public class AirportUsecase {

    private final AirportAdapter airportAdapter;

    public AirportUsecase(AirportAdapter airportAdapter) {
        this.airportAdapter = airportAdapter;
    }

    //thêm airport
    public Airport addAirport(Airport airport) {
        return airportAdapter.addAirport(airport);
    }

    //sửa airport
    public Airport updateAirport(Airport airport, String airportId) {
        //tìm airport theo id
        Airport airportById = airportAdapter.findAirportById(airportId);
        airport.setId(airportById.getId());
        return airportAdapter.updateAirport(airport, airportId);
    }

    //xóa airport
    public void deleteAirport(String airportId) {
        airportAdapter.deleteAirport(airportId);
    }

    //lấy tat ca airport
    public List<Airport> getAllAirport() {
        return airportAdapter.getAllAirport();
    }

    //tìm theo id
    public Airport findAirportById(String airportId) {
        return airportAdapter.findAirportById(airportId);
    }
}
