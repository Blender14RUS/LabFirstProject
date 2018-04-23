package com.epam.lab.library.service.impl;

import com.epam.lab.library.dao.OrderDao;
import com.epam.lab.library.domain.Location;
import com.epam.lab.library.domain.Order;
import com.epam.lab.library.domain.Status;
import com.epam.lab.library.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {
    private OrderDao mockOrderDao;
    private OrderService orderService;

    @BeforeEach
    void setup() {
        orderService = mock(OrderService.class);
    }

    @Test
    void requestBook() {
        //Given
        Order expectedOrder = new Order(5L, 1L, 1L, Location.HOME, Status.REQUESTED);
        Order order = new Order(null, 1L, 1L, Location.HOME, Status.REQUESTED);
        doReturn(new Order(5L, 1L, 1L, Location.HOME, Status.REQUESTED))
                .when(orderService).requestBook(order);

        //When
        Order actualOrder = orderService.requestBook(order);

        //Then
        verify(orderService).requestBook(order);
        verifyNoMoreInteractions(orderService);
        assertEquals(expectedOrder, actualOrder);
    }

}
