package com.project.WebMart.configuration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String role = authentication.getAuthorities().toString();

        if(role.contains("ADMIN")){
            response.sendRedirect("/Home.html");
        } else if (role.contains("USER")) {
            response.sendRedirect("/HomeUser.html");

        }else{
            response.sendRedirect("/login?unauthorized=true");
        }

    }

}
