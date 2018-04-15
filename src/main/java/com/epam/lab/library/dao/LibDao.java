package com.epam.lab.library.dao;

import com.epam.lab.library.domain.Book;

public interface LibDao {

    /**
     * Creates new book
     *
     * @param book book to be created
     * @return book with id
     */
    Book addBook(Book book);

}
