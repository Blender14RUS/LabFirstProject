package com.epam.lab.library.service;

import com.epam.lab.library.domain.Book;

import java.util.List;

public interface LibService {

    /**
    * Returns list of Books
    * @return books
     */
    List<Book> getAllBooks();


}
