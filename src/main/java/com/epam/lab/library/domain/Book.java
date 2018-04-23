package com.epam.lab.library.domain;

import javax.validation.constraints.*;
import java.util.List;
import java.util.Objects;

public class Book {
    /**
     * Id of this book
     */
    private Long id;

    /**
     * Full book title without name of author
     */
    private String title;

    /**
     * Publication Date
     */
    private int year;

    /**
     * Count available copy this book
     */
    private int available;

    /**
     * Contains all authors of a book
     */
    private List<Author> authors;

    public Book() {
    }

    /**
     * Creates a new Book with the given param.
     *
     * @param id        {@link Book#id}
     * @param title     {@link Book#title}
     * @param year      {@link Book#year}
     * @param available {@link Book#available}
     */
    public Book(Long id, String title, int year, int available) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.available = available;
    }

    /**
     * Constructor without id.
     * Useful for create book instance to the database.
     *
     * @param title     {@link Book#title}
     * @param year      {@link Book#year}
     * @param available {@link Book#available}
     */
    public Book(String title, int year, int available) {
        this.title = title;
        this.year = year;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, year);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(title, book.title) && Objects.equals(year, book.year);
    }

    @Override
    public String toString() {
        return String.format("Book[id_book=%s, title=%s, year=%s, count available=%s]",
                id, title, year, available);
    }

}
