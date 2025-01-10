package com.homeify.auth.authapi.Configuration;

import com.homeify.auth.Mapper.UsersMapper;
import com.homeify.auth.authapi.Mapper.UserDTOMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    //đăng ký bean user mapper
    @Bean
    public UsersMapper userMapper()
    {
        return Mappers.getMapper(UsersMapper.class);
    }
}
