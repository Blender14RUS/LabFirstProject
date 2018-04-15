package com.epam.lab.library.service.impl;

import com.epam.lab.library.dao.LibDao;
import com.epam.lab.library.domain.Order;
import com.epam.lab.library.domain.Status;
import com.epam.lab.library.service.LibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibServiceImpl implements LibService{

    private LibDao libDao;

    @Autowired
    LibServiceImpl(LibDao libDao) {
        this.libDao = libDao;
    }

    @Override
    public List<Order> getAllOrderByStatus(Enum<Status> status) {
        return libDao.getAllOrderByStatus(status);
    }

    @Override
    public void setBookStatus(Enum<Status> status, Long id) {
        libDao.setBookStatus(status, id);
    }
}
