package com.epam.lab.library.service.impl;

import com.epam.lab.library.dao.BookDao;
import com.epam.lab.library.dao.UserDao;
import com.epam.lab.library.domain.AccessLevel;
import com.epam.lab.library.domain.Order;
import com.epam.lab.library.domain.Status;
import com.epam.lab.library.domain.User;
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
    private final PasswordEncoder bcryptEncoder;
    private UserDao userDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookService bookService;

    public UserServiceImpl(UserDao userDao, PasswordEncoder bcryptEncoder) {
        this.userDao = userDao;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public List<Order> getAllOrderByStatus(Status status) {
        List<Order> orderList = userDao.getAllOrderByStatus(status);
        for (Order order : orderList) {
            order.setBook(bookDao.getBook(order.getBookId()));
            order.setUser(userDao.getUser(order.getUserId()));
        }
        return orderList;
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
        userDao.deleteOrdersByUserId(id);
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
        return userDao.updateUserNameByLogin(user) != 0;
    }

    @Override
    public boolean createUser(User user) {
        if (user.getLogin() == null ||
                user.getPass() == null ||
                user.getLogin().length() == 0 ||
                user.getPass().length() == 0 ||
                userDao.isUserLoginAlreadyExists(user.getLogin())) {
            return false;
        } else {
            user.setPass(bcryptEncoder.encode(user.getPass()));
            return userDao.createUser(user, AccessLevel.READER) != 0;
        }
    }

    @Override
    public void deleteRequest(Long orderId, Long bookId) {
        userDao.deleteRequest(orderId);
        bookDao.plusBook(bookId);
    }

    @Override
    public boolean isUserLoginAlreadyExists(String login) {
        if (login != null && !login.equals("")) {
            return userDao.isUserLoginAlreadyExists(login);
        } else {
            return false;
        }
    }

    @Override
    public boolean equalsPasswords(String password, String confirmPassword) {
        return (password != null &&
                password != "" &&
                password.equals(confirmPassword));
    }

}
