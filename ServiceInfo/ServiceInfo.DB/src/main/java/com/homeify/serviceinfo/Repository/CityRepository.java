package com.homeify.serviceinfo.Repository;

import com.homeify.serviceinfo.Model.CityModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends MongoRepository<CityModel, String> {
    //tìm id cuối cùng
    CityModel findTopByOrderByIdDesc();
}
