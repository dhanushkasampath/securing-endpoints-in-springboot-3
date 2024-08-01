package com.example.apilogin.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    //before spring boot 3 we had to extend WebConfigurationAdapter.
    // but in spring 3 we need to config a new bean

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final CustomUserDetailService customUserDetailService;

    private final UnauthorizedHandler unauthorizedHandler;

    @Bean//rest apis should be stateless. this is where we config that
    public SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception {
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);//make the spring aware about the filter

        http
            .securityMatcher("/**")
            .authorizeHttpRequests(registry->registry
                .requestMatchers("/").permitAll()// Highest restrictive should be first, Least restrictive should be least
                .requestMatchers("/actuator/**").permitAll()
                .requestMatchers("/auth/login").permitAll()//allowing access to this endpoint
                .requestMatchers("/admin/**").hasRole("ADMIN")// why it does not work when I change this value to ABC and set role as ABC in UserService
                .anyRequest().authenticated())

            .cors().disable()
            .csrf().disable()
            .formLogin().disable()
            .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailService)
                .passwordEncoder(passwordEncoder())
                .and().build();
    }

}