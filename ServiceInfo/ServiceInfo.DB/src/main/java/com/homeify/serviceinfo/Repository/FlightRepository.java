package com.homeify.serviceinfo.Repository;

import com.homeify.serviceinfo.Model.FlightModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends MongoRepository<FlightModel, String> {
    //tìm id cuối cùng
    FlightModel findTopByOrderByIdDesc();
}
