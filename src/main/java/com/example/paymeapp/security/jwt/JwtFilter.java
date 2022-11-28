package com.example.paymeapp.security.jwt;

import com.example.paymeapp.controller.AuthController;
import com.example.paymeapp.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * keladigan token birinchi JwtFilterga keladi
 */

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        //REQUESTDAN TOKENNI OLISH
        String token = httpServletRequest.getHeader("Authorization");
        //TOKEN MAVJUDLIGI VA BEARER BN BOSHLANISHINI TEKSHIRISH
        if (token != null && token.startsWith("Bearer")) {
            token=token.substring(7);
            //TOKENNI VALIDATSIYADAN O'TKAZDIK (TOKEN BUZULMAGANLIGI, MUDDATI TUGAMAGANLIGI VA H.K)
            boolean validateToken = jwtProvider.validateToken(token);
            if (validateToken){
                //TOKENNI ICHIDAN USERNAME OLDIK
                String userNameFromToken = jwtProvider.getUserNameFromToken(token);
                //USERNAME ORQALI USERDETAILSNI OLDIK
                UserDetails userDetails = myUserDetailsService.loadUserByUsername(userNameFromToken);
                //USERDETAILS ORQALI AUNTHENTICATION YARATIB OLDIK
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());

                //SISTEMEGA KIM KIRGANLIGINI O'RNATIB QO'YDIK
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                System.out.println(userNameFromToken);
            }
        }
        //filterChain BOSHQA FILTERLARNI ISHLATISHNI BUYRUG'INI BERADI
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
