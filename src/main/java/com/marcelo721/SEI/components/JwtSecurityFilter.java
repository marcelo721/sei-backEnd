package com.marcelo721.SEI.components;


import com.marcelo721.SEI.entities.User;
import com.marcelo721.SEI.jwt.JwtUtils;
import com.marcelo721.SEI.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtSecurityFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extractToken(request);

        if (token!= null){
            String login = JwtUtils.isValidToken(token);
            User user = userRepository.findByEmail(login).orElse(null);
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("User authenticated: {} with roles: {}", user.getEmail(), user.getAuthorities());
        }
        filterChain.doFilter(request, response);
    }

    public String extractToken(HttpServletRequest request){

        var authHeader = request.getHeader("Authorization");
        if (authHeader == null)
            return null;

        if(!authHeader.split(" ")[0].equals("Bearer")){
            return null;
        }
        return authHeader.split(" ")[1];
    }
}
