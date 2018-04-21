package com.epam.lab.library.service.impl;

import com.epam.lab.library.dao.UserDao;
import com.epam.lab.library.dao.impl.OrderDaoImpl;
import com.epam.lab.library.domain.AccessLevel;
import com.epam.lab.library.domain.User;
import com.epam.lab.library.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    private final PasswordEncoder bcryptEncoder;

    private UserDao userDao;

    @Autowired
    private OrderDaoImpl orderDao;

    public UserServiceImpl(UserDao userDao, PasswordEncoder bcryptEncoder) {
        this.userDao = userDao;
        this.bcryptEncoder = bcryptEncoder;
    }

    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userDao.getAllUsers();
        if (users.isEmpty()) {
            LOG.error("Users list is empty.");
        }
        return users;
    }

    @Override
    public void deleteUserById(Long id) {
        orderDao.deleteOrdersByUserId(id);
        if (!userDao.deleteUserById(id)) {
            LOG.error("Delete user failed.");
        }
    }

    @Override
    public void updateUserAccessLevel(Long id, AccessLevel accessLevel) {
        if (accessLevel.equals(AccessLevel.READER))
            accessLevel = AccessLevel.LIBRARIAN;
        else accessLevel = AccessLevel.READER;
        if (!userDao.updateUserAccessLevel(id, accessLevel)) {
            LOG.error("Update userRole failed.");
        }
    }

    @Override
    public User getUserDataByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    public void updateUserNameByLogin(User user) {
        if (!userDao.updateUserNameByLogin(user)) {
            LOG.error("Update userName by login failed.");
        }
    }

    @Override
    public boolean createUser(User user) {
        if (user.getLogin() == null ||
                user.getPass() == null ||
                user.getLogin().isEmpty() ||
                user.getPass().isEmpty() ||
                userDao.isUserLoginAlreadyExists(user.getLogin())) {
            return false;
        } else {
            user.setPass(bcryptEncoder.encode(user.getPass()));
            if (userDao.createUser(user, AccessLevel.READER)) {
                return true;
            }
            LOG.error("Create user failed.");
            return false;
        }
    }

    @Override
    public boolean isUserLoginAlreadyExists(String login) {
        if (login != null && !login.isEmpty()) {
            return userDao.isUserLoginAlreadyExists(login);
        } else {
            return false;
        }
    }

    @Override
    public boolean equalsPasswords(String password, String confirmPassword) {
        return (password != null &&
                !password.isEmpty() &&
                password.equals(confirmPassword));
    }

}
