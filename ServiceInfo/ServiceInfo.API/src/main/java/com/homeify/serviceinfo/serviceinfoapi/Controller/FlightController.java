package com.homeify.serviceinfo.serviceinfoapi.Controller;

import com.homeify.serviceinfo.Entities.Flight;
import com.homeify.serviceinfo.UseCases.FlightUsecase;
import com.homeify.serviceinfo.serviceinfoapi.DTO.FlightDTO;
import com.homeify.serviceinfo.serviceinfoapi.DTO.SaveFlightDTO;
import com.homeify.serviceinfo.serviceinfoapi.Mapper.FlightDTOMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/api/flight")
public class FlightController {

//    private final FlightUsecase flightUsecase;
//    private final FlightDTOMapper flightDTOMapper;
//
//    public FlightController(FlightUsecase flightUsecase, FlightDTOMapper flightDTOMapper) {
//        this.flightUsecase = flightUsecase;
//        this.flightDTOMapper = flightDTOMapper;
//    }
//
//    //get all
//    @GetMapping("/getAll")
//    public List<FlightDTO> getAll() {
//
//        List<Flight> flights = flightUsecase.getAllFlight();
//
//        //chuyển từ List<Flight> sang List<FlightDTO>
//        //dùng mapper
//        return flightDTOMapper.toFlightDTOs(flights);
//
//    }
//
//    //thêm
//    @PostMapping("/add")
//    public FlightDTO add(@RequestBody SaveFlightDTO flightDTO) {
//
//        Flight flight = flightDTOMapper.toFlight(flightDTO);
//
//        flight = flightUsecase.addFlight(flight);
//
//        return flightDTOMapper.toFlightDTO(flight);
//
//    }
//
//    //sửa
//    @PutMapping("/update/{id}")
//    public FlightDTO update(@RequestBody SaveFlightDTO flightDTO, @PathVariable String id) {
//
//        Flight flight = flightDTOMapper.toFlight(flightDTO);
//
//        flight = flightUsecase.updateFlight(flight, id);
//
//        return flightDTOMapper.toFlightDTO(flight);
//
//    }
//
//    //xóa
//    @DeleteMapping("/delete/{id}")
//    public void delete(@PathVariable String id) {
//
//        flightUsecase.deleteFlight(id);
//
//    }
}
