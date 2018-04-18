package com.epam.lab.library.service.impl;

import com.epam.lab.library.dao.BookDao;
import com.epam.lab.library.domain.Author;
import com.epam.lab.library.domain.Book;
import com.epam.lab.library.domain.Status;
import com.epam.lab.library.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void createBookAuthors(Long book_id, List<Long> list_author_id) {
        bookDao.createBookAuthors(book_id, list_author_id);
    }

    @Override
    public Book getBook(Long id) {
        return bookDao.getBook(id);
    }

    @Override
    public List<Long> createAuthors(String names) {
        return bookDao.createAuthors(names);
    }

    @Override
    public void setBookStatus(Status status, Long id) {
        bookDao.setBookStatus(status, id);
    }

    public Book addBook(Book book, String authors) {
        book = bookDao.addBook(book);
        if (book.getId() != null) createBookAuthors(book.getId(), createAuthors(authors));
        else book.setId(null);

        return book;
    }

    @Override
    public List<Book> getBooks(String searchingTitle,boolean showNotAvailable,String sortType){
        return bookDao.getBooks(searchingTitle,showNotAvailable,sortType);
    }

    @Override
    public Author getAuthor(String name) {
        return null;
    }

}
