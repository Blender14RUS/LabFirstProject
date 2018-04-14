package com.epam.lab.library.dao;

import com.epam.lab.library.domain.User;

import java.util.List;

public interface LibDAO {

    List<User> getAllUsers();

    int createUser(User user);

    void deleteUserById(Long id);

    void updateUserAccessLevel(Long id, String access_level);
}
