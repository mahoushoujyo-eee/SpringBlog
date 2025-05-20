package org.eee.account.service;

import org.eee.account.mapper.UserMapper;
import org.eee.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // 如果不使用构造函数注入，可以手动设置mapper
        userService = new UserService();
        // 使用真实的PasswordEncoder实例
        if (passwordEncoder == null) {
            passwordEncoder = new BCryptPasswordEncoder();
        }
        // 通过反射或直接方法设置私有字段
        try {
            java.lang.reflect.Field field = UserService.class.getDeclaredField("userMapper");
            field.setAccessible(true);
            field.set(userService, userMapper);

            field = UserService.class.getDeclaredField("passwordEncoder");
            field.setAccessible(true);
            field.set(userService, passwordEncoder);
        } catch (Exception e) {
            fail("Failed to set up mocks via reflection: " + e.getMessage());
        }
    }

    @Test
    public void testRegisterUser() {
        // Arrange
        String rawPassword = "password123";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        
        User user = new User();
        user.setEmail("test@example.com");
        user.setEncryptedPassword(rawPassword);
        user.setNickname("testNickname");
        user.setGithubInfo("testGithub");
        user.setGoogleInfo("testGoogle");
        
        when(userMapper.getMaxUserName()).thenReturn("9999999999");
        doNothing().when(userMapper).insertUser(any(User.class));
        
        // Act
        var response = userService.register(user);
        
        // Assert
        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertNotNull(response.getData());
        assertTrue(response.getMessage().contains("注册成功！您的uid为"));
        
        // Verify that the password was encoded
        verify(passwordEncoder, atLeastOnce()).encode(rawPassword);
        // Verify that insertUser was called with the correct parameters
        verify(userMapper, times(1)).getMaxUserName();
        verify(userMapper, times(1)).insertUser(argThat(u -> {
            return u != null && 
                   u.getUserName() != null && 
                   Long.parseLong(u.getUserName()) > 9999999999L &&
                   !u.getEncryptedPassword().equals(rawPassword) &&
                   u.getEmail().equals("test@example.com") &&
                   u.getNickname().equals("testNickname") &&
                   u.getGithubInfo().equals("testGithub") &&
                   u.getGoogleInfo().equals("testGoogle");
        }));
    }
}