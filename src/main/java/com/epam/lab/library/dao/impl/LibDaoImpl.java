package com.epam.lab.library.dao.impl;

import com.epam.lab.library.dao.LibDAO;
import com.epam.lab.library.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LibDaoImpl implements LibDAO {

    @Autowired
    private JdbcOperations jdbcOperations;

    @Autowired
    NamedParameterJdbcOperations namedParameterJdbcOperations;

    private static final String GET_USERS_BY_ID = "SELECT * FROM users WHERE id=?";
    private static final String GET_ALL_USERS = "SELECT * FROM users ORDER BY id";
    private static final String CREATE_NEW_USER = "INSERT INTO users (id, login, name, access_level, pass) VALUES (nextval('users_seq'),?,?,'READER','pass')";
    private static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id=?";
    private static final String UPDATE_USER_ACCESS_LEVEL = "UPDATE users SET access_level = ? WHERE id = ?";


    @Override
    public List<User> getAllUsers() {
        List<User> users = jdbcOperations.query(GET_ALL_USERS, new BeanPropertyRowMapper<User>(User.class));
        return !users.isEmpty() ? users : null;
    }

    @Override
    public int createUser(User user) {
        return jdbcOperations.update(CREATE_NEW_USER,
                user.getLogin(),
                user.getName());
    }

    @Override
    public void deleteUserById(Long id) {
        jdbcOperations.update(DELETE_USER_BY_ID, id);
    }

    @Override
    public void updateUserAccessLevel(Long id, String access_level) {
        jdbcOperations.update(UPDATE_USER_ACCESS_LEVEL, access_level, id);
    }
}
