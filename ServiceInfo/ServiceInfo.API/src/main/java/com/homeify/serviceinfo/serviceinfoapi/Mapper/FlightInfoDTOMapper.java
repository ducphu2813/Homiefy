package com.homeify.serviceinfo.serviceinfoapi.Mapper;

import com.homeify.serviceinfo.Entities.FlightInfo;
import com.homeify.serviceinfo.serviceinfoapi.DTO.FlightInfo.FlightInfoDTO;
import com.homeify.serviceinfo.serviceinfoapi.DTO.FlightInfo.SaveFlightInfoDTO;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface FlightInfoDTOMapper {

    //chuyển từ FlightInfo sang FlightInfoDTO
    FlightInfoDTO toFlightInfoDTO(FlightInfo flightInfo);

    //chuyển từ FlightInfoDTO sang FlightInfo
    FlightInfo toFlightInfo(FlightInfoDTO flightInfoDTO);

    //chuyển từ List<FlightInfo> sang List<FlightInfoDTO>
    List<FlightInfoDTO> toFlightInfoDTOs(List<FlightInfo> flightInfos);

    //chuyển từ List<FlightInfoDTO> sang List<FlightInfo>
    List<FlightInfo> toFlightInfos(List<FlightInfoDTO> flightInfoDTOs);

    //chuyển từ FlightInfo sang SaveFlightInfoDTO
    SaveFlightInfoDTO toSaveFlightInfoDTO(FlightInfo flightInfo);

    //chuyển từ SaveFlightInfoDTO sang FlightInfo
    FlightInfo toFlightInfo(SaveFlightInfoDTO saveFlightInfoDTO);

}
