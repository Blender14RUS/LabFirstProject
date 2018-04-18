package com.epam.lab.library.service.impl;

import com.epam.lab.library.dao.UserDao;
import com.epam.lab.library.domain.*;
import com.epam.lab.library.service.BookService;
import com.epam.lab.library.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserDao userDao;

    @Autowired
    private BookService bookService;

    @Autowired
    UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<Order> getAllOrderByStatus(Status status) {
        return userDao.getAllOrderByStatus(status);
    }

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public int createUser(User user) {
        return userDao.createUser(user);
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

}
