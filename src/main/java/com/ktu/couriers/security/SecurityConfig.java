package com.ktu.couriers.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/login").permitAll()
                        // users endopoints
                        .requestMatchers(HttpMethod.GET, "/api/user").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/user/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/user").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/user/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/user/**").hasRole("ADMIN")

                        // pickup points endpoints pickup-point
                        .requestMatchers(HttpMethod.GET, "/api/pickup-point").hasAnyRole("ADMIN", "CLIENT")
                        .requestMatchers(HttpMethod.GET, "/api/pickup-point/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/pickup-point").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/pickup-point/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/pickup-point/**").hasRole("ADMIN")

                        // parcels endpoints parcel
                        .requestMatchers(HttpMethod.GET, "/api/parcel").hasAnyRole("ADMIN", "CLIENT", "COURIER")
                        .requestMatchers(HttpMethod.GET, "/api/parcel/**").hasAnyRole("ADMIN", "CLIENT", "COURIER")
                        .requestMatchers(HttpMethod.POST, "/api/parcel").hasAnyRole("ADMIN", "CLIENT")
                        .requestMatchers(HttpMethod.PUT, "/api/parcel/**").hasAnyRole("ADMIN", "CLIENT", "COURIER")
                        .requestMatchers(HttpMethod.DELETE, "/api/parcel/**").hasRole("ADMIN")

                        // routes endpoints route
                        .requestMatchers(HttpMethod.GET, "/api/route").hasAnyRole("ADMIN", "COURIER")
                        .requestMatchers(HttpMethod.GET, "/api/route/**").hasAnyRole("ADMIN", "COURIER")
                        .requestMatchers(HttpMethod.POST, "/api/route").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/route/**").hasAnyRole("ADMIN", "COURIER")
                        .requestMatchers(HttpMethod.DELETE, "/api/route/**").hasRole("ADMIN")

                        .anyRequest().authenticated())
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutSuccessUrl("/api/logout"));

        http.cors(cors -> cors
                .configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("http://localhost:4200"));
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(List.of("*"));
                    config.setAllowCredentials(true);
                    return config;
                })
        );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
