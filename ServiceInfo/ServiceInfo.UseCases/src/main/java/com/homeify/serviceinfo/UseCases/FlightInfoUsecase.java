package com.homeify.serviceinfo.UseCases;

import com.homeify.serviceinfo.Adapter.FlightInfoAdapter;
import com.homeify.serviceinfo.Entities.FlightInfo;

import java.util.List;

public class FlightInfoUsecase {

    private final FlightInfoAdapter flightInfoAdapter;

    public FlightInfoUsecase(FlightInfoAdapter flightInfoAdapter) {
        this.flightInfoAdapter = flightInfoAdapter;
    }

    //thêm flight info
    public FlightInfo addFlightInfo(FlightInfo flightInfo) {
        return flightInfoAdapter.addFlightInfo(flightInfo);
    }

    //sửa flight info
    public FlightInfo updateFlightInfo(FlightInfo flightInfo, String flightInfoId) {
        //tìm theo id
        FlightInfo flightInfoById = flightInfoAdapter.findFlightInfoById(flightInfoId);
        flightInfo.setId(flightInfoById.getId());
        return flightInfoAdapter.updateFlightInfo(flightInfo, flightInfoId);
    }

    //xóa flight info
    public void deleteFlightInfo(String flightInfoId) {
        flightInfoAdapter.deleteFlightInfo(flightInfoId);
    }

    //lấy tất cả flight info
    public List<FlightInfo> getAllFlightInfo() {
        return flightInfoAdapter.getAllFlightInfo();
    }

    //tìm theo id
    public FlightInfo findFlightInfoById(String flightInfoId) {
        return flightInfoAdapter.findFlightInfoById(flightInfoId);
    }
}
