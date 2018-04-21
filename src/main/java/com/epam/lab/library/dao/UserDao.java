package com.epam.lab.library.dao;

import com.epam.lab.library.domain.AccessLevel;
import com.epam.lab.library.domain.User;

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
    boolean createUser(User user, AccessLevel accessLevel);

    /**
     * Method removes user from database
     *
     * @param id It's id of user which must be deleted from the database
     */
    boolean deleteUserById(Long id);

    /**
     * Changes user access level (READER / LIBRARIAN)
     *
     * @param id          It's id of user which access level must be changed
     * @param accessLevel current value of user access level
     */
    boolean updateUserAccessLevel(Long id, AccessLevel accessLevel);

    /**
     * Takes user data from database by login
     *
     * @param login
     * @return an instances of User filled with data from the database
     */
    User getUserByLogin(String login);

    /**
     * Check is user already exists.
     *
     * @param login
     * @return true if exist
     */
    boolean isUserLoginAlreadyExists(String login);

    /**
     * @param
     * @return
     */
    boolean updateUserNameByLogin(User user);

}
