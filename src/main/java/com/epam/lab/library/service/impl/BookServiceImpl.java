package com.epam.lab.library.service.impl;

import com.epam.lab.library.dao.BookDao;
import com.epam.lab.library.domain.*;
import com.epam.lab.library.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private BookDao bookDao;

    @Autowired
    private DataBaseUserDetailService detailsService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void createBookAuthors(Long bookId, List<Long> listAuthorId) {
        for (Long authorId : listAuthorId) {
            bookDao.createBookAuthors(bookId, authorId);
        }
    }

    @Override
    public Book updateBook(Book book, String authors) {
        bookDao.updateBook(book);
        bookDao.deleteBookAuthors(book.getId());
        createBookAuthors(book.getId(), createAuthors(authors));
        return bookDao.getBook(book.getId());
    }

    @Override
    public Book getBook(Long id) {
        return bookDao.getBook(id);
    }

    @Override
    public List<Long> createAuthors(String names) {
        String[] arrayNames = names.split(",");
        List<Long> authorIdList = new ArrayList<>();
        for (String name : arrayNames) {
            if (bookDao.checkAuthor(name.trim()) > 0) {
                Author author = bookDao.getAuthor(name.trim());
                authorIdList.add(author.getId());
            } else {
                authorIdList.add(bookDao.addAuthor(name.trim()));
            }
        }
        return authorIdList;
    }

    @Override
    public void setBookStatus(Status status, Long id) {
        bookDao.setBookStatus(status, id);
        bookDao.plusBook(id);
    }

    @Override
    public Book addBook(Book book, String authors) {
        int row = bookDao.checkBook(book.getTitle(), book.getYear());
        if (row == 0) {
            book = bookDao.addBook(book);
            LOG.info("Created new book = {}", book);
            createBookAuthors(book.getId(), createAuthors(authors));
        }
        return book;
    }

    @Override
    public List<Book> getBooks(String searchingTitle, boolean showNotAvailable, String sortType) {
        return bookDao.getBooks(searchingTitle, showNotAvailable, sortType);
    }

    @Override
    public Order requestBook(Order order) {
        String userName = detailsService.getCurrentUsername();
        User user = userService.getUserByLogin(userName);
        order.setUserId(user.getId());
        Book book = getBook(order.getBookId());
        Order orderCreated = new Order();
        if (book.getAvailable() > 0 && bookDao.checkOrderGiven(order.getBookId(), order.getUserId()) == 0) {
            int countRequested = bookDao.checkOrderRequested(order.getBookId(), order.getUserId());
            if (countRequested > 0)
                return orderCreated;
            bookDao.requestBook(book);
            if (book.getAvailable() > getBook(order.getBookId()).getAvailable())
                orderCreated = bookDao.createOrder(order);
        }
        return orderCreated;
    }


}
