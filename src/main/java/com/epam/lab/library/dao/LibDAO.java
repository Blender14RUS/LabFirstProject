package com.epam.lab.library.dao;

import com.epam.lab.library.domain.Book;

public interface LibDAO {
    /**
     * Creates book in the database
     *
     * @param book book to be created
     * @return id of created book
     */
    long createBook(Book book);

}
