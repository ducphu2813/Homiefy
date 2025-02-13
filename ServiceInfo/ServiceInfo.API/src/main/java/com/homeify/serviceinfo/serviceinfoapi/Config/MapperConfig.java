package com.homeify.serviceinfo.serviceinfoapi.Config;

import com.homeify.serviceinfo.Mapper.AirportMapper;
import com.homeify.serviceinfo.Mapper.CityMapper;
import com.homeify.serviceinfo.Mapper.FlightInfoMapper;
import com.homeify.serviceinfo.Mapper.FlightMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    //đăng ký bean city mapper
    @Bean
    public CityMapper cityMapper()
    {
        return Mappers.getMapper(CityMapper.class);
    }

    //đăng ký bean flight mapper
    @Bean
    public FlightMapper flightMapper()
    {
        return Mappers.getMapper(FlightMapper.class);
    }

    //đăng ký bean airport mapper
    @Bean
    public AirportMapper airportMapper()
    {
        return Mappers.getMapper(AirportMapper.class);
    }

    //đăng ký bean flight info mapper
    @Bean
    public FlightInfoMapper flightInfoMapper()
    {
        return Mappers.getMapper(FlightInfoMapper.class);
    }
}
