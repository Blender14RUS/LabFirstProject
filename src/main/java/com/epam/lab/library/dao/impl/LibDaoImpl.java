package com.epam.lab.library.dao.impl;


import com.epam.lab.library.dao.LibDao;
import com.epam.lab.library.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LibDaoImpl implements LibDao {

    @Autowired
    JdbcOperations jdbcOperations;

    @Autowired
    NamedParameterJdbcOperations namedParameterJdbcOperations;


    private static final String GET_ALL_AVAILABLE_BOOKS = "SELECT * FROM books WHERE available!=0";
    private static final String GET_AUTHORS = "SELECT * FROM authors JOIN book_authors ON authors.id = book_authors.author_id WHERE book_authors.book_id=?";

    private static final String GET_USERS_BY_ID = "SELECT * FROM users WHERE id=?";
    private static final String GET_ALL_USERS = "SELECT * FROM users ORDER BY id";
    private static final String CREATE_NEW_USER = "INSERT INTO users (id, login, name, access_level, pass) VALUES (nextval('users_seq'),?,?,'READER','pass')";
    private static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id=?";
    private static final String UPDATE_USER_ACCESS_LEVEL = "UPDATE users SET access_level = ? WHERE id = ?";


    @Override
    public User getUser(Long id) {
        User user = (User)jdbcOperations.queryForObject(
                GET_USERS_BY_ID, new Object[] { id },
                new BeanPropertyRowMapper(User.class));
        return user;
    }

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
    public void updateUserAccessLevel(Long id, AccessLevel accessLevel) {
        jdbcOperations.update(UPDATE_USER_ACCESS_LEVEL, accessLevel.toString(), id);
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = jdbcOperations.query(GET_ALL_AVAILABLE_BOOKS, new BeanPropertyRowMapper<Book>(Book.class));
        for (Book b : books) {
            b.setAuthors(jdbcOperations.query(GET_AUTHORS, new BeanPropertyRowMapper<Author>(Author.class), b.getId()));
        }
        return books;
    }

    private static final String ADD_BOOK = "INSERT INTO books (id, title, year, available) " +
            "VALUES (nextval('books_seq'), :title, :year, :available)";

    @Override
    public Book addBook(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource params = new MapSqlParameterSource().addValue("title", book.getTitle())
                .addValue("year", book.getYear()).addValue("available", book.getAvailable());
        namedParameterJdbcOperations.update(ADD_BOOK, params, keyHolder, new String[]{"id"});
        book.setId(keyHolder.getKey().longValue());
        return book;
    }

    private static final String GET_ALL_ORDER_BY_STATUS = "SELECT * FROM orders WHERE status = ?";
    private static final String SET_BOOK_STATUS = "UPDATE orders SET status = ? WHERE id = ?";

    @Override
    public List<Order> getAllOrderByStatus(Status status) {
        return jdbcOperations.query(GET_ALL_ORDER_BY_STATUS, new BeanPropertyRowMapper<>(Order.class), status.toString());
    }

    @Override
    public void setBookStatus(Status status, Long id) {
        jdbcOperations.update(SET_BOOK_STATUS, status.toString(), id);

    }

}
