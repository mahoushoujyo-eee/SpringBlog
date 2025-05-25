package org.eee.account.service;

import lombok.extern.slf4j.Slf4j;
import org.eee.account.mapper.RoleMapper;
import org.eee.account.mapper.UserMapper;
import org.eee.account.param.Response;
import org.eee.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import stark.dataworks.boot.autoconfig.web.LogArgumentsAndResponse;
import stark.dataworks.boot.web.ServiceResponse;

@Service
@Slf4j
@LogArgumentsAndResponse
public class UserService
{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper  roleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ServiceResponse<Long> register(User user)
    {
        String username = userMapper.getMaxUserName();
        Long newUserName = Long.parseLong(username) + 1L;
        user.setUsername(String.valueOf(newUserName));
        user.setEncryptedPassword(passwordEncoder.encode(user.getEncryptedPassword()));
        user.setCreatorId(1L);
        user.setModifierId(1L);
        log.info("register user:{}", user);
        userMapper.insertUser(user);

        return ServiceResponse.buildSuccessResponse(newUserName, "注册成功！您的uid为"+newUserName);
    }
}
