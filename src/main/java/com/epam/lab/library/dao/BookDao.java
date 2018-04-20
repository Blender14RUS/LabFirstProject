package com.epam.lab.library.dao;

import com.epam.lab.library.domain.Author;
import com.epam.lab.library.domain.Book;
import com.epam.lab.library.domain.Order;
import com.epam.lab.library.domain.Status;

import java.util.List;

public interface BookDao {

    /**
     * Creates new book
     *
     * @param book an instances of Book filled with data (not include id) must be recorded in the database
     * @return instances of Book with id
     */
    Book addBook(Book book);

    /**
     * Selects list of books
     *
     * @param searchingTitle
     * @return
     */
    List<Book> getBooks(String searchingTitle,boolean showNotAvailable,String sortType);

    /**
     * Set book status
     *
     * @param status
     * @param id
     */
    void setBookStatus(Status status, Long id);

    /**
     * Takes author data from database by author name
     * if author with such name does not exist in database - inserting author
     *
     * @param name
     * @return
     */
    Author getAuthor(String name);

    void createBookAuthors(Long bookId, Long authorId);

    Book getBook(Long id);

    int checkBook(String title, int year);

    void updateBook(Book book);

    int checkAuthor(String name);

    Long addAuthor(String name);

    void deleteBookAuthors(Long bookId);

    void requestBook(Book book);

    Order createOrder(Order order);

    int checkOrderGiven(Long bookId, Long userId);

    int checkOrderRequested(Long bookId, Long userId);

}
