package org.eee.account.controller;


import lombok.extern.slf4j.Slf4j;
import org.eee.account.param.Response;
import org.eee.account.service.UserService;
import org.eee.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stark.dataworks.boot.web.ServiceResponse;

@RestController
@RequestMapping("/auth")
@Slf4j
public class UserController
{
    @Autowired
    private UserService  userService;

    @PostMapping("/register")
    public ServiceResponse<Long> register(@RequestBody User user)
    {
        log.info("Registering user: {}", user);
        return userService.register(user);
    }

}
