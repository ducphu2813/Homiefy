package com.homeify.serviceinfo.serviceinfoapi.Controller;

import com.homeify.serviceinfo.Entities.Airport;
import com.homeify.serviceinfo.Entities.City;
import com.homeify.serviceinfo.UseCases.AirportUsecase;
import com.homeify.serviceinfo.serviceinfoapi.DTO.Airport.AirportDTO;
import com.homeify.serviceinfo.serviceinfoapi.DTO.Airport.SaveAirportDTO;
import com.homeify.serviceinfo.serviceinfoapi.Mapper.AirportDTOMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airport")
public class AirportController {

    private final AirportUsecase airportUsecase;
    private final AirportDTOMapper airportDTOMapper;

    public AirportController(AirportUsecase airportUsecase
                            , AirportDTOMapper airportDTOMapper) {
        this.airportUsecase = airportUsecase;
        this.airportDTOMapper = airportDTOMapper;
    }

    //get all
    @GetMapping("/getAll")
    public List<AirportDTO> getAll() {

        List<Airport> airports = airportUsecase.getAllAirport();

        //chuyển từ List<Airport> sang List<AirportDTO>
        //dùng mapper
        return airportDTOMapper.toAirportDTOs(airports);

    }

    //thêm
    @PostMapping("/add")
    public AirportDTO add(@RequestBody SaveAirportDTO airportDTO) {

        Airport airport = airportDTOMapper.toAirport(airportDTO);

        //phần này lấy id từ dto rồi set vào entity
        City city = new City();
        city.setId(airportDTO.getCityId());
        airport.setCity(city);

        airport = airportUsecase.addAirport(airport);

        return airportDTOMapper.toAirportDTO(airport);

    }

    //sửa
    @PutMapping("/update/{id}")
    public AirportDTO update(@RequestBody SaveAirportDTO airportDTO, @PathVariable String id) {

        Airport airport = airportDTOMapper.toAirport(airportDTO);

        City city = new City();
        city.setId(airportDTO.getCityId());

        airport.setCity(city);

        airport = airportUsecase.updateAirport(airport, id);

        return airportDTOMapper.toAirportDTO(airport);

    }

    //xóa
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {

        airportUsecase.deleteAirport(id);

    }
}
