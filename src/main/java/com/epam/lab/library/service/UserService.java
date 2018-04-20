package com.epam.lab.library.service;

import com.epam.lab.library.domain.*;

import java.util.List;

public interface UserService {

    List<Order> getAllOrderByStatus(Status status);

    /**
     * Takes user data from database by user id
     *
     * @param login It's user unique login value in database
     * @return an instances of User filled with data from the database
     */
    User getUserByLogin(String login);

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
    boolean createUser(User user);

    /**
     * Method removes user from database
     *
     * @param id It's id of user which must be deleted from the database
     */
    void deleteUserById(Long id);

    /**
     * Changes user access level (READER / LIBRARIAN)
     *
     * @param id          It's id of user which access level must be changed
     * @param accessLevel current value of user access level
     */
    void updateUserAccessLevel(Long id, AccessLevel accessLevel);

    /**
     * Get all user orders
     *
     * @param id user
     * @return list of user's orders
     */
    List<Order> getAllUserOrders(Long id);

    /**
     *
     * @param login
     * @return
     */
    User getUserDataByLogin(String login);

    boolean updateUserNameByLogin(User user);

    void deleteRequest(Long orderId, Long bookId);
}