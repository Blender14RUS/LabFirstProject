package com.epam.lab.library.service;

import com.epam.lab.library.domain.Book;
import com.epam.lab.library.domain.Order;
import com.epam.lab.library.domain.Status;

import java.util.List;

public interface BookService {

    /**
     * Creates new book with all authors
     *
     * @param book    an instances of Book filled with data (not include id) must be recorded in the database
     * @param authors String with all names authors
     * @return instances of Book with id
     */
    Book addBook(Book book, String authors);

    /**
     * Returns list of Books
     *
     * @return books
     */
    List<Book> getBooks(String searchingTitle, boolean showNotAvailable);

    /**
     * Set book status
     *
     * @param status
     * @param id
     */
    void setBookStatus(Status status, Long id);


    /**
     * Splitting names and added author_id to list
     * if author does not exist in database - inserting author and add author_id to list
     *
     * @param names String with several author name
     * @return List of author_id
     */
    List<Long> createAuthors(String names);

    /**
     * Add author_id for book_id
     *
     * @param book_id        Book id
     * @param list_author_id List of author for book
     */
    void createBookAuthors(Long book_id, List<Long> list_author_id);

    Book getBook(Long book_id);

    Book editBook(Book book, String authors);

    Order requestBook(Order order);
}
