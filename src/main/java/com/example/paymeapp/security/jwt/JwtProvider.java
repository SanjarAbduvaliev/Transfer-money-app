package com.example.paymeapp.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtProvider {
    @Value("${jwt.secret}")
    static  String secretKey="maxfiySuzhckimBilmasin1234";


    public String generateToken(String username){
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1_000_000_000L))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        return "Bearer "+token;
    }
    //TOKEN YAROQLI EKANLIGINI AYTADI
    public boolean validateToken(String token){
        try {

            Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    // tokenni pars qiluvchi metod (ENCODE)
    public String getUserNameFromToken(String token){
        String subject = Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return subject;
    }

}
