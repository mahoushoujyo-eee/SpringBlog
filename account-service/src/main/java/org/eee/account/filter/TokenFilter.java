package org.eee.account.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eee.account.entity.UserPrincipal;
import org.eee.account.util.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class TokenFilter extends OncePerRequestFilter
{
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String token = request.getHeader("token");
        if (token == null || token.isEmpty())
        {
//            response.setContentType("application/json;charset=utf-8");
//            response.getWriter().write("{\"msg\":\"token为空\"}");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        if (JwtUtil.validateToken(token))
        {
            UserPrincipal user = JwtUtil.parseToken(token);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        else
        {
//            response.setContentType("application/json;charset=utf-8");
//            response.getWriter().write("{\"msg\":\"token无效\"}");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        filterChain.doFilter(request, response);


    }
}
