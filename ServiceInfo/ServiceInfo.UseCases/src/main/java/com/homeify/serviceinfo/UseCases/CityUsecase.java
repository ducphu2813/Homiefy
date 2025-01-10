package com.homeify.serviceinfo.UseCases;

import com.homeify.serviceinfo.Adapter.CityAdapter;
import com.homeify.serviceinfo.Entities.City;

import java.util.List;

public class CityUsecase {

    private final CityAdapter cityAdapter;

    public CityUsecase(CityAdapter cityAdapter) {
        this.cityAdapter = cityAdapter;
    }

    //thêm city
    public City addCity(City city) {
//        city.setId(String.valueOf(System.currentTimeMillis()));

        return cityAdapter.addCity(city);
    }

    //sửa city
    public City updateCity(City city, String cityId) {

        //tìm city theo id
        City cityById = cityAdapter.findCityById(cityId);

        city.setId(cityById.getId());

        return cityAdapter.updateCity(city, cityId);
    }

    //xóa city
    public void deleteCity(String cityId) {
        cityAdapter.deleteCity(cityId);
    }

    //lấy tat ca city
    public List<City> getAllCity() {
        return cityAdapter.getAllCity();
    }

    //tìm theo id
    public City findCityById(String cityId) {
        return cityAdapter.findCityById(cityId);
    }

}
