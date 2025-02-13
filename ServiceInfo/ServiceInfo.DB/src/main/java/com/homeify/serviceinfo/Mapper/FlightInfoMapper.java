package com.homeify.serviceinfo.Mapper;

import com.homeify.serviceinfo.Entities.FlightInfo;
import com.homeify.serviceinfo.Model.FlightInfoModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface FlightInfoMapper {

    //chuyển từ FlightInfoModel sang FlightInfo
    FlightInfo toFlightInfo(FlightInfoModel flightInfoModel);

    //chuyển từ FlightInfo sang FlightInfoModel
    FlightInfoModel toFlightInfoModel(FlightInfo flightInfo);

    //chuyển từ List<FlightInfoModel> sang List<FlightInfo>
    List<FlightInfo> toFlightInfo(List<FlightInfoModel> flightInfoModel);

    //chuyển từ List<FlightInfo> sang List<FlightInfoModel>
    List<FlightInfoModel> toFlightInfoModel(List<FlightInfo> flightInfo);
}
