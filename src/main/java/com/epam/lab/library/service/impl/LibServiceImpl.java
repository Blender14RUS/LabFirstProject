package com.epam.lab.library.service.impl;

import com.epam.lab.library.dao.LibDao;
import com.epam.lab.library.domain.*;
import com.epam.lab.library.service.LibService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibServiceImpl implements LibService {
    private static final Logger LOG = LoggerFactory.getLogger(LibServiceImpl.class);
    private LibDao libDao;

    @Autowired
    LibServiceImpl(LibDao libDao) {
        this.libDao = libDao;
    }

    @Override
    public List<Book> getAllBooks(){
        return libDao.getAllBooks();
    }

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

    @Override
    public List<Order> getAllOrderByStatus(Status status) {
        return libDao.getAllOrderByStatus(status);
    }

    @Override
    public void setBookStatus(Status status, Long id) {
        libDao.setBookStatus(status, id);
    }

    @Override
    public User getUser(Long id) {
        return libDao.getUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return libDao.getAllUsers();
    }

    @Override
    public int createUser(User user) {
        return libDao.createUser(user);
    }

    @Override
    public void deleteUserById(Long id) {
        libDao.deleteUserById(id);
    }

    @Override
    public void updateUserAccessLevel(Long id, AccessLevel accessLevel) {
        if (accessLevel.equals(AccessLevel.READER))
            accessLevel = AccessLevel.LIBRARIAN;
        else accessLevel = AccessLevel.READER;
        libDao.updateUserAccessLevel(id, accessLevel);

    }
}
