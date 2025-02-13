package com.homeify.serviceinfo.Repository;

import com.homeify.serviceinfo.Model.AirportModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends MongoRepository<AirportModel, String> {

    //tìm id cuối cùng
    AirportModel findTopByOrderByIdDesc();

    //tìm theo danh sách id
    List<AirportModel> findByIdIn(List<String> ids);
}
