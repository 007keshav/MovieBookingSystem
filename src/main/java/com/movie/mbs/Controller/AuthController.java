package com.movie.mbs.Controller;


import com.movie.mbs.DTO.LoginRequestDTO;
import com.movie.mbs.DTO.LoginResponseDTO;
import com.movie.mbs.DTO.RegisterRequestDTO;
import com.movie.mbs.Entity.User;
import com.movie.mbs.Service.AuthenticationService;
import com.movie.mbs.Service.ServiceIMPL.AuthenticationServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/registernormaluser")
    public ResponseEntity<User> registerNormalUser(@RequestBody RegisterRequestDTO registerRequestDTO){
        return ResponseEntity.ok(authenticationService.registerNormalUser(registerRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(authenticationService.login(loginRequestDTO));
    }

    @PostMapping("/registeradminuser")
    public ResponseEntity<User> registerAdminUser(@RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(authenticationService.registerAdminUser(request));
    }
//    @PostMapping("/register-admin")
//    public ResponseEntity<User> registerAdmin(@RequestBody RegisterRequestDTO request) {
//        return ResponseEntity.ok(authenticationService.registerAdminUser(request));
//    }


}
