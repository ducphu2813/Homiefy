package com.homeify.serviceinfo.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Airport {

    private String id;
    private String name;
    private City city;
    private String address;
}
