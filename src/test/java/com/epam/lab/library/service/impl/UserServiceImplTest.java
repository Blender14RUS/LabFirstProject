package com.epam.lab.library.service.impl;

import com.epam.lab.library.dao.UserDao;
import com.epam.lab.library.domain.Location;
import com.epam.lab.library.domain.Order;
import com.epam.lab.library.domain.Status;
import com.epam.lab.library.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//class UserServiceImplTest {
//    private UserDao mockUserDao;
//    private UserService userService;
//
//    @BeforeEach
//    void setup() {
//        mockUserDao = mock(UserDao.class);
//        userService = new UserServiceImpl(mockUserDao);
//    }
//
//    @Test
//    void getAllOrderByStatus() {
//        //Given
//        Status status = Status.GIVEN;
//        Order order = new Order(1L, 1L, 1L, Location.HOME, status);
//        List<Order> expectedOrders = new ArrayList<>();
//        expectedOrders.add(order);
//        List<Order> orders = new ArrayList<>();
//        orders.add(new Order(1L, 1L, 1L, Location.HOME, status));
//        doReturn(orders).when(mockUserDao).getAllOrderByStatus(status);
//
//        //When
//        List<Order> actualOrders = userService.getAllOrderByStatus(status);
//
//        //Then
//        verify(mockUserDao).getAllOrderByStatus(status);
//        verifyNoMoreInteractions(mockUserDao);
//        assertEquals(expectedOrders, actualOrders);
//    }
//}
