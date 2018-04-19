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
//        //Given
//        Long id = 1L;
//        String author = " ";
//        Book expectedBook = new Book(id, "Title", 2000, 42);
//        doReturn(new Book(id, "Title", 2000, 42)).when(mockBookDao)
//                .addBook(new Book("Title", 2000, 42));
//
//        //When
//        Book actualBook = bookService.addBook(new Book("Title", 2000, 42), author);
//
//        //Then
//        verify(mockBookDao).addBook(new Book("Title", 2000, 42));
//        assertEquals(expectedBook, actualBook);
    }

}
