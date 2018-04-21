package com.epam.lab.library.service;

import com.epam.lab.library.domain.Book;

import java.util.List;

public interface BookService {

    /**
     * Creates new book with all authors
     *
     * @param book    an instances of Book filled with data (not include id) must be recorded in the database
     * @param authors String with all names authors
     * @return instances of Book with id
     */
    Long addBook(Book book, String authors);

    /**
     * Returns list of Books
     *
     * @return books
     */
    List<Book> getBooks(String searchingTitle, boolean showNotAvailable, String sortType);

    /**
     * Splitting names and added author_id to list
     * if author does not exist in database - inserting author and add author_id to list
     *
     * @param names String with several author name
     * @return List of author_id
     */
    List<Long> createAuthorIdList(String names);

    /**
     * Insert book authors to database
     *
     * @param bookId It's book id value in database
     * @param listAuthorId List author id
     */
    void createBookAuthors(Long bookId, List<Long> listAuthorId);

    /**
     * Update book and book authors
     *
     * @param book an instances of Book filled with data
     * @param authors String with all names authors
     * @return instances of Book
     */
    Book updateBook(Book book, String authors);

    /**
     * Takes book data from database by id
     *
     * @param id It's book id value in database
     * @return an instances of Book filled with data from the database
     */
    Book getBook(Long id);

}
