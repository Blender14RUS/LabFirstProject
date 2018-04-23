package com.epam.lab.library.dao;

import com.epam.lab.library.domain.Book;

import java.util.List;

public interface BookDao {

    /**
     * Add new book in database
     *
     * @param book - book is instance filled with data (not include id) must be recorded in the database
     * @return Book id value in database
     */
    Long addBook(Book book);

    /**
     * Creates List of all books from database
     *
     * @param searchingTitle
     * @param showNotAvailable
     * @param sortType
     * @return List of Book instances filled with data from the database
     */
    List<Book> getBooks(String searchingTitle, boolean showNotAvailable, String sortType);

    /**
     * By author name takes author id from database
     *
     * @param name - author name
     * @return Author id value in database
     */
    Long getAuthor(String name);

    /**
     * Insert book authors to database
     *
     * @param bookId   - It's book id value in database
     * @param authorId - It's author id value in database
     * @return true if data inserted, false if not
     */
    boolean createBookAuthors(Long bookId, Long authorId);

    /**
     * Takes book data from database by id
     *
     * @param id It's book id value in database
     * @return an instances of Book filled with data from the database
     */
    Book getBook(Long id);

    /**
     * Checks book with current params in database
     *
     * @param title Book title
     * @param year  Book year
     * @return true if book don't exist in database
     */
    boolean checkBookInDB(String title, int year);

    /**
     * Update book in database
     *
     * @param book - book is instance filled with data must be update in the database
     * @return true if book updated
     */
    boolean updateBook(Book book);

    /**
     * Checks author with name in database
     *
     * @param name Author name
     * @return true author exist
     */
    boolean authorExist(String name);

    /**
     * Add new author in database
     *
     * @param name - Author name
     * @return Author id value in database
     */
    Long addAuthor(String name);

    /**
     * Method removes book authors from database
     *
     * @param bookId It's id of book which must be deleted from the database
     * @return true if deleted successful
     */
    boolean deleteBookAuthors(Long bookId);

    /**
     * Decrement the book count available
     *
     * @param book is instance filled with data
     * @return true if decrement successful
     */
    boolean requestBook(Book book);

    /**
     * Increment the book count available
     *
     * @param id It's id of book
     * @return true if increment successful
     */
    boolean incrementBookCountAvailable(Long id);

    /**
     * Method removes book from database
     *
     * @param id id of book
     * @return true if delete executed
     */
    boolean deleteBook(Long id);

}
