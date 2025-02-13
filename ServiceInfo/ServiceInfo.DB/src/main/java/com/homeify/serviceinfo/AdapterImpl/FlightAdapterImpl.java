package com.homeify.serviceinfo.AdapterImpl;

import com.homeify.serviceinfo.Adapter.FlightAdapter;
import com.homeify.serviceinfo.Entities.Airport;
import com.homeify.serviceinfo.Entities.City;
import com.homeify.serviceinfo.Entities.Flight;
import com.homeify.serviceinfo.Entities.FlightInfo;
import com.homeify.serviceinfo.Mapper.AirportMapper;
import com.homeify.serviceinfo.Mapper.CityMapper;
import com.homeify.serviceinfo.Mapper.FlightInfoMapper;
import com.homeify.serviceinfo.Mapper.FlightMapper;
import com.homeify.serviceinfo.Model.AirportModel;
import com.homeify.serviceinfo.Model.CityModel;
import com.homeify.serviceinfo.Model.FlightInfoModel;
import com.homeify.serviceinfo.Model.FlightModel;
import com.homeify.serviceinfo.Repository.AirportRepository;
import com.homeify.serviceinfo.Repository.CityRepository;
import com.homeify.serviceinfo.Repository.FlightInfoRepository;
import com.homeify.serviceinfo.Repository.FlightRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FlightAdapterImpl implements FlightAdapter {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    //inject repo của flight info và mapper của flight info
    private final FlightInfoRepository flightInfoRepository;
    private final FlightInfoMapper flightInfoMapper;

    //inject repo của airport và mapper của airport
    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    //inject repo của city và mapper của city
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public FlightAdapterImpl(FlightRepository flightRepository
                                , FlightMapper flightMapper
                                , FlightInfoRepository flightInfoRepository
                                , FlightInfoMapper flightInfoMapper
                                , AirportRepository airportRepository
                                , AirportMapper airportMapper
                                , CityRepository cityRepository
                                , CityMapper cityMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
        this.flightInfoRepository = flightInfoRepository;
        this.flightInfoMapper = flightInfoMapper;
        this.airportRepository = airportRepository;
        this.airportMapper = airportMapper;
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }


    @Override
    @Transactional
    public Flight addFlight(Flight flight) {
        //dùng mapper để chuyển từ entity sang model
        FlightModel flightModel = flightMapper.toFlightModel(flight);

        //tự động tạo id
        flightModel.setId(generateId());

        //set id của flight info cho flight
        flightModel.setFlightInfoId(flight.getFlightInfo().getId());

        //lưu vào db
        flightModel = flightRepository.save(flightModel);

        //dùng mapper để chuyển từ model sang entity
        return flightMapper.toFlight(flightModel);
    }

    @Override
    @Transactional
    public Flight updateFlight(Flight flight, String flightId) {
        //tìm theo id
        FlightModel flightModel = flightRepository.findById(flightId).orElse(null);

        if (flightModel == null) {
            return null;
        }

        //dùng mapper
        flightModel = flightMapper.toFlightModel(flight);

        //set id của flight info cho flight
        flightModel.setFlightInfoId(flight.getFlightInfo().getId());

        flightModel = flightRepository.save(flightModel);

        return flightMapper.toFlight(flightModel);
    }

    @Override
    @Transactional
    public void deleteFlight(String flightId) {
        flightRepository.deleteById(flightId);
    }

    @Override
    public List<Flight> getAllFlight() {
        List<FlightModel> flightModels = flightRepository.findAll();

        //lấy tất cả flightInfoId từ flightModels
        List<String> flightInfoIds = flightModels.stream()
                .map(FlightModel::getFlightInfoId)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        //lấy tất cả flightInfo tương ứng
        List<FlightInfoModel> flightInfoModels = flightInfoRepository.findByIdIn(flightInfoIds);

        // lấy tất cả departureAirportId và arrivalAirportId
        List<String> airportIds = flightInfoModels.stream()
                .flatMap(flightInfoModel -> Stream.of(
                        flightInfoModel.getDepartureAirportId(),
                        flightInfoModel.getArrivalAirportId()
                ))
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        // lấy tất cả AirportModel tương ứng
        List<AirportModel> airportModels = airportRepository.findByIdIn(airportIds);

        // lấy tất cả cityId từ các AirportModel
        List<String> cityIds = airportModels.stream()
                .map(AirportModel::getCityId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        // lấy tất cả CityModel tương ứng và chuyển sang Map<cityId, City>
        Map<String, City> cityMap = cityRepository.findAllByIdIn(cityIds)
                .stream()
                .map(cityMapper::toCity)  // Sử dụng CityMapper để chuyển CityModel -> City
                .collect(Collectors.toMap(City::getId, Function.identity()));

        // chuyển AirportModel sang Airport và set City dùng Map
        Map<String, Airport> airportMap = airportModels.stream()
                .map(airportModel -> {
                    Airport airport = airportMapper.toAirport(airportModel);
                    airport.setCity(cityMap.get(airportModel.getCityId()));
                    return airport;
                })
                .collect(Collectors.toMap(Airport::getId, Function.identity()));

        // chuyển flightInfoModels sang flightInfo và set Airport dùng Map
        Map<String, FlightInfo> flightInfoMap = flightInfoModels.stream()
                .map(flightInfoModel -> {
                    FlightInfo flightInfo = flightInfoMapper.toFlightInfo(flightInfoModel);
                    flightInfo.setDepartureAirport(airportMap.get(flightInfoModel.getDepartureAirportId()));
                    flightInfo.setArrivalAirport(airportMap.get(flightInfoModel.getArrivalAirportId()));
                    return flightInfo;
                })
                .collect(Collectors.toMap(FlightInfo::getId, Function.identity()));

        // chuyển flightModels sang flight và set flightInfo dùng Map
        return flightModels.stream()
                .map(flightModel -> {
                    Flight flight = flightMapper.toFlight(flightModel);
                    flight.setFlightInfo(flightInfoMap.get(flightModel.getFlightInfoId()));
                    return flight;
                })
                .collect(Collectors.toList());

    }

    @Override
    public Flight findFlightById(String flightId) {
        FlightModel flightModel = flightRepository.findById(flightId).orElse(null);

        if (flightModel == null) {
            return null;
        }

        //set Flight Info cho flight
        FlightInfoModel flightInfoModel = flightInfoRepository.findById(flightModel.getFlightInfoId()).orElse(null);

        //set Airport cho flight info
        assert flightInfoModel != null;
        AirportModel departureAirport = airportRepository.findById(flightInfoModel.getDepartureAirportId()).orElse(null);
        AirportModel arrivalAirport = airportRepository.findById(flightInfoModel.getArrivalAirportId()).orElse(null);

        //set City cho Airport
        assert departureAirport != null;
        CityModel departureCity = cityRepository.findById(departureAirport.getCityId()).orElse(null);
        assert arrivalAirport != null;
        CityModel arrivalCity = cityRepository.findById(arrivalAirport.getCityId()).orElse(null);

        //chuyển CityModel sang City
        City departureCityEntity = cityMapper.toCity(departureCity);
        City arrivalCityEntity = cityMapper.toCity(arrivalCity);

        //chuyển AirportModel sang Airport
        Airport departureAirportEntity = airportMapper.toAirport(departureAirport);
        Airport arrivalAirportEntity = airportMapper.toAirport(arrivalAirport);
        departureAirportEntity.setCity(departureCityEntity);
        arrivalAirportEntity.setCity(arrivalCityEntity);

        //chuyển FlightInfoModel sang FlightInfo
        FlightInfo flightInfoModelEntity = flightInfoMapper.toFlightInfo(flightInfoModel);
        flightInfoModelEntity.setDepartureAirport(departureAirportEntity);
        flightInfoModelEntity.setArrivalAirport(arrivalAirportEntity);

        //chuyển FlightModel sang Flight
        Flight flightEntity = flightMapper.toFlight(flightModel);
        flightEntity.setFlightInfo(flightInfoModelEntity);

        return flightEntity;
    }

    //tự động tạo id(FL0001, FL0002, ...)
    private String generateId() {
        //tìm id cuối cùng
        FlightModel flightModel = flightRepository.findTopByOrderByIdDesc();
        if (flightModel == null) {
            return "FL0001";
        }
        String id = flightModel.getId();
        String prefix = id.substring(0, 2);
        int number = Integer.parseInt(id.substring(2));
        number++;
        return prefix + String.format("%04d", number);
    }

}
