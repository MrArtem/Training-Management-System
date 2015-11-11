package com.exadel.training.security.handlers;


import com.exadel.training.security.authentication.CustomAuthentication;
import com.exadel.training.security.authentication.UserModelSecurity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        clearAuthenticationAttributes(request);
        try {
            CustomAuthentication user = (CustomAuthentication) authentication;
            String userName = user.getFirstName() + " " + user.getSecondName();
            String role = "";
            for (GrantedAuthority grantedAuthority : user.getAuthorities()) {
                role = grantedAuthority.getAuthority();
            }
            UserModelSecurity userModelSecurity = new UserModelSecurity(userName, user.getUserId(), role);
            PrintWriter writer = response.getWriter();
            objectMapper.writeValue(writer, userModelSecurity);
            writer.flush();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
