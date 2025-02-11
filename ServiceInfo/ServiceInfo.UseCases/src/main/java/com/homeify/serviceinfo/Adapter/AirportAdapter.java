package com.homeify.serviceinfo.Adapter;

import com.homeify.serviceinfo.Entities.Airport;

import java.util.List;

public interface AirportAdapter {

    //thêm airport
    Airport addAirport(Airport airport);

    //sửa airport
    Airport updateAirport(Airport airport, String airportId);

    //xóa airport
    void deleteAirport(String airportId);

    //lấy tat ca airport
    List<Airport> getAllAirport();

    //tìm theo id
    Airport findAirportById(String airportId);
}
