package com.homeify.serviceinfo.Repository;

import com.homeify.serviceinfo.Model.AirportModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AirportRepository extends MongoRepository<AirportModel, String> {

    //tìm id cuối cùng
    AirportModel findTopByOrderByIdDesc();

}
