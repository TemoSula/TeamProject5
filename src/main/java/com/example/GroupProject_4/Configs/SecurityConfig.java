package com.example.GroupProject_4.Configs;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, @Qualifier("myCustomAuthFilter")OncePerRequestFilter myCustomAuth) throws Exception {
        return http.csrf(custom -> custom.disable())
                .addFilterBefore(myCustomAuth, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(custom->custom.requestMatchers("/login","/whoamI","/register","/swagger-ui/**",
                        "/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui.html", "/webjars/**").permitAll().anyRequest().authenticated())
                .build();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
