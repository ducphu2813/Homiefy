package com.homeify.serviceinfo.serviceinfoapi.Config;

import com.homeify.serviceinfo.Mapper.CityMapper;
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
}
