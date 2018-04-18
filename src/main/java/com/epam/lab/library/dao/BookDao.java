package com.epam.lab.library.dao;

import com.epam.lab.library.domain.Author;
import com.epam.lab.library.domain.Book;
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

    Book getBook(Long bookId);
}
