package com.epam.lab.library.service;

import com.epam.lab.library.domain.Order;
import com.epam.lab.library.domain.Status;

import java.util.List;

public interface OrderService {

    /**
     * Set book status
     *
     * @param status book
     * @param id It's order id value in database
     */
    void setBookStatus(Status status, Long id);

    /**
     * Get login from session and create order request
     *
     * @param order an instances of Order filled with data (not include id) must be recorded in the database
     * @return instances of Order with id
     */
    Order requestBook(Order order);

    /**
     * Get all orders by status
     *
     * @param status status of the book
     * @return List of all orders by status
     */
    List<Order> getAllOrderByStatus(Status status);

    /**
     * Get all user orders
     *
     * @param id user
     * @return list of user's orders
     */
    List<Order> getAllUserOrders(Long id);

    /**
     * Removes order request from database
     *
     * @param orderId order id
     */
    void deleteOrder(Long orderId);

}
