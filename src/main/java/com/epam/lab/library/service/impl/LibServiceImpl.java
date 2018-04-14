package com.epam.lab.library.service.impl;

import com.epam.lab.library.dao.LibDAO;
import com.epam.lab.library.domain.Book;
import com.epam.lab.library.service.LibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibServiceImpl implements LibService {

    private LibDAO libDao;

    @Autowired
    LibServiceImpl(LibDAO libDao) {
        this.libDao = libDao;
    }

    @Override
    public long createBook(Book book) {
        return libDao.createBook(book);
    }
}
