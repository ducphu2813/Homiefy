package com.homeify.serviceinfo.AdapterImpl;

import com.homeify.serviceinfo.Adapter.FlightAdapter;
import com.homeify.serviceinfo.Entities.Flight;
import com.homeify.serviceinfo.Mapper.FlightMapper;
import com.homeify.serviceinfo.Model.FlightModel;
import com.homeify.serviceinfo.Repository.FlightRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FlightAdapterImpl implements FlightAdapter {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    public FlightAdapterImpl(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }


    @Override
    @Transactional
    public Flight addFlight(Flight flight) {
        //dùng mapper để chuyển từ entity sang model
        FlightModel flightModel = flightMapper.toFlightModel(flight);

        //tự động tạo id
        flightModel.setId(generateId());

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

        return flightMapper.toFlight(flightModels);
    }

    @Override
    public Flight findFlightById(String flightId) {
        FlightModel flightModel = flightRepository.findById(flightId).orElse(null);

        return flightMapper.toFlight(flightModel);
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
