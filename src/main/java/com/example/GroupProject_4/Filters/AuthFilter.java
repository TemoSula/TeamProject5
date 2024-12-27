package com.example.GroupProject_4.Filters;

import com.example.GroupProject_4.Models.UserModel;
import com.example.GroupProject_4.Repositories.UserRepostory;
import com.example.GroupProject_4.Services.JwtService;
import com.example.GroupProject_4.enums.Roles;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.management.relation.Role;
import java.io.IOException;
import java.util.List;

@Component
@Qualifier("myCustomAuthFilter")
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepostory userRepo;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String AuthHeader = request.getHeader("Authorization");
        String token = "";
        if(AuthHeader == null || !AuthHeader.startsWith("Bearer "))
        {
            filterChain.doFilter(request,response);
            return;
        }
        token = AuthHeader.substring(7);
        if(jwtService.isExpirationToken(token))
        {
            filterChain.doFilter(request,response);
            return;
        }
        UserModel userModel = userRepo.findByUsername((String)jwtService.parseToken(token).get("username"));
        Authentication auth = new UsernamePasswordAuthenticationToken(userModel.getUserName(),userModel.getPassword(),
                List.of(new SimpleGrantedAuthority(userModel.getRoles().toString())));
       if(SecurityContextHolder.getContext().getAuthentication() == null || SecurityContextHolder.getContext().getAuthentication() !=null)
       {
           SecurityContextHolder.getContext().setAuthentication(auth);
       }
            filterChain.doFilter(request,response);
    }
}
