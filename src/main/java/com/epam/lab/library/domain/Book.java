package com.epam.lab.library.domain;

import java.util.Objects;

/**
 *
 * @autor Igor Ukrainets
 */
public class Book {

    private Long id;
    private String title;
    private int year;
    private int available;

    /**
     * Constructor directly set all fields.
     * @param id
     * @param title
     * @param year
     * @param available
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
     * @param title
     * @param year
     * @param available
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
