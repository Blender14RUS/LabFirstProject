package com.epam.lab.library.dao.impl;

import com.epam.lab.library.dao.BookDao;
import com.epam.lab.library.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class BookDaoImpl implements BookDao {

    private static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

    private static final String SELECT_BOOK = "SELECT count(*) FROM books WHERE title = ? AND year = ?";
    private static final String ADD_BOOK = "INSERT INTO books (id, title, year, available) " +
            "VALUES (nextval('books_seq'), :title, :year, :available)";
    private static final String SET_BOOK_STATUS = "UPDATE orders SET status = ? WHERE id = ?";
    private static final String CREATE_NEW_AUTHOR = "INSERT INTO authors (id, name) " +
            "VALUES (nextval('authors_seq'), :name)";
    private static final String CREATE_BOOK_AUTHORS = "INSERT INTO book_authors (book_id, author_id) VALUES (:bookId, :authorId)";
    private static final String GET_AUTHOR = "SELECT * FROM authors WHERE name = ?";
    private static final String SELECT_AUTHOR = "SELECT count(*) FROM authors WHERE name = ?";
    private static final String GET_AUTHORS = "SELECT * FROM authors JOIN book_authors ON authors.id = book_authors.author_id WHERE book_authors.book_id=?";
    private static final String GET_ALL_AVAILABLE_BOOKS = "SELECT * FROM books WHERE available!=0";
    private static final String GET_BOOK_BY_ID = "SELECT * FROM books WHERE id=?";
    private static final String UPDATE_BOOK_BY_ID = "UPDATE books SET title = :title, year = :year, available = :available WHERE id = :id";
    private static final String DELETE_BOOK_AUTHORS = "DELETE FROM book_authors WHERE book_id = :id";
    private static final String CREATE_ORDER = "INSERT INTO orders (id, user_id, book_id, location, status) " +
            "VALUES (nextval('orders_seq'), :userId, :bookId, :location, :status)";
    private static final String REQUEST_BOOK = "UPDATE books SET available = :newCount WHERE id = :bookId AND available = :nowCount";

    @Autowired
    private JdbcOperations jdbcOperations;
    @Autowired
    private NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public int checkAuthor(String name) {
        return jdbcOperations.queryForObject(SELECT_AUTHOR, new Object[]{name}, Integer.class);
    }

    @Override
    public Author getAuthor(String name) {
        return jdbcOperations.queryForObject(GET_AUTHOR, new Object[]{name}, new BeanPropertyRowMapper<>(Author.class));
    }

    @Override
    public List<Book> getBooks(String searchingTitle, boolean showNotAvailable) {
        Filter filter = new Filter(searchingTitle, showNotAvailable);
        List<Book> books = jdbcOperations.query(filter.getSelect(), new BeanPropertyRowMapper<>(Book.class));
        for (Book b : books) {
            b.setAuthors(jdbcOperations.query(GET_AUTHORS, new BeanPropertyRowMapper<>(Author.class), b.getId()));
        }
        return books;
    }

    @Override
    public Book addBook(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource params = new MapSqlParameterSource().addValue("title", book.getTitle())
                .addValue("year", book.getYear()).addValue("available", book.getAvailable());
        namedParameterJdbcOperations.update(ADD_BOOK, params, keyHolder, new String[]{"id"});
        book.setId(keyHolder.getKey().longValue());
        return book;
    }

    @Override
    public Long addAuthor(String name) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource params = new MapSqlParameterSource().addValue("name", name);
        namedParameterJdbcOperations.update(CREATE_NEW_AUTHOR, params, keyHolder, new String[]{"id"});
        return keyHolder.getKey().longValue();
    }

    @Override
    public void deleteBookAuthors(Long bookId) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", bookId);
        namedParameterJdbcOperations.update(DELETE_BOOK_AUTHORS, params);
    }

    @Override
    public void setBookStatus(Status status, Long id) {
        jdbcOperations.update(SET_BOOK_STATUS, status.toString(), id);
    }

    @Override
    public void createBookAuthors(Long bookId, Long authorId) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("bookId", bookId).addValue("authorId", authorId);
        namedParameterJdbcOperations.update(CREATE_BOOK_AUTHORS, params);
    }

    @Override
    public Book getBook(Long bookId) {
        Book book = jdbcOperations.queryForObject(GET_BOOK_BY_ID, new Object[]{bookId}, new BeanPropertyRowMapper<>(Book.class));
        book.setAuthors(jdbcOperations.query(GET_AUTHORS, new BeanPropertyRowMapper<>(Author.class), book.getId()));
        return book;
    }

    @Override
    public int checkBook(String title, int year) {
        return jdbcOperations.queryForObject(SELECT_BOOK, new Object[]{title, year}, Integer.class);
    }

    @Override
    public void editBook(Book book) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("title", book.getTitle())
                .addValue("year", book.getYear()).addValue("available", book.getAvailable())
                .addValue("id", book.getId());
        namedParameterJdbcOperations.update(UPDATE_BOOK_BY_ID, params);
    }

    @Override
    public void requestBook(Book book) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("newCount", book.getAvailable() - 1)
                .addValue("bookId", book.getId()).addValue("nowCount", book.getAvailable());
        namedParameterJdbcOperations.update(REQUEST_BOOK, params);
    }

    @Override
    public Order createOrder(Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource params = new MapSqlParameterSource().addValue("userId", order.getUserId()).addValue("bookId", order.getBookId())
                .addValue("location", order.getLocation()).addValue("status", order.getStatus());
        namedParameterJdbcOperations.update(CREATE_ORDER, params, keyHolder, new String[]{"id"});
        order.setId(keyHolder.getKey().longValue());
        return order;
    }

}
