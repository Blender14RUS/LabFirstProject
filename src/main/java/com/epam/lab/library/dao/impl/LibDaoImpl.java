package com.epam.lab.library.dao.impl;

import com.epam.lab.library.dao.LibDAO;
import com.epam.lab.library.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LibDaoImpl implements LibDAO {

    @Autowired
    private JdbcOperations jdbcOperations;

    @Autowired
    NamedParameterJdbcOperations namedParameterJdbcOperations;

    private static final String ADD_BOOK = "INSERT INTO books (id, title, year, available) " +
                                                "VALUES (nextval('books_seq'), :title, :year, :available)";

    @Override
    public long createBook(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> bookValues = new HashMap<>();
        bookValues.put("title", book.getTitle());
        bookValues.put("year", book.getYear());
        bookValues.put("available", book.getAvailable());
        SqlParameterSource params = new MapSqlParameterSource().addValues(bookValues);
        namedParameterJdbcOperations.update(ADD_BOOK, params, keyHolder, new String[]{"id"});
        return keyHolder.getKey().longValue();
    }
}
