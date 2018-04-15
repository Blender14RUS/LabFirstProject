package com.epam.lab.library.service.impl;

import com.epam.lab.library.dao.LibDao;
import com.epam.lab.library.domain.Book;
import com.epam.lab.library.service.LibService;
import com.epam.lab.library.web.controller.BookController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class LibServiceImpl implements LibService {

    private static final Logger LOG = LoggerFactory.getLogger(LibServiceImpl.class);

    private LibDao libDao;

    @Autowired
    LibServiceImpl(LibDao libDao) {
        this.libDao = libDao;
    }

    @Override
    public Book addBook(Book book) {
        try {
            book = libDao.addBook(book);
        }
        catch(DataAccessException e) {
            LOG.error("Book not add to database -- " + e);
            book.setId(null);
        }
        return book;
    }
}
