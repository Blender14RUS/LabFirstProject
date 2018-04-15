package com.epam.lab.library.service;

import com.epam.lab.library.domain.Book;
import org.springframework.dao.DataAccessException;

public interface LibService {

    /**
     * Creates new book
     *
     * @param book book to be created
     * @return book with id
     */
    Book addBook(Book book) throws DataAccessException;

}
