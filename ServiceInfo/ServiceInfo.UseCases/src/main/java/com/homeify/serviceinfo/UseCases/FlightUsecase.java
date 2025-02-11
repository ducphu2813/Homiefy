package com.homeify.serviceinfo.UseCases;

import com.homeify.serviceinfo.Adapter.FlightAdapter;
import com.homeify.serviceinfo.Entities.Flight;

import java.util.List;

public class FlightUsecase {

    private final FlightAdapter flightAdapter;

    public FlightUsecase(FlightAdapter flightAdapter) {
        this.flightAdapter = flightAdapter;
    }

    //thêm flight
    public Flight addFlight(Flight flight) {
        return flightAdapter.addFlight(flight);
    }

    //sửa flight
    public Flight updateFlight(Flight flight, String flightId) {
        return flightAdapter.updateFlight(flight, flightId);
    }

    //xóa flight
    public void deleteFlight(String flightId) {
        flightAdapter.deleteFlight(flightId);
    }

    //tìm theo id
    public Flight findFlightById(String flightId) {
        return flightAdapter.findFlightById(flightId);
    }

    //lấy tat ca flight
    public List<Flight> getAllFlight() {
        return flightAdapter.getAllFlight();
    }
}
