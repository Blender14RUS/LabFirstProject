package com.epam.lab.library.service;

import com.epam.lab.library.domain.AccessLevel;
import com.epam.lab.library.domain.User;

import java.util.List;

public interface UserService {

    String getUsersLanguage();

    void setUsersLanguage(String language);
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
     * @param user an instances of User filled with data must be recorded in the database
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
    boolean updateUserAccessLevel(Long id, AccessLevel accessLevel);

    /**
     * Takes user data from database by login
     *
     * @param login user's login
     * @return an instances of User filled with data from the database
     */
    User getUserDataByLogin(String login);

    /**
     * Update userName by login
     *
     * @param user instance of user model with new name
     */
    void updateUserNameByLogin(User user);

    /**
     * Check is user already exists
     *
     * @param login user's login
     * @return true if exist
     */
    boolean isUserLoginAlreadyExists(String login);

    /**
     * compares password and password confirmation
     *
     * @param password        user password
     * @param confirmPassword confirmed user password
     * @return true if password are the same or false if are not
     */
    boolean equalsPasswords(String password, String confirmPassword);

}
