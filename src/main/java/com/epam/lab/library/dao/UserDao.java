package com.epam.lab.library.dao;

import com.epam.lab.library.domain.AccessLevel;
import com.epam.lab.library.domain.User;

import java.util.List;

public interface UserDao {

    boolean setUsersLanguage(String language,String userName);

    String getUsersLanguage(String name);
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
     * @return true if user has been deleted successfully
     * or false otherwise
     */
    boolean deleteUserById(Long id);

    /**
     * Changes user access level (READER / LIBRARIAN)
     *
     * @param id          It's id of user which access level must be changed
     * @param accessLevel current value of user access level
     * @return true if user access level name has been changed successfully
     * or false otherwise
     */
    boolean updateUserAccessLevel(Long id, AccessLevel accessLevel);

    /**
     * Takes user data from database by login
     *
     * @param login User login in database (should be unique)
     * @return an instances of User filled with data from the database
     */
    User getUserByLogin(String login);

    /**
     * Check is user already exists.
     *
     * @param login User login in database (should be unique)
     * @return true if exist
     */
    boolean isUserLoginAlreadyExists(String login);

    /**
     * Updates user name
     *
     * @param user User with new new name in user.name field
     * @return true if name has been changed successfully
     * or false otherwise
     */
    boolean updateUserNameByLogin(User user);

    /**
     * Takes user's login, password and role
     * @ Method should only use in security authentication</>
     *
     * @param login user's login in database
     * @return instance of user with filled login, password and role
     */
    User getUserWithPassByLogin (String login);

}
