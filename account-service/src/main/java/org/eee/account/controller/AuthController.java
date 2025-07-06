package org.eee.account.controller;


import lombok.extern.slf4j.Slf4j;
import org.eee.account.param.Response;
import org.eee.account.param.UserRegisterParam;
import org.eee.account.param.UserResetParam;
import org.eee.account.service.UserService;
import org.eee.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController
{
    @Autowired
    private UserService  userService;

    @PostMapping("/register")
    public Response<Long> register(@RequestBody UserRegisterParam user)
    {
        log.info("Registering user: {}", user);
        return userService.register(user);
    }

    @PostMapping("/reset")
    public Response<String> reset(@RequestBody UserResetParam user)
    {
        log.info("Resetting user: {}", user);
        return userService.resetPassword(user);
    }

}
