package com.epam.lab.library.service.impl;

import com.epam.lab.library.dao.BookDao;
import com.epam.lab.library.domain.Book;
import com.epam.lab.library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookServiceImplTest {
    private BookDao mockBookDao;
    private BookService bookService;

    @BeforeEach
    void setup() {
        mockBookDao = mock(BookDao.class);
        bookService = new BookServiceImpl(mockBookDao);
    }

    @Test
    void addBook() {
    }

}
