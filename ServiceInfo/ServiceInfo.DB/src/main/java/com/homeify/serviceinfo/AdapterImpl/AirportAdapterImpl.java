package com.homeify.serviceinfo.AdapterImpl;

import com.homeify.serviceinfo.Adapter.AirportAdapter;
import com.homeify.serviceinfo.Entities.Airport;
import com.homeify.serviceinfo.Entities.City;
import com.homeify.serviceinfo.Mapper.AirportMapper;
import com.homeify.serviceinfo.Mapper.CityMapper;
import com.homeify.serviceinfo.Model.AirportModel;
import com.homeify.serviceinfo.Repository.AirportRepository;
import com.homeify.serviceinfo.Repository.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AirportAdapterImpl implements AirportAdapter {

    private final AirportRepository airportRepository;
    private final CityRepository cityRepository;
    private final AirportMapper airportMapper;
    private final CityMapper cityMapper;

    public AirportAdapterImpl(AirportRepository airportRepository
                                , AirportMapper airportMapper
                                , CityRepository cityRepository
                                , CityMapper cityMapper) {
        this.airportRepository = airportRepository;
        this.airportMapper = airportMapper;
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }


    @Override
    @Transactional
    public Airport addAirport(Airport airport) {
        AirportModel airportModel = airportMapper.toAirportModel(airport);

        //tự động tạo id
        airportModel.setId(generateId());

        airportModel.setCityId(airport.getCity().getId());

        airportModel = airportRepository.save(airportModel);

        Airport addedAirport = airportMapper.toAirport(airportModel);
        addedAirport.setCity(cityMapper.toCity(cityRepository.findById(airportModel.getCityId()).orElse(null)));

        return addedAirport;
    }

    @Override
    @Transactional
    public Airport updateAirport(Airport airport, String airportId) {
        //tìm theo id
        AirportModel airportModel = airportRepository.findById(airportId).orElse(null);

        if (airportModel == null) {
            return null;
        }

        //dùng mapper
        airportModel = airportMapper.toAirportModel(airport);

        airportModel.setCityId(airport.getCity().getId());

        airportModel = airportRepository.save(airportModel);

        Airport updatedAirport = airportMapper.toAirport(airportModel);
        updatedAirport.setCity(cityMapper.toCity(cityRepository.findById(airportModel.getCityId()).orElse(null)));

        return updatedAirport;
    }

    @Override
    @Transactional
    public void deleteAirport(String airportId) {
        airportRepository.deleteById(airportId);
    }

    @Override
    public List<Airport> getAllAirport() {
        List<AirportModel> airportModels = airportRepository.findAll();

        //lấy danh sách city id từ airportModels
        List<String> cityIds = airportModels.stream()
                .map(airportModel -> airportModel.getCityId())
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        //lấy danh sách city từ cityIds
        Map<String, City> cityMap = cityRepository.findAllByIdIn(cityIds)
                .stream()
                .map(cityMapper::toCity)  // sử dụng CityMapper để chuyển CityModel -> City
                .collect(Collectors.toMap(City::getId, Function.identity()));

        //chuyển từ List<AirportModel> sang List<Airport>
        //cần chú ý chuyển cityId sang city
        //lấy danh sách airport và dùng stream để chuyển từ airportModel sang airport
        return airportModels.stream()
                .map(airportModel -> {
                    Airport airport = airportMapper.toAirport(airportModel);
                    airport.setCity(cityMap.get(airportModel.getCityId()));
                    return airport;
                })
                .collect(Collectors.toList());

//        return airports;
    }

    @Override
    public Airport findAirportById(String airportId) {
        AirportModel airportModel = airportRepository.findById(airportId).orElse(null);

        City city = cityMapper.toCity(cityRepository.findById(airportModel.getCityId()).orElse(null));

        Airport airport = airportMapper.toAirport(airportModel);
        airport.setCity(city);

        return airport;
    }

    //tự động tạo id(AP01, AP02, AP03,...)
    private String generateId() {
        AirportModel airport = airportRepository.findTopByOrderByIdDesc();

        if (airport == null) {
            return "AP01";
        }

        String id = airport.getId();
        String prefix = id.substring(0, 2);
        String suffix = id.substring(2);

        int number = Integer.parseInt(suffix);
        number++;

        return prefix + String.format("%02d", number);
    }
}
