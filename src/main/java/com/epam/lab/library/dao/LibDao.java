package com.epam.lab.library.dao;

import com.epam.lab.library.domain.Book;
import com.epam.lab.library.domain.Order;
import com.epam.lab.library.domain.Status;

import java.util.List;

public interface LibDao {

    /**
     * Creates new book
     *
     * @param book book to be created
     * @return book with id
     */
    Book addBook(Book book);

    List<Order> getAllOrderByStatus(Status status);

    List<Book> getAllBooks();

    void setBookStatus(Status status, Long id);
}
