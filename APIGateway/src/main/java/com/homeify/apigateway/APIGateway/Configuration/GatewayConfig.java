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
                .route("auth_users_route", r -> r.path("/api/users/**")     // định nghĩa route đến 1 controller cụ thể
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))   // add filter jwt
                        .uri("lb://Auth.API"))  // tên service đăng ký trên eureka

                .route("auth_roles_route", r -> r.path("/api/roles/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://Auth.API"))

                .route("auth_auth_route", r -> r.path("/api/auth/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://Auth.API"))

                .route("service_info_city_route", r -> r.path("/api/city/**")
//                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://ServiceInfo.API"))

                .build();

    }

}
