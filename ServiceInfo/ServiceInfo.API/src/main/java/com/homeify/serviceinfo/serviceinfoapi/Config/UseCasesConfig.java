package com.homeify.serviceinfo.serviceinfoapi.Config;

import com.homeify.serviceinfo.Adapter.AirportAdapter;
import com.homeify.serviceinfo.Adapter.CityAdapter;
import com.homeify.serviceinfo.Adapter.FlightAdapter;
import com.homeify.serviceinfo.AdapterImpl.AirportAdapterImpl;
import com.homeify.serviceinfo.AdapterImpl.CityAdapterImpl;
import com.homeify.serviceinfo.AdapterImpl.FlightAdapterImpl;
import com.homeify.serviceinfo.Mapper.AirportMapper;
import com.homeify.serviceinfo.Mapper.CityMapper;
import com.homeify.serviceinfo.Mapper.FlightMapper;
import com.homeify.serviceinfo.Repository.AirportRepository;
import com.homeify.serviceinfo.Repository.CityRepository;
import com.homeify.serviceinfo.Repository.FlightRepository;
import com.homeify.serviceinfo.UseCases.AirportUsecase;
import com.homeify.serviceinfo.UseCases.CityUsecase;
import com.homeify.serviceinfo.UseCases.FlightUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    //đăng ký adapter

    //adapter của city
    @Bean
    public CityAdapter cityAdapter (CityRepository cityRepository
                                    , CityMapper cityMapper)
    {
        return new CityAdapterImpl(cityRepository, cityMapper);
    }

    //adapter của flight
    @Bean
    public FlightAdapter flightAdapter (FlightRepository flightRepository
                                    , FlightMapper flightMapper)
    {
        return new FlightAdapterImpl(flightRepository, flightMapper);
    }

    //adapter của airport
     @Bean
    public AirportAdapter airportAdapter (AirportRepository airportRepository
                                    , AirportMapper airportMapper
                                    , CityRepository cityRepository
                                    , CityMapper cityMapper)
    {
        return new AirportAdapterImpl(airportRepository, airportMapper, cityRepository, cityMapper);
    }
    //đăng ký use case

    //use case của city
    @Bean
    public CityUsecase cityUsecase (CityAdapter cityAdapter){
        return new CityUsecase(cityAdapter);
    }

    //use case của flight
    @Bean
    public FlightUsecase flightUsecase (FlightAdapter flightAdapter){
        return new FlightUsecase(flightAdapter);
    }

    //use case của airport
    @Bean
    public AirportUsecase airportUsecase (AirportAdapter airportAdapter){
        return new AirportUsecase(airportAdapter);
    }
}
