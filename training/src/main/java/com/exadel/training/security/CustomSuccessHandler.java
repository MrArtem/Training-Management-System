package com.exadel.training.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        clearAuthenticationAttributes(request);
        try {
            response.addHeader("Content-Type","application/json");
            PrintWriter writer = response.getWriter();
            writer.print("{'login': 'FAILURE'}");
            writer.flush();
        }catch (IOException e) {
            System.out.println(e);
        }
    }
}
