package com.epam.lab.library.service.impl;

import com.epam.lab.library.dao.OrderDao;
import com.epam.lab.library.service.OrderService;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public class OrderServiceImplTest {
    private OrderDao mockOrderDao;
    private OrderService orderService;

    @BeforeEach
    void setup() {
        mockOrderDao = mock(OrderDao.class);
        orderService = new OrderServiceImpl();
    }
}
