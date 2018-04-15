package com.epam.lab.library.dao.impl;

import com.epam.lab.library.dao.LibDao;
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
public class LibDaoImpl implements LibDao {

    @Autowired
    NamedParameterJdbcOperations namedParameterJdbcOperations;

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
}
