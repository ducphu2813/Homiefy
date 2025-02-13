package com.homeify.serviceinfo.serviceinfoapi.Controller;


import com.homeify.serviceinfo.Entities.Airport;
import com.homeify.serviceinfo.Entities.FlightInfo;
import com.homeify.serviceinfo.UseCases.FlightInfoUsecase;
import com.homeify.serviceinfo.serviceinfoapi.DTO.FlightInfo.FlightInfoDTO;
import com.homeify.serviceinfo.serviceinfoapi.DTO.FlightInfo.SaveFlightInfoDTO;
import com.homeify.serviceinfo.serviceinfoapi.Mapper.FlightInfoDTOMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flight-info")
public class FlightInfoController {

    private final FlightInfoUsecase flightInfoUsecase;
    private final FlightInfoDTOMapper flightInfoDTOMapper;

    public FlightInfoController(FlightInfoUsecase flightInfoUsecase
                                , FlightInfoDTOMapper flightInfoDTOMapper)
    {
        this.flightInfoUsecase = flightInfoUsecase;
        this.flightInfoDTOMapper = flightInfoDTOMapper;
    }

    //get all
    @GetMapping("/getAll")
    public List<FlightInfoDTO> getAll() {

        List<FlightInfo> flightInfos = flightInfoUsecase.getAllFlightInfo();

        //chuyển từ List<FlightInfo> sang List<FlightInfoDTO>
        //dùng mapper
        return flightInfoDTOMapper.toFlightInfoDTOs(flightInfos);

    }

    //thêm
    @PostMapping("/add")
    public FlightInfoDTO add(@RequestBody SaveFlightInfoDTO flightInfoDTO) {

        FlightInfo flightInfo = flightInfoDTOMapper.toFlightInfo(flightInfoDTO);

        //lấy id từ dto rồi set vào entity
        Airport departureAirport = new Airport();
        departureAirport.setId(flightInfoDTO.getDepartureAirportId());

        Airport arrivalAirport = new Airport();
        arrivalAirport.setId(flightInfoDTO.getArrivalAirportId());

        flightInfo.setDepartureAirport(departureAirport);
        flightInfo.setArrivalAirport(arrivalAirport);

        flightInfo = flightInfoUsecase.addFlightInfo(flightInfo);

        return flightInfoDTOMapper.toFlightInfoDTO(flightInfo);

    }

    //sửa
    @PutMapping("/update/{id}")
    public FlightInfoDTO update(@RequestBody SaveFlightInfoDTO flightInfoDTO, @PathVariable String id) {

        FlightInfo flightInfo = flightInfoDTOMapper.toFlightInfo(flightInfoDTO);

        flightInfo = flightInfoUsecase.updateFlightInfo(flightInfo, id);

        return flightInfoDTOMapper.toFlightInfoDTO(flightInfo);

    }

    //xóa
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {

        flightInfoUsecase.deleteFlightInfo(id);

    }

    //tìm theo id
    @GetMapping("/find/{id}")
    public FlightInfoDTO find(@PathVariable String id) {

        FlightInfo flightInfo = flightInfoUsecase.findFlightInfoById(id);

        return flightInfoDTOMapper.toFlightInfoDTO(flightInfo);

    }
}
