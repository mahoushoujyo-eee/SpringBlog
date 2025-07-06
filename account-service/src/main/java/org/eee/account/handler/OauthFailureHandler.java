package org.eee.account.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Enumeration;

@Slf4j
@Component
public class OauthFailureHandler implements AuthenticationFailureHandler
{
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException
    {
        // 打印请求的基本信息
        log.info("OAuth2 认证失败 - 请求信息:");
        log.info("请求方法: {}", request.getMethod());
        log.info("请求URL: {}", request.getRequestURL());
        log.info("请求URI: {}", request.getRequestURI());
        log.info("查询参数: {}", request.getQueryString());
        log.info("客户端IP: {}", getClientIpAddress(request));
        log.info("User-Agent: {}", request.getHeader("User-Agent"));
        log.info("Referer: {}", request.getHeader("Referer"));

        // 打印所有请求头
        log.info("请求头信息:");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            log.info("  {}: {}", headerName, headerValue);
        }

        // 打印所有请求参数
        log.info("请求参数:");
        request.getParameterMap().forEach((key, values) ->
            log.info("  {}: {}", key, String.join(", ", values))
        );

        // 打印异常信息
        log.error("认证异常: {}", exception.getMessage(), exception);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"code\":401,\"message\":\"OAuth2登录失败!\"}");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

    /**
     * 获取客户端真实IP地址
     */
    private String getClientIpAddress(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty() && !"unknown".equalsIgnoreCase(xForwardedFor)) {
            return xForwardedFor.split(",")[0].trim();
        }

        String xRealIp = request.getHeader("X-Real-IP");
        if (xRealIp != null && !xRealIp.isEmpty() && !"unknown".equalsIgnoreCase(xRealIp)) {
            return xRealIp;
        }

        return request.getRemoteAddr();
    }
}
