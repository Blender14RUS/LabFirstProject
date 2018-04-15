package com.epam.lab.library.service;

import com.epam.lab.library.domain.Order;
import com.epam.lab.library.domain.Status;

import java.util.List;

public interface LibService {

    List<Order> getAllOrderByStatus(Enum<Status> status);

    void setBookStatus(Enum<Status> status, Long id);
}
