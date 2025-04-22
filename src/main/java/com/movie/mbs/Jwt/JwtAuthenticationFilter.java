package com.movie.mbs.Jwt;

import com.movie.mbs.Repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String username;


        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        //extract jwt token from the header
        jwtToken = authHeader.substring(7);// remove the starting 7 string word then extract the token
        username = jwtService.extractUsername(jwtToken);

        //check if we have a username and no login status has not been set then authentication  this user and set the user in securitycontextholder
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userdetails = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            //validate the token
            if (jwtService.isTokenValid(jwtToken, userdetails)) {
                //create the authenctcication with roles

                List<SimpleGrantedAuthority> authorities = userdetails.getRoles().stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList();

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userdetails, null, authorities);

                //set authentication    details
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //update the security context with authentication
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response); // ✔️ correct spelling
    }
}
