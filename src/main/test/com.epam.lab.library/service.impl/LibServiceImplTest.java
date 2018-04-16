package com.epam.lab.library.service.impl;

import com.epam.lab.library.dao.LibDao;
import com.epam.lab.library.domain.Book;
import com.epam.lab.library.service.LibService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibServiceImplTest {

    private LibDao mockLibDao;
    private LibService libService;

    @BeforeEach
    void setup() {
        mockLibDao = mock(LibDao.class);
        libService = new LibServiceImpl(mockLibDao);
    }

    @Test
    void addBook() {
        //Given
        Long id = 1L;
        Book expectedBook = new Book(id, "Title", 2000, 42);
        doReturn(new Book(id, "Title", 2000, 42)).when(mockLibDao)
                .addBook(new Book("Title", 2000, 42));

        //When
        Book actualBook = libService.addBook(new Book("Title", 2000, 42));

        //Then
        verify(mockLibDao).addBook(new Book("Title", 2000, 42));
        verifyNoMoreInteractions(mockLibDao);
        assertEquals(expectedBook, actualBook);
    }
}
