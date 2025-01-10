package com.homeify.auth.authapi.Configuration;

import com.homeify.auth.Adapter.UsersAdapter;
import com.homeify.auth.DB.UsersAdapterImpl;
import com.homeify.auth.DB.UsersRepository;
import com.homeify.auth.Mapper.UsersMapper;
import com.homeify.auth.UseCases.UserUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    //đăng ký bean user adapter
    @Bean
    public UsersAdapter usersAdapter (UsersRepository usersRepository
                                        , UsersMapper usersMapper)
    {
        return new UsersAdapterImpl(usersRepository, usersMapper);
    }

    //bean user use case
    @Bean
    public UserUsecase userUsecase (UsersAdapter usersAdapter){
        return new UserUsecase(usersAdapter);
    }
}
