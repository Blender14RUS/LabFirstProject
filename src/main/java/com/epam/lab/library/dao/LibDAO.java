package com.epam.lab.library.dao;

import com.epam.lab.library.domain.Order;
import com.epam.lab.library.domain.Status;

import java.util.List;

public interface LibDao {

    List<Order> getAllOrderByStatus(Enum<Status> status);

    void setBookStatus(Enum<Status> status, Long id);
}
