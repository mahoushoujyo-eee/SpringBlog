package org.eee.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.eee.account.entity.UserPrincipal;
import org.eee.model.entity.User;

@Mapper
public interface UserMapper
{
    UserPrincipal getUserByUsername(String username);

    void updatePassword(String username, String newPassword);

    void insertUser(User user);

    String getMaxUserName();
}
