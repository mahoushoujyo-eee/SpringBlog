package org.eee.account.controller;

import lombok.extern.slf4j.Slf4j;
import org.eee.account.entity.UserPrincipal;
import org.eee.account.param.Response;
import org.eee.account.param.UserInfoParam;
import org.eee.account.service.UserService;
import org.eee.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/user"))
@Slf4j
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public Response<UserInfoParam> me()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated())
        {
            // 通常token会存储在Principal对象中，具体取决于你的实现
            UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

            return userService.loadUserById(principal);
        }
        return Response.error(401, "token信息异常！");
    }
}
