package com.epam.lab.library.service.impl;

import com.epam.lab.library.dao.LibDAO;
import com.epam.lab.library.domain.Book;
import com.epam.lab.library.service.LibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibServiceImpl implements LibService{

    private LibDAO libDao;

    @Autowired
    LibServiceImpl(LibDAO libDao) {
        this.libDao = libDao;
    }

    @Override
    public List<Book> getAllBooks(){
        return libDao.getAllBooks();
    }

}
