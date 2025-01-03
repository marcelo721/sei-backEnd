package com.marcelo721.SEI.components;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcelo721.SEI.web.exceptions.ErrorMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        try {
            response.getWriter().write(new ObjectMapper().writeValueAsString(new ErrorMessage(request, HttpStatus.UNAUTHORIZED,
                    "Unauthorized: " + authException.getMessage())));
        } catch (IOException e) {
            log.error("Error while writing response", e);
        }
    }
}

