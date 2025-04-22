package com.movie.mbs.Controller;


import com.movie.mbs.DTO.RegisterRequestDTO;
import com.movie.mbs.Entity.User;
import com.movie.mbs.Service.AuthenticationService;
import com.movie.mbs.Service.ServiceIMPL.AuthenticationServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
//@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private AuthenticationService authenticationService;

    //  Allow registration without token
    @PostMapping("/registeradminuser")
    public ResponseEntity<User> registerAdminUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.ok(authenticationService.registerAdminUser(registerRequestDTO));
    }

    //  Other methods require ADMIN role
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/dashboard")
    public String getAdminDashboard() {
        return "Welcome Admin";
    }
}
