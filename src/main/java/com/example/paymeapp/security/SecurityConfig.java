package com.example.paymeapp.security;

import com.example.paymeapp.security.jwt.JwtFilter;
import com.example.paymeapp.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    JwtFilter  jwtFilter;
    protected  void configur(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(myUserDetailsService);

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {



      http
              .csrf().disable()
              .authorizeHttpRequests()
              .requestMatchers("/","/api/auth/login").permitAll()
              .anyRequest().authenticated();
      // UsernamePasswordAuthenticationFilter clasdan oldin jwtFilter ishlasin
      http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

      //SPRING SECURITYNI SESSIONIGA USHLAB QOLMASLIGINI TAMINLAYDI YA'NI SESSIONGA SAQLANIB QOLMAYDI
      http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


      return http.build();


    }
    @Bean
    PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
