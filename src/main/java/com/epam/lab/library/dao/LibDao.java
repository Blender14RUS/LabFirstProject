package com.epam.lab.library.dao;

import com.epam.lab.library.domain.*;

import java.util.List;

public interface LibDao {

    /**
     * Takes user data from database by user id
     *
     * @param id It's user id value in database
     * @return an instances of User filled with data from the database
     */
    User getUser(Long id);

    /**
     * Creates List of all users from database
     *
     * @return List of User instances filled with data from the database
     */
    List<User> getAllUsers();

    /**
     * Creates new user through web form
     *
     * @param user - an instances of User filled with data must be recorded in the database
     * @return Count of changes rows in database
     */
    int createUser(User user);

    /**
     * Method removes user from database
     *
     * @param id It's id of user which must be deleted from the database
     */
    void deleteUserById(Long id);

    /**
     * Changes user access level (READER / LIBRARIAN)
     *
     * @param id It's id of user which access level must be changed
     * @param accessLevel current value of user access level
     */
    void updateUserAccessLevel(Long id, AccessLevel accessLevel);
    /**
     * Creates new book
     *
     * @param book book to be created
     * @return book with id
     */
    Book addBook(Book book);

    List<Order> getAllOrderByStatus(Status status);

    List<Book> getAllBooks();

    void setBookStatus(Status status, Long id);
}