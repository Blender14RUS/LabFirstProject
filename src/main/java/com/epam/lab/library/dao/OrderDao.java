package com.epam.lab.library.dao;

import com.epam.lab.library.domain.Order;
import com.epam.lab.library.domain.Status;

import java.util.List;

public interface OrderDao {

    /**
     * Creates new order
     *
     * @param order - an instances of Order filled with data must be recorded in the database
     * @return Order id value in database
     */
    Long createOrder(Order order);

    /**
     * Takes order data from database by order id
     *
     * @param id It's order id value in database
     * @return an instances of Order filled with data from the database
     */
    Order getOrder(Long id);

    /**
     *
     *
     * @param bookId It's book id value in database
     * @param userId It's user id value in database
     * @return true if book not requested
     */
    boolean isBookNotRequestedByUser(Long bookId, Long userId);

    /**
     * Set book status
     *
     * @param status
     * @param id It's book id value in database
     * @return true if status set successful
     */
    boolean setBookStatus(Status status, Long id);

    /**
     * Get all orders by status
     *
     * @param status status of the book
     * @return List of all orders by status
     */
    List<Order> getAllOrderByStatus(Status status);

    /**
     * Get all user's orders
     *
     * @param id user's id
     * @return list of all user's orders
     */
    List<Order> getAllUserOrders(Long id);

    /**
     * Method removes all user orders from database
     *
     * @param id user's id
     */
    void deleteOrdersByUserId(Long id);

    /**
     * Removes order request from database
     *
     * @param id It's order id value in database
     * @return true if successful
     */
    boolean deleteOrder(Long id);
}
