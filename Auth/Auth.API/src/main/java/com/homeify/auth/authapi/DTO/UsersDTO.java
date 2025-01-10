package com.homeify.auth.authapi.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDTO {

    private String id;

    private String fullname;

    private String username;

    private String password;

    private String email;
}
