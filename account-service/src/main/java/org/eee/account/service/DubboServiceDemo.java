package org.eee.account.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

@Slf4j
@DubboService
public class DubboServiceDemo
{
    public String sayHello(String name)
    {
        log.info("name: {}", name);
        return "Hello " + name;
    }
}
