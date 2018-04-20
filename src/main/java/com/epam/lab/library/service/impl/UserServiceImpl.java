package com.epam.lab.library.service.impl;

import com.epam.lab.library.dao.UserDao;
import com.epam.lab.library.domain.*;
import com.epam.lab.library.service.BookService;
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
    private UserDao userDao;
    private final PasswordEncoder bcryptEncoder;

    public UserServiceImpl(UserDao userDao, PasswordEncoder bcryptEncoder) {
        this.userDao = userDao;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Autowired
    private BookService bookService;


    @Override
    public List<Order> getAllOrderByStatus(Status status) {
        return userDao.getAllOrderByStatus(status);
    }

    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void deleteUserById(Long id) {
        userDao.deleteUserById(id);
    }

    @Override
    public void updateUserAccessLevel(Long id, AccessLevel accessLevel) {
        if (accessLevel.equals(AccessLevel.READER))
            accessLevel = AccessLevel.LIBRARIAN;
        else accessLevel = AccessLevel.READER;
        userDao.updateUserAccessLevel(id, accessLevel);
    }

    @Override
    public List<Order> getAllUserOrders(Long id) {
        List<Order> orders = userDao.getAllUserOrders(id);
        for (Order order : orders) {
            order.setBook(bookService.getBook(order.getBookId()));
        }
        return orders;
    }

    @Override
    public User getUserDataByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    public boolean updateUserNameByLogin(User user) {
        return (userDao.updateUserNameByLogin(user)!=0) ? true : false;
    }

    @Override
    public boolean createUser(User user) {
        if (user.getLogin().length()==0 ||
                user.getPass().length()==0 ||
                userDao.isUserLoginAlreadyExists(user.getLogin())){ return false; }
        else {
            user.setPass(bcryptEncoder.encode(user.getPass()));
            if (userDao.createUser(user, AccessLevel.READER)==0){ return false; }
            else return true;
        }
    }

}
