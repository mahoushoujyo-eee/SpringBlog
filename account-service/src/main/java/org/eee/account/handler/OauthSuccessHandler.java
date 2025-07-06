package org.eee.account.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.eee.account.entity.UserPrincipal;
import org.eee.account.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class OauthSuccessHandler implements AuthenticationSuccessHandler
{
    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException
    {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        log.info("Success Handler");
        log.info("Principal: {}", authentication.getPrincipal());
        log.info("OAuth2User: {}", oAuth2User);

        // 构建 JWT 的 payload
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", oAuth2User.getAttribute("id").toString());
        claims.put("username", oAuth2User.getAttribute("login"));
        claims.put("email", oAuth2User.getAttribute("email"));
//        claims.put("iat", new Date());

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        String token = JwtUtil.generateToken(userPrincipal);

        // 返回 JSON 格式的登录结果
//        response.setContentType("application/json;charset=UTF-8");
//        response.getWriter().write("{\"token\": \"" + token + "\"}");
//        response.setStatus(HttpServletResponse.SC_OK);

        // 重定向到前端页面，并带上 Token 参数
        String redirectUrl = "/?token=" + token;
        response.sendRedirect(redirectUrl);
    }
}
