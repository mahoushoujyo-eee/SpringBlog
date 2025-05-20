package org.eee.account.service;

import org.eee.account.entity.UserPrincipal;
import org.eee.account.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;


@Service
public class UserPrincipalService implements UserDetailsService, UserDetailsPasswordService
{
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username)
    {
        UserDetails user = userMapper.getUserByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("用户名不存在");

        return user;
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        userMapper.updatePassword(user.getUsername(), newPassword);

        UserPrincipal newUser = new UserPrincipal();

        newUser.setUsername(user.getUsername());
        newUser.setPassword(newPassword);

        return newUser;
    }
}
