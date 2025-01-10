package com.homeify.auth.Entities;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Users {

    private String id;
    private String username;
    private String fullname;
    private String password;
    private String email;
}
