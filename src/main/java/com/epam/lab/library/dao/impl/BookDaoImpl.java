package com.epam.lab.library.dao.impl;

import com.epam.lab.library.dao.BookDao;
import com.epam.lab.library.domain.Author;
import com.epam.lab.library.domain.Book;
import com.epam.lab.library.domain.Filter;
import com.epam.lab.library.domain.Status;
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

import java.util.ArrayList;
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
    private static final String CREATE_BOOK_AUTHORS = "INSERT INTO book_authors (book_id, author_id) VALUES (:book_id, :author_id)";
    private static final String GET_AUTHOR = "SELECT * FROM authors WHERE name = ?";
    private static final String GET_AUTHORS = "SELECT * FROM authors JOIN book_authors ON authors.id = book_authors.author_id WHERE book_authors.book_id=?";
    private static final String GET_ALL_AVAILABLE_BOOKS = "SELECT * FROM books WHERE available != 0";
    private static final String GET_BOOK_BY_ID = "SELECT * FROM books WHERE id=?";

    @Autowired
    private JdbcOperations jdbcOperations;
    @Autowired
    private NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public Author getAuthor(String name) {
        Author author = new Author();
        int rowCount = jdbcOperations.queryForObject(GET_AUTHOR, new Object[]{name}, Integer.class);
        if (rowCount >= 0) {
            LOG.info("getAuthor: " + author.getId() + " " + author.getName());
            return jdbcOperations.queryForObject(
                    GET_AUTHOR, new Object[]{name},
                    new BeanPropertyRowMapper<>(Author.class));
        }
        author.setId(null);
        author.setName(null);
        LOG.info("getAuthor: " + author.getId() + " " + author.getName());
        return author;
    }

    @Override
    public List<Book> getBooks(String searchingTitle,boolean showNotAvailable,String sortType) {
        Filter filter= new Filter(searchingTitle,showNotAvailable,sortType);
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
        int rowCount = jdbcOperations.queryForObject(SELECT_BOOK, new Object[]{book.getTitle(), book.getYear()}, Integer.class);
        if (rowCount == 0) {
            namedParameterJdbcOperations.update(ADD_BOOK, params, keyHolder, new String[]{"id"});
            book.setId(keyHolder.getKey().longValue());

        } else {
            book.setId(null);
        }
        LOG.info("addBook: " + book.toString());
        return book;
    }

    @Override
    public List<Long> createAuthors(String names) {
        String[] arrayNames = names.split(",");
        List<Long> author_id = new ArrayList<>();
        for (String name : arrayNames) {
            Author author = getAuthor(name.trim());
            KeyHolder keyHolder = new GeneratedKeyHolder();
            SqlParameterSource params = new MapSqlParameterSource().addValue("name", name.trim());
            if (author.getId() != null) {
                author_id.add(author.getId());
            } else {
                namedParameterJdbcOperations.update(CREATE_NEW_AUTHOR, params, keyHolder, new String[]{"id"});
                author_id.add(keyHolder.getKey().longValue());
            }
        }
        LOG.info("createAuthors (all id authors): " + author_id.toString());
        return author_id;
    }

    @Override
    public void setBookStatus(Status status, Long id) {
        jdbcOperations.update(SET_BOOK_STATUS, status.toString(), id);

    }

    @Override
    public void createBookAuthors(Long book_id, List<Long> list_author_id) {
        for (Long author_id : list_author_id) {
            SqlParameterSource params = new MapSqlParameterSource().addValue("book_id", book_id).addValue("author_id", author_id);
            namedParameterJdbcOperations.update(CREATE_BOOK_AUTHORS, params);
            LOG.info(String.format("write book_id = %s, author_id = %s", book_id, author_id));
        }
    }

    @Override
    public Book getBook(Long bookId) {
        Book book = jdbcOperations.queryForObject(GET_BOOK_BY_ID, new Object[]{bookId}, new BeanPropertyRowMapper<>(Book.class));
        book.setAuthors(jdbcOperations.query(GET_AUTHORS, new BeanPropertyRowMapper<>(Author.class), book.getId()));
        return book;
    }

}
