package com.epam.lab.library.dao.impl;

import com.epam.lab.library.dao.UserDao;
import com.epam.lab.library.domain.AccessLevel;
import com.epam.lab.library.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private static final String GET_USERS_BY_ID = "SELECT id, login, name, access_level FROM users WHERE id = ?";
    private static final String GET_ALL_USERS = "SELECT id, login, name, access_level FROM users WHERE access_level != 'ADMIN' ORDER BY id";
    private static final String CREATE_NEW_USER = "INSERT INTO users (id, login, name, access_level, pass) " +
            "VALUES (nextval('users_seq'),?,?,?,?)";
    private static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?";
    private static final String UPDATE_USER_LANGUAGE="UPDATE users SET language = ? WHERE login = ?";
    private static final String UPDATE_USER_ACCESS_LEVEL = "UPDATE users SET access_level = ? WHERE id = ?";
    private static final String GET_USER_BY_LOGIN = "SELECT id, login, name, access_level FROM users WHERE login = ?";
    private static final String GET_USER_AND_LANGUAGE_BY_LOGIN = "SELECT id, login, name, access_level, language FROM users WHERE login = ?";
    private static final String USER_COUNT = "SELECT count(*) FROM users WHERE login=?";
    private static final String UPDATE_USER_NAME = "UPDATE users SET name = ? WHERE login = ?";
    private static final String GET_USER_WITH_PASS_AND_ROLE = "SELECT login, pass, access_level FROM users WHERE login = ?";

    @Autowired
    private NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public boolean setUsersLanguage(String language,String userName) {
        int i=namedParameterJdbcOperations.getJdbcOperations().update(UPDATE_USER_LANGUAGE, language.toString(), userName.toString());
       return i>0;
    }

    @Override
    public String getUsersLanguage(String login) {
        if(!"anonymousUser".equals(login)){
        User current = jdbcOperations.queryForObject(
                GET_USER_AND_LANGUAGE_BY_LOGIN, new Object[]{login}, new BeanPropertyRowMapper<>(User.class));
            return current.getLanguage() ;
        }
        else return "en_US";
    }

    @Override
    public User getUser(Long id) {
        return namedParameterJdbcOperations.getJdbcOperations().queryForObject(GET_USERS_BY_ID, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public List<User> getAllUsers() {
        return namedParameterJdbcOperations.getJdbcOperations().query(GET_ALL_USERS, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public boolean createUser(User user, AccessLevel accessLevel) {
        return namedParameterJdbcOperations.getJdbcOperations().update(CREATE_NEW_USER, user.getLogin(), user.getName(),
                accessLevel.toString(), user.getPass()) > 0;
    }

    @Override
    public boolean deleteUserById(Long id) {
        return namedParameterJdbcOperations.getJdbcOperations().update(DELETE_USER_BY_ID, id) > 0;
    }

    @Override
    public boolean updateUserAccessLevel(Long id, AccessLevel accessLevel) {
        return namedParameterJdbcOperations.getJdbcOperations().update(UPDATE_USER_ACCESS_LEVEL, accessLevel.toString(), id) > 0;
    }

    @Override
    public User getUserByLogin(String login) {
        return namedParameterJdbcOperations.getJdbcOperations().queryForObject(
                GET_USER_BY_LOGIN, new Object[]{login}, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public boolean isUserLoginAlreadyExists(String login) {
        return namedParameterJdbcOperations.getJdbcOperations().queryForObject(
                USER_COUNT, new Object[]{login}, Integer.class) > 0;
    }

    @Override
    public boolean updateUserNameByLogin(User user) {
        return namedParameterJdbcOperations.getJdbcOperations().update(UPDATE_USER_NAME, user.getName(), user.getLogin()) > 0;
    }

    @Override
    public User getUserWithPassByLogin(String login) {
        return namedParameterJdbcOperations.getJdbcOperations().queryForObject(
                GET_USER_WITH_PASS_AND_ROLE, new Object[]{login}, new BeanPropertyRowMapper<>(User.class));
    }

}
