package com.homeify.apigateway.APIGateway.Configuration;

import com.homeify.apigateway.APIGateway.Filter.AuthenticationFilter;
import com.homeify.apigateway.APIGateway.Filter.RouteValidator;
import com.homeify.apigateway.APIGateway.Service.JWTService;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public AuthenticationFilter authenticationFilter(JWTService jwtService
            , RouteValidator routeValidator) {
        return new AuthenticationFilter(jwtService, routeValidator);
    }

    //tạo route locator
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder
            , AuthenticationFilter authenticationFilter) {


        return builder.routes()
                .route("auth_service_route", r -> r.path("/api/auth/**")     // định nghĩa route đến 1 controller cụ thể
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))   // add filter jwt
                        .uri("lb://USER"))  // tên service đăng ký trên eureka

                .build();

    }

}
