package com.movie.mbs.DTO;

import com.movie.mbs.Entity.Booking;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UserDTO {


    private Long id;

    private String username;

    private String password;

    private String emailAddress;

    private String mobileNumber;

    private Set<String> roles;

    private List<Booking> bookings;
}
