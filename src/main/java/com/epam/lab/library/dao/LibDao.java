package com.epam.lab.library.dao;

import com.epam.lab.library.domain.Order;
import com.epam.lab.library.domain.Status;

import java.util.List;

public interface LibDao {

    List<Order> getAllOrderByStatus(Status status);

    void setBookStatus(Status status, Long id);
}