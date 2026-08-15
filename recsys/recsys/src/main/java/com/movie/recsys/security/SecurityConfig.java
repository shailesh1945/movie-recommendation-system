package com.movie.recsys.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;


    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter
    ) {

        this.jwtAuthenticationFilter =
                jwtAuthenticationFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http

                // ==========================
                // CORS
                // ==========================

                .cors(cors -> {
                })


                // ==========================
                // CSRF
                // ==========================

                .csrf(csrf ->
                        csrf.disable()
                )


                // ==========================
                // STATELESS JWT
                // ==========================

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )


                // ==========================
                // AUTHORIZATION
                // ==========================

                .authorizeHttpRequests(auth -> auth

                        // ==========================
                        // CORS PREFLIGHT
                        // ==========================

                        .requestMatchers(
                                HttpMethod.OPTIONS,
                                "/**"
                        ).permitAll()


                        // ==========================
                        // PUBLIC AUTH APIs
                        // ==========================

                        .requestMatchers("/uploads/**").permitAll()
                        .requestMatchers(
                                "/api/auth/login",
                                "/api/auth/register"
                        ).permitAll()


                        // ==========================
                        // FRONTEND RESOURCES
                        // ==========================

                        .requestMatchers(
                                "/frontend/**",
                                "/assets/**",
                                "/assets/**"
                        ).permitAll()


                        // ==========================
                        // ADMIN APIs
                        // ==========================

                        .requestMatchers(
                                "/api/admin/**"
                        ).hasRole("ADMIN")


                        // ==========================
                        // EVERYTHING ELSE
                        // ==========================

                        .anyRequest()
                        .authenticated()
                )


                // ==========================
                // JWT FILTER
                // ==========================

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );


        return http.build();
    }
}