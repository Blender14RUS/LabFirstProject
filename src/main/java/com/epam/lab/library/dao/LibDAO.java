package com.epam.lab.library.dao;

import com.epam.lab.library.domain.Order;

import java.util.List;

public interface LibDao {

    List<Order> getAllOrderRequests();

    List<Order> getAllOrderGiven();
}
