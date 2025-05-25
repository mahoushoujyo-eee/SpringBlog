package org.eee.account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController
{
    @GetMapping("/user/hello")
    public String helloUser()
    {
        return "hello";
    }

    @GetMapping("/public/hello")
    public String helloPublic()
    {
        return "hello";
    }

    @GetMapping("/admin/hello")
    public String helloAdmin()
    {
        return "hello";
    }
}
