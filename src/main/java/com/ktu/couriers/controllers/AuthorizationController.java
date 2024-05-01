package com.ktu.couriers.controllers;

import com.ktu.couriers.models.Credentials;
import com.ktu.couriers.models.User;
import com.ktu.couriers.security.CustomUserDetailsService;
import com.ktu.couriers.security.TokenBlacklistService;
import com.ktu.couriers.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class AuthorizationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @GetMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            tokenBlacklistService.blacklistToken(token);
        }
        return ResponseEntity.ok("You have been logged out successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<User> createAuthenticationToken(@RequestBody Credentials credentials) {
        try {
            log.debug("Logging in user: {}, password: {}", credentials.getEmail(), credentials.getPassword());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            if (userDetails == null) {
                return ResponseEntity.badRequest().build();
            }
            final String jwt = jwtUtil.generateToken(userDetails.getUsername());
            User user = userDetailsService.loadLoggedInUser(userDetails.getUsername());

            user.setToken(jwt);
            user.setPassword(null);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
