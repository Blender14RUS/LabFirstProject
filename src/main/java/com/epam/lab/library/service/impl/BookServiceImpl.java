package com.epam.lab.library.service.impl;

import com.epam.lab.library.dao.BookDao;
import com.epam.lab.library.domain.Author;
import com.epam.lab.library.domain.Book;
import com.epam.lab.library.domain.Order;
import com.epam.lab.library.domain.Status;
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
    public Book getBook(Long bookId) {
        return bookDao.getBook(bookId);
    }

    @Override
    public Book editBook(Book book, String authors) {
        bookDao.editBook(book);
        bookDao.deleteBookAuthors(book.getId());
        createBookAuthors(book.getId(), createAuthors(authors));
        return bookDao.getBook(book.getId());
    }

    @Override
    public List<Long> createAuthors(String names) {
        String[] arrayNames = names.split(",");
        List<Long> authorId = new ArrayList<>();
        for (String name : arrayNames) {
            if (bookDao.checkAuthor(name.trim()) > 0) {
                Author author = bookDao.getAuthor(name.trim());
                authorId.add(author.getId());
            } else {
                authorId.add(bookDao.addAuthor(name.trim()));
            }
        }
        return authorId;
    }

    @Override
    public void setBookStatus(Status status, Long id) {
        bookDao.setBookStatus(status, id);
    }

    @Override
    public Book addBook(Book book, String authors) {
        int row = bookDao.checkBook(book.getTitle(), book.getYear());
        if (row == 0) {
            book = bookDao.addBook(book);
            LOG.info(book.toString());
            createBookAuthors(book.getId(), createAuthors(authors));
        }
        return book;
    }

    @Override
    public List<Book> getBooks(String searchingTitle, boolean showNotAvailable) {
        return bookDao.getBooks(searchingTitle, showNotAvailable);
    }

    @Override
    public Order requestBook(Order order) {
        Book book = getBook(order.getBookId());
        if (book.getAvailable() > 0) {
            bookDao.requestBook(book);
            if (book.getAvailable() > getBook(order.getBookId()).getAvailable())
                bookDao.createOrder(order);
        }
        return order;
    }
}
