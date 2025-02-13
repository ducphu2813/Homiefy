package com.homeify.serviceinfo.AdapterImpl;

import com.homeify.serviceinfo.Adapter.FlightInfoAdapter;
import com.homeify.serviceinfo.Entities.Airport;
import com.homeify.serviceinfo.Entities.City;
import com.homeify.serviceinfo.Entities.FlightInfo;
import com.homeify.serviceinfo.Mapper.AirportMapper;
import com.homeify.serviceinfo.Mapper.CityMapper;
import com.homeify.serviceinfo.Mapper.FlightInfoMapper;
import com.homeify.serviceinfo.Model.AirportModel;
import com.homeify.serviceinfo.Model.FlightInfoModel;
import com.homeify.serviceinfo.Repository.AirportRepository;
import com.homeify.serviceinfo.Repository.CityRepository;
import com.homeify.serviceinfo.Repository.FlightInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FlightInfoAdapterImpl implements FlightInfoAdapter {

    private final FlightInfoRepository flightInfoRepository;
    private final FlightInfoMapper flightInfoMapper;

    //inject repo của airport và mapper của airport
    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    //inject repo của city và mapper của city
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public FlightInfoAdapterImpl(FlightInfoRepository flightInfoRepository
                                    , FlightInfoMapper flightInfoMapper
                                    , AirportRepository airportRepository
                                    , AirportMapper airportMapper
                                    , CityRepository cityRepository
                                    , CityMapper cityMapper) {
        this.flightInfoRepository = flightInfoRepository;
        this.flightInfoMapper = flightInfoMapper;
        this.airportRepository = airportRepository;
        this.airportMapper = airportMapper;
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    @Override
    @Transactional
    public FlightInfo addFlightInfo(FlightInfo flightInfo) {
        //chuyển từ FlightInfo sang FlightInfoModel
        FlightInfoModel flightInfoModel = flightInfoMapper.toFlightInfoModel(flightInfo);

        //tự động tạo id
        flightInfoModel.setId(generateId());

        //lưu vào db
        flightInfoModel.setDepartureAirportId(flightInfo.getDepartureAirport().getId());
        flightInfoModel.setArrivalAirportId(flightInfo.getArrivalAirport().getId());
        flightInfoModel = flightInfoRepository.save(flightInfoModel);

        //chuyển từ FlightInfoModel sang FlightInfo
        return flightInfoMapper.toFlightInfo(flightInfoModel);
    }

    @Override
    @Transactional
    public FlightInfo updateFlightInfo(FlightInfo flightInfo, String flightInfoId) {
        FlightInfoModel flightInfoModel = flightInfoRepository.findById(flightInfoId).orElse(null);

        if (flightInfoModel == null) {
            return null;
        }

        //lưu vào db
        flightInfoModel.setDepartureAirportId(flightInfo.getDepartureAirport().getId());
        flightInfoModel.setArrivalAirportId(flightInfo.getArrivalAirport().getId());
        flightInfoModel = flightInfoRepository.save(flightInfoModel);

        return flightInfoMapper.toFlightInfo(flightInfoModel);
    }

    @Override
    @Transactional
    public void deleteFlightInfo(String flightInfoId) {
        flightInfoRepository.deleteById(flightInfoId);
    }

    @Override
    public List<FlightInfo> getAllFlightInfo() {
        List<FlightInfoModel> flightInfoModels = flightInfoRepository.findAll();

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

        // chuyển FlightInfoModel sang FlightInfo và set Airport dùng Map
        return flightInfoModels.stream()
                .map(flightInfoModel -> {
                    FlightInfo flightInfo = flightInfoMapper.toFlightInfo(flightInfoModel);
                    flightInfo.setDepartureAirport(airportMap.get(flightInfoModel.getDepartureAirportId()));
                    flightInfo.setArrivalAirport(airportMap.get(flightInfoModel.getArrivalAirportId()));
                    return flightInfo;
                })
                .collect(Collectors.toList());

    }

    @Override
    public FlightInfo findFlightInfoById(String flightInfoId) {
        FlightInfoModel flightInfoModel = flightInfoRepository.findById(flightInfoId).orElse(null);
        if (flightInfoModel == null) {
            return null;
        }

        AirportModel departureAirportModel = airportRepository.findById(flightInfoModel.getDepartureAirportId()).orElse(null);
        Airport departureAirport = airportMapper.toAirport(departureAirportModel);
        City departureCity = cityMapper.toCity(cityRepository.findById(departureAirportModel.getCityId()).orElse(null));
        departureAirport.setCity(departureCity);

        AirportModel arrivalAirportModel = airportRepository.findById(flightInfoModel.getArrivalAirportId()).orElse(null);
        Airport arrivalAirport = airportMapper.toAirport(airportRepository.findById(flightInfoModel.getArrivalAirportId()).orElse(null));
        City arrivalCity = cityMapper.toCity(cityRepository.findById(arrivalAirportModel.getCityId()).orElse(null));
        arrivalAirport.setCity(arrivalCity);

        FlightInfo flightInfo = flightInfoMapper.toFlightInfo(flightInfoModel);
        flightInfo.setDepartureAirport(departureAirport);
        flightInfo.setArrivalAirport(arrivalAirport);

        return flightInfo;
    }

    //tự động tạo id(FI001, FI002, ...)
    private String generateId() {
        List<FlightInfoModel> flightInfoModels = flightInfoRepository.findAll();
        int max = 0;
        for (FlightInfoModel flightInfoModel : flightInfoModels) {
            String id = flightInfoModel.getId();
            int number = Integer.parseInt(id.substring(2));
            if (number > max) {
                max = number;
            }
        }
        return "FI" + String.format("%03d", max + 1);
    }
}
