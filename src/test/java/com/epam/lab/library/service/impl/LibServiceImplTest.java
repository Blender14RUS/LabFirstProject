package com.epam.lab.library.service.impl;

import com.epam.lab.library.dao.LibDao;
import com.epam.lab.library.domain.Book;
import com.epam.lab.library.domain.Location;
import com.epam.lab.library.domain.Order;
import com.epam.lab.library.domain.Status;
import com.epam.lab.library.service.LibService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LibServiceImplTest {
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

    @Test
    void getAllOrderByStatus() {
        //Given
        Status status = Status.GIVEN;
        Order order = new Order(1L, 1L, 1L, Location.HOME, status);
        List<Order> expectedOrders = new ArrayList<>();
        expectedOrders.add(order);
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1L, 1L, 1L, Location.HOME, status));
        doReturn(orders).when(mockLibDao).getAllOrderByStatus(status);

        //When
        List<Order> actualOrders = libService.getAllOrderByStatus(status);

        //Then
        verify(mockLibDao).getAllOrderByStatus(status);
        verifyNoMoreInteractions(mockLibDao);
        assertEquals(expectedOrders, actualOrders);
    }
}
