package com.movie.mbs.DTO;


import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
public class RegisterRequestDTO {

    private String username;
    private String password;
    private String emailAddress;
    private String mobileNumber;
//    private Set<String> roles;
    private String adminKey;
}
