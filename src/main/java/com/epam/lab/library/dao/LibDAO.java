package com.epam.lab.library.dao;

import com.epam.lab.library.domain.User;

import java.util.List;

public interface LibDAO {

    /**
     * Creates List of all users from database
     *
     * @return
     */
    List<User> getAllUsers();

    /**
     * Creates new user through web form
     *
     * @param user
     * @return
     */
    int createUser(User user);

    /**
     * Method removes user from databese
     *
     * @param id
     */
    void deleteUserById(Long id);

    /**
     * Changes user access level (READER / LIBRARIAN)
     *
     * @param id
     * @param access_level
     */
    void updateUserAccessLevel(Long id, String access_level);
}
