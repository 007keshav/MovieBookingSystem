package com.movie.mbs.Service.ServiceIMPL;

import com.movie.mbs.DTO.LoginRequestDTO;
import com.movie.mbs.DTO.LoginResponseDTO;
import com.movie.mbs.DTO.RegisterRequestDTO;
import com.movie.mbs.Entity.User;
import com.movie.mbs.Repository.UserRepository;
import com.movie.mbs.Jwt.JwtService;
import com.movie.mbs.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class AuthenticationServiceIMPL implements AuthenticationService {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;



    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public User registerNormalUser(RegisterRequestDTO registerRequestDTO){
         if(userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()){
             throw new RuntimeException("user already registered");
         }

        Set<String> roles = new HashSet<>();
         roles.add("ROLE_USER");

         User user  = new User();
         user.setUsername(registerRequestDTO.getUsername());
         user.setEmailAddress(registerRequestDTO.getEmailAddress());
         user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
         user.setMobileNumber(registerRequestDTO.getMobileNumber());
         user.setRoles(roles);

         return userRepository.save(user);
    }


    @Value("${admin.secret-key}")
    private String adminSecretKey;


    public User registerAdminUser(RegisterRequestDTO registerRequestDTO){
        if (!adminSecretKey.equals(registerRequestDTO.getAdminKey())) {
            throw new RuntimeException("Unauthorized attempt to register as admin!");
        }

        if(userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()){
             throw new RuntimeException("user already registered");
         }

        Set<String> roles = new HashSet<>();
         roles.add("ROLE_USER");
         roles.add("ROLE_ADMIN");

         User user  = new User();
         user.setUsername(registerRequestDTO.getUsername());
         user.setEmailAddress(registerRequestDTO.getEmailAddress());
         user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
         user.setMobileNumber(registerRequestDTO.getMobileNumber());
         user.setRoles(roles);

         return userRepository.save(user);
    }



    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){
        User user = userRepository.findByUsername(loginRequestDTO.getUsername())
                .orElseThrow(()-> new RuntimeException( "User not found"));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword()
                )
        );
        String token = jwtService.genrateToken(user);
        return LoginResponseDTO.builder()
                .jwtToken(token)
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();
    }



}
