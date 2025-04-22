package com.movie.mbs.Service;

import com.movie.mbs.DTO.LoginRequestDTO;
import com.movie.mbs.DTO.LoginResponseDTO;
import com.movie.mbs.DTO.RegisterRequestDTO;
import com.movie.mbs.Entity.User;

public interface AuthenticationService {
    User registerNormalUser(RegisterRequestDTO registerRequestDTO);
    User registerAdminUser(RegisterRequestDTO registerRequestDTO);
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
