package com.epam.lab.library.dao;

import com.epam.lab.library.domain.*;

import java.util.List;

public interface UserDao {

    /**
     * Takes user data from database by user id
     *
     * @param id It's user id value in database
     * @return an instances of User filled with data from the database
     */
    User getUser(Long id);

    /**
     * Creates List of all users from database
     *
     * @return List of User instances filled with data from the database
     */
    List<User> getAllUsers();

    /**
     * Creates new user through web form
     *
     * @param user - an instances of User filled with data must be recorded in the database
     * @return Count of changes rows in database
     */
    int createUser(User user);

    /**
     * Method removes user from database
     *
     * @param id It's id of user which must be deleted from the database
     */
    void deleteUserById(Long id);

    /**
     * Changes user access level (READER / LIBRARIAN)
     *
     * @param id It's id of user which access level must be changed
     * @param accessLevel current value of user access level
     */
    void updateUserAccessLevel(Long id, AccessLevel accessLevel);

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
     *
     * @param login
     * @return
     */
    public User getUserByLogin(String login);

    /**
     *
     * @param login
     * @return
     */
    public boolean isUserLoginAlreadyExists(String login);

}
