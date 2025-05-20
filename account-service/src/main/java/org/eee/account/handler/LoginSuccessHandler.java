package org.eee.account.handler;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginSuccessHandler  implements AuthenticationSuccessHandler
{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException
    {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"code\":200,\"message\":\"登录成功\"}");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
