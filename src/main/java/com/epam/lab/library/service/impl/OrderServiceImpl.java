package com.epam.lab.library.service.impl;

import com.epam.lab.library.dao.BookDao;
import com.epam.lab.library.dao.OrderDao;
import com.epam.lab.library.dao.UserDao;
import com.epam.lab.library.domain.Book;
import com.epam.lab.library.domain.Order;
import com.epam.lab.library.domain.Status;
import com.epam.lab.library.domain.User;
import com.epam.lab.library.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private DataBaseUserDetailService detailsService;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public void setBookStatus(Status status, Long orderId) {
        Order order = orderDao.getOrder(orderId);
        if ((!orderDao.setBookStatus(status, orderId)) || (order == null)) {
            LOG.error("Update bookStatus failed.");
        }
        if (status == Status.IN_LIBRARY && order != null) {
            bookDao.incrementBookCountAvailable(order.getBookId());
        }
    }

    @Override
    public Order requestBook(Order order) {
        String userName = detailsService.getCurrentUsername();
        User user = userService.getUserByLogin(userName);
        order.setUserId(user.getId());
        Book book = bookDao.getBook(order.getBookId());
        if ((book.getAvailable() > 0) && (orderDao.isBookNotRequestedByUser(order.getBookId(), order.getUserId())) && bookDao.requestBook(book)) {
            order.setId(orderDao.createOrder(order));
        } else {
            LOG.error("requestBook failed.");
        }
        return order;
    }

    @Override
    public List<Order> getAllUserOrders(Long id) {
        List<Order> orders = orderDao.getAllUserOrders(id);
        for (Order order : orders) {
            order.setBook(bookDao.getBook(order.getBookId()));
        }
        return orders;
    }

    @Override
    public List<Order> getAllOrderByStatus(Status status) {
        List<Order> orderList = orderDao.getAllOrderByStatus(status);
        for (Order order : orderList) {
            order.setBook(bookDao.getBook(order.getBookId()));
            order.setUser(userDao.getUser(order.getUserId()));
        }
        return orderList;
    }

    @Override
    public void deleteOrder(Long orderId) {
        Long bookId = orderDao.getOrder(orderId).getBookId();
        if (!((orderDao.deleteOrder(orderId)) && (bookDao.incrementBookCountAvailable(bookId)))) {
            LOG.error("deleteOrder failed.");
        }
    }

}
