package com.epam.lab.library.dao;

import com.epam.lab.library.domain.Book;

import java.util.List;

public interface LibDAO {

    /**
    *Returns list of books.
    * @return books.
    */
     List<Book> getAllBooks();
}
