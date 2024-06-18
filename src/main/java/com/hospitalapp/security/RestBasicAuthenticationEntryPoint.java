package com.hospitalapp.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

public class RestBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response,
            final AuthenticationException authException) throws IOException, ServletException {

        response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    }
}
