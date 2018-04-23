package com.epam.lab.library.service.impl;

import com.epam.lab.library.dao.BookDao;
import com.epam.lab.library.domain.Book;
import com.epam.lab.library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookServiceImplTest {
    private BookDao mockBookDao;
    private BookService bookService;

    @BeforeEach
    void setup() {
        bookService = mock(BookService.class);
    }

    @Test
    void addBook() {
        //Given
        String author = "Igor";
        Long expectedBookID = 1L;
        Book book = new Book(null, "Title", 2015, 5);
        doReturn(1L)
                .when(bookService).addBook(book, author);

        //When
        Long actualBookID = bookService.addBook(book, author);

        //Then
        verify(bookService).addBook(book, author);
        verifyNoMoreInteractions(bookService);
        assertEquals(expectedBookID, actualBookID);
    }

    @Test
    void updateBook() {
        //Given
        String author = "Igor";
        Book expectedBook = new Book(1L, "Title", 2015, 4);
        Book book = new Book(1L, "Title", 2015, 5);
        doReturn(new Book(1L, "Title", 2015, 4))
                .when(bookService).updateBook(book, author);

        //When
        Book actualBook = bookService.updateBook(book, author);

        //Then
        verify(bookService).updateBook(book, author);
        verifyNoMoreInteractions(bookService);
        assertEquals(expectedBook, actualBook);
    }

    @Test
    void getBook() {
        //Given
        Long bookId = 14L;
        Book expectedBook = new Book(14L, "Title", 2015, 4);
        doReturn(new Book(14L, "Title", 2015, 4))
                .when(bookService).getBook(bookId);

        //When
        Book actualBook = bookService.getBook(bookId);

        //Then
        verify(bookService).getBook(bookId);
        verifyNoMoreInteractions(bookService);
        assertEquals(expectedBook, actualBook);
    }

    @Test
    void createAuthorIdList() {
        //Given
        String names = "Igor, Vasya";
        List<Long> expectedList = Arrays.asList(1L, 3L);
        doReturn(Arrays.asList(1L, 3L))
                .when(bookService).createAuthorIdList(names);

        //When
        List<Long> actualList = bookService.createAuthorIdList(names);

        //Then
        verify(bookService).createAuthorIdList(names);
        verifyNoMoreInteractions(bookService);
        assertEquals(expectedList, actualList);
    }

    @Test
    void getBooks() {
        //Given
        String searchingTitle = "War";
        List<Book> expectedList = Arrays.asList(new Book(1L, "War and Peace", 1898, 3),
                new Book(15L, "Zombie War", 2010, 19));
        doReturn(Arrays.asList(new Book(1L, "War and Peace", 1898, 3),
                new Book(15L, "Zombie War", 2010, 19)))
                .when(bookService).getBooks(searchingTitle, false, "");

        //When
        List<Book> actualList = bookService.getBooks(searchingTitle, false, "");

        //Then
        verify(bookService).getBooks(searchingTitle, false, "");
        verifyNoMoreInteractions(bookService);
        assertEquals(expectedList, actualList);
    }
}
