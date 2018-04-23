package com.epam.lab.library.service.impl;

import com.epam.lab.library.dao.BookDao;
import com.epam.lab.library.domain.Book;
import com.epam.lab.library.service.BookService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    private BookDao bookDao;

    @Autowired
    BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void createBookAuthors(Long bookId, List<Long> listAuthorId) {
        for (Long authorId : listAuthorId) {
            if (!bookDao.createBookAuthors(bookId, authorId)) {
                LOG.error("Book authors don't inserted to database.");
            }
        }
    }

    @Override
    public Book updateBook(Book book, String authors) {
        if (!bookDao.updateBook(book) || !bookDao.deleteBookAuthors(book.getId())) {
            LOG.error("Update book failed.");
        }
        createBookAuthors(book.getId(), createAuthorIdList(authors));
        return bookDao.getBook(book.getId());
    }

    @Override
    public Book getBook(Long id) {
        return bookDao.getBook(id);
    }

    @Override
    public List<Long> createAuthorIdList(String names) {
        String[] arrayNames = names.split(",");
        List<Long> authorIdList = new ArrayList<>();
        for (String name : arrayNames) {
            if (bookDao.authorExist(name.trim())) {
                authorIdList.add(bookDao.getAuthor(name.trim()));
            } else {
                authorIdList.add(bookDao.addAuthor(name.trim()));
            }
        }
        if (authorIdList.isEmpty()) {
            LOG.error("Authors list is empty.");
        }
        return authorIdList;
    }

    @Override
    public Long addBook(Book book, String authors) {
        Long bookId = null;
        if (bookDao.checkBookInDB(book.getTitle(), book.getYear())) {
            bookId = bookDao.addBook(book);
            LOG.debug("Created new book with id: " + bookId);
            createBookAuthors(bookId, createAuthorIdList(authors));
        } else {
            LOG.warn("Book already exist.");
        }
        return bookId;
    }

    @Override
    public List<Book> getBooks(String searchingTitle, boolean showNotAvailable, String sortType) {
        return bookDao.getBooks(searchingTitle, showNotAvailable, sortType);
    }

    @Override
    public boolean deleteBook(Long id) {
        return bookDao.deleteBook(id);
    }

    @Override
    public boolean deleteBookAuthors(Long id) {
        return bookDao.deleteBookAuthors(id);
    }

}
