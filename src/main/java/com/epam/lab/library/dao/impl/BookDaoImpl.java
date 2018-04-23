package com.epam.lab.library.dao.impl;

import com.epam.lab.library.dao.BookDao;
import com.epam.lab.library.domain.Author;
import com.epam.lab.library.domain.Book;
import com.epam.lab.library.domain.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    private static final String SELECT_AUTHOR = "SELECT COUNT(*) FROM authors WHERE name = ?";
    private static final String GET_AUTHOR = "SELECT id FROM authors WHERE name = ?";
    private static final String GET_AUTHORS = "SELECT * FROM authors JOIN book_authors ON authors.id = book_authors.author_id WHERE book_authors.book_id = ?";
    private static final String ADD_BOOK = "INSERT INTO books (id, title, year, available) " +
            "VALUES (nextval('books_seq'), :title, :year, :available)";
    private static final String CREATE_NEW_AUTHOR = "INSERT INTO authors (id, name) " +
            "VALUES (nextval('authors_seq'), :name)";
    private static final String DELETE_BOOK_AUTHORS = "DELETE FROM book_authors WHERE book_id = :id";
    private static final String DELETE_BOOK = "DELETE FROM books WHERE id = :id";
    private static final String CREATE_BOOK_AUTHORS = "INSERT INTO book_authors (book_id, author_id) VALUES (:bookId, :authorId)";
    private static final String GET_BOOK_BY_ID = "SELECT * FROM books WHERE id = ?";
    private static final String SELECT_BOOK = "SELECT COUNT(*) FROM books WHERE title = ? AND year = ?";
    private static final String UPDATE_BOOK_BY_ID = "UPDATE books SET title = :title, year = :year, available = :available WHERE id = :id";
    private static final String REQUEST_BOOK = "UPDATE books SET available = :newCount WHERE id = :bookId AND available = :nowCount";
    private static final String INC_BOOK_COUNT = "UPDATE books SET available = available + 1 WHERE id = ?";
    @Autowired
    private NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public boolean authorExist(String name) {
        return namedParameterJdbcOperations.getJdbcOperations().queryForObject(SELECT_AUTHOR, Integer.class, name) > 0;
    }

    @Override
    public boolean deleteBook(Long id) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        return namedParameterJdbcOperations.update(DELETE_BOOK, params) > 0;
    }

    @Override
    public Long getAuthor(String name) {
        return namedParameterJdbcOperations.getJdbcOperations().queryForObject(GET_AUTHOR, Long.class, name);
    }

    @Override
    public List<Book> getBooks(String searchingTitle, boolean showNotAvailable, String sortType) {
        Filter filter = new Filter(searchingTitle, showNotAvailable, sortType);
        List<Book> books;
        if (!searchingTitle.equals(""))
            books = namedParameterJdbcOperations.getJdbcOperations().query(filter.getSelect(), new BeanPropertyRowMapper<>(Book.class), "%" + searchingTitle + "%");
        else
            books = namedParameterJdbcOperations.getJdbcOperations().query(filter.getSelect(), new BeanPropertyRowMapper<>(Book.class));
        for (Book b : books) {
            b.setAuthors(namedParameterJdbcOperations.getJdbcOperations().query(GET_AUTHORS, new BeanPropertyRowMapper<>(Author.class), b.getId()));
        }
        return books;
    }

    @Override
    public Long addBook(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource params = new MapSqlParameterSource().addValue("title", book.getTitle())
                .addValue("year", book.getYear()).addValue("available", book.getAvailable());
        namedParameterJdbcOperations.update(ADD_BOOK, params, keyHolder, new String[]{"id"});
        return keyHolder.getKey().longValue();
    }

    @Override
    public Long addAuthor(String name) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource params = new MapSqlParameterSource().addValue("name", name);
        namedParameterJdbcOperations.update(CREATE_NEW_AUTHOR, params, keyHolder, new String[]{"id"});
        return keyHolder.getKey().longValue();
    }

    @Override
    public boolean deleteBookAuthors(Long bookId) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", bookId);
        return namedParameterJdbcOperations.update(DELETE_BOOK_AUTHORS, params) > 0;
    }

    @Override
    public boolean createBookAuthors(Long bookId, Long authorId) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("bookId", bookId).addValue("authorId", authorId);
        return namedParameterJdbcOperations.update(CREATE_BOOK_AUTHORS, params) > 0;
    }

    @Override
    public Book getBook(Long bookId) {
        Book book = namedParameterJdbcOperations.getJdbcOperations().queryForObject(GET_BOOK_BY_ID, new BeanPropertyRowMapper<>(Book.class), bookId);
        book.setAuthors(namedParameterJdbcOperations.getJdbcOperations().query(GET_AUTHORS, new BeanPropertyRowMapper<>(Author.class), book.getId()));
        return book;
    }

    @Override
    public boolean checkBookInDB(String title, int year) {
        return namedParameterJdbcOperations.getJdbcOperations().queryForObject(SELECT_BOOK, Integer.class, title, year) == 0;
    }

    @Override
    public boolean updateBook(Book book) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("title", book.getTitle())
                .addValue("year", book.getYear()).addValue("available", book.getAvailable())
                .addValue("id", book.getId());
        return namedParameterJdbcOperations.update(UPDATE_BOOK_BY_ID, params) > 0;
    }

    @Override
    public boolean requestBook(Book book) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("newCount", book.getAvailable() - 1)
                .addValue("bookId", book.getId()).addValue("nowCount", book.getAvailable());
        return namedParameterJdbcOperations.update(REQUEST_BOOK, params) > 0;
    }

    @Override
    public boolean incrementBookCountAvailable(Long id) {
        return namedParameterJdbcOperations.getJdbcOperations().update(INC_BOOK_COUNT, id) > 0;
    }

}
