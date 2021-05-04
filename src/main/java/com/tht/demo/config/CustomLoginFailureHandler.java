package com.tht.demo.config;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String message = "";
        if(e instanceof UsernameNotFoundException){
            message = "sai tên đăng nhập";
        }else if(e instanceof BadCredentialsException){
            message = " sai mật khẩu";
        }else {
            message = "sai tên đăng nhập và mật khẩu";
        }

        httpServletRequest.getRequestDispatcher(String.format("/login/error?message=%s",message)).forward(httpServletRequest,httpServletResponse);
    }
}
