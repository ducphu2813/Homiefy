package com.homeify.serviceinfo.Adapter;

import com.homeify.serviceinfo.Entities.FlightInfo;

import java.util.List;

public interface FlightInfoAdapter {

    //thêm flight info
    FlightInfo addFlightInfo(FlightInfo flightInfo);

    //sửa flight info
    FlightInfo updateFlightInfo(FlightInfo flightInfo, String flightInfoId);

    //xóa flight info
    void deleteFlightInfo(String flightInfoId);

    //lấy tat ca flight info
    List<FlightInfo> getAllFlightInfo();

    //tìm theo id
    FlightInfo findFlightInfoById(String flightInfoId);
}
