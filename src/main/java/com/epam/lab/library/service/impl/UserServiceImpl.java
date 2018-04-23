package com.epam.lab.library.service.impl;

import com.epam.lab.library.dao.UserDao;
import com.epam.lab.library.dao.impl.OrderDaoImpl;
import com.epam.lab.library.domain.AccessLevel;
import com.epam.lab.library.domain.User;
import com.epam.lab.library.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String getUsersLanguage(){
        return userDao.getUsersLanguage(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public void setUsersLanguage(String language) {
        if(!userDao.setUsersLanguage(language,SecurityContextHolder.getContext().getAuthentication().getName())){
            LOG.error("Language hadn't changed!");
        }
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
        LOG.info("Orders of user with id: " +
                +id + " has been deleted");
        if (userDao.deleteUserById(id)) {
            LOG.error("Delete user with id: " + id + "failed.");
        } else {
            LOG.error("Delete user failed.");
        }
    }

    @Override
    public boolean updateUserAccessLevel(Long id, AccessLevel accessLevel) {
        if (accessLevel.equals(AccessLevel.READER))
            accessLevel = AccessLevel.LIBRARIAN;
        else accessLevel = AccessLevel.READER;
        if (!userDao.updateUserAccessLevel(id, accessLevel)) {
            LOG.error("Update userRole failed.");
            return false;
        }
        return true;
    }

    @Override
    public User getUserDataByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    public void updateUserNameByLogin(User user) {
        if (userDao.updateUserNameByLogin(user)) {
            LOG.info("Update userName success " + user.toString());
        } else {
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
            LOG.error("Create user failed: " +
                    "necessary field is empty (" + user.toString() + ")");
            return false;
        } else {
            user.setPass(bcryptEncoder.encode(user.getPass()));
            if (userDao.createUser(user, AccessLevel.READER)) {
                LOG.info("New user has been created: " + user.toString());
                return true;
            }
            LOG.error("Create user failed: " + user.toString());
            return false;
        }
    }

    @Override
    public boolean isUserLoginAlreadyExists(String login) {
        if (login != null && !login.isEmpty()) {
            return userDao.isUserLoginAlreadyExists(login);
        } else {
            LOG.error("User " + login + " already exist: ");
            return false;
        }
    }

    @Override
    public boolean equalsPasswords(String password, String confirmPassword) {
        LOG.info("checking passwords");
        return (password != null &&
                !password.isEmpty() &&
                password.equals(confirmPassword));
    }

}
