package com.homeify.serviceinfo.Adapter;

import com.homeify.serviceinfo.Entities.Flight;

import java.util.List;

public interface FlightAdapter {

    //thêm flight
    Flight addFlight(Flight flight);

    //sửa flight
    Flight updateFlight(Flight flight, String flightId);

    //xóa flight
    void deleteFlight(String flightId);

    //lấy tat ca flight
    List<Flight> getAllFlight();

    //tìm theo id
    Flight findFlightById(String flightId);
}
