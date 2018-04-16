package com.epam.lab.library.service;

import com.epam.lab.library.domain.Book;
import org.springframework.dao.DataAccessException;
import com.epam.lab.library.domain.Order;
import com.epam.lab.library.domain.Status;

import java.util.List;

public interface LibService {

     /**
     * Creates new book
     *
     * @param book book to be created
     * @return book with id
     */
    Book addBook(Book book) throws DataAccessException;
  
    List<Order> getAllOrderByStatus(Status status);

    void setBookStatus(Status status, Long id);
}
