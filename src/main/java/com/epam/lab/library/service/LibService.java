package com.epam.lab.library.service;

import com.epam.lab.library.domain.Order;

import java.util.List;

public interface LibService {

    List<Order> getAllOrderRequests();

    List<Order> getAllOrderGiven();
}
