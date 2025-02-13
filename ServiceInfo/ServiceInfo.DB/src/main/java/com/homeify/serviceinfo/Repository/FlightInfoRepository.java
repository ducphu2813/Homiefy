package com.homeify.serviceinfo.Repository;

import com.homeify.serviceinfo.Model.FlightInfoModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightInfoRepository extends MongoRepository<FlightInfoModel, String> {
    //tìm id cuối cùng
    FlightInfoModel findTopByOrderByIdDesc();

    //tìm theo danh sách id
    List<FlightInfoModel> findByIdIn(List<String> ids);
}
