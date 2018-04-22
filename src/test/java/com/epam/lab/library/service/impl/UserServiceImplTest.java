package com.epam.lab.library.service.impl;

import com.epam.lab.library.dao.UserDao;
import com.epam.lab.library.domain.AccessLevel;
import com.epam.lab.library.domain.User;
import com.epam.lab.library.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceImplTest {
    private UserDao mockUserDao;
    private UserService userService;

    @BeforeEach
    public void setup() {
        mockUserDao = mock(UserDao.class);
        PasswordEncoder mockBcryptEncoder;
        mockBcryptEncoder = mock(PasswordEncoder.class);
        userService = new UserServiceImpl(mockUserDao, mockBcryptEncoder);
    }

    @Test
    public void getUserByLoginTest() {
        Long id = 2L;
        String login = "login";
        User expectedUser = new User(id, login, "name", AccessLevel.READER);
        doReturn(new User(id, login, "name", AccessLevel.READER))
                .when(mockUserDao).getUserByLogin("login");

        User actualUser = userService.getUserByLogin(login);

        verify(mockUserDao).getUserByLogin(login);
        verifyNoMoreInteractions(mockUserDao);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void updateUserAccessLevel() {
        Long id = 2L;
        AccessLevel accessLevel = AccessLevel.READER;
        boolean expectedValue = true;
        doReturn(true)
                .when(mockUserDao).updateUserAccessLevel(id, accessLevel);

        boolean actualValue = userService.updateUserAccessLevel(id, accessLevel);

        verify(mockUserDao).updateUserAccessLevel(id, accessLevel);
        verifyNoMoreInteractions(mockUserDao);
        assertEquals(expectedValue, actualValue);
    }
}
