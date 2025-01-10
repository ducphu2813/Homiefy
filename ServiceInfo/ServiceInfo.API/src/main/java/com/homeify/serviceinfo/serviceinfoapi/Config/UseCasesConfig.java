package com.homeify.serviceinfo.serviceinfoapi.Config;

import com.homeify.serviceinfo.Adapter.CityAdapter;
import com.homeify.serviceinfo.AdapterImpl.CityAdapterImpl;
import com.homeify.serviceinfo.Mapper.CityMapper;
import com.homeify.serviceinfo.Repository.CityRepository;
import com.homeify.serviceinfo.UseCases.CityUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    //đăng ký adapter
    @Bean
    public CityAdapter cityAdapter (CityRepository cityRepository
                                    , CityMapper cityMapper)
    {
        return new CityAdapterImpl(cityRepository, cityMapper);
    }

    //đăng ký use case
    @Bean
    public CityUsecase cityUsecase (CityAdapter cityAdapter){
        return new CityUsecase(cityAdapter);
    }
}
