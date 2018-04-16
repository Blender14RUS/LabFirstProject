/**
 * Class Book with properties <b>id</b>, <b>title</b>, <b>year</b> and <b>available</b>.
 *
 * @autor Igor Ukrainets
 * @version 0.1
 */
package com.epam.lab.library.domain;

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

    private List<Author> authors;

    /**
     * Creates a new Book with the given param.
     *
     * @param id        {@link Book#id}
     * @param title     {@link Book#title}
     * @param year      {@link Book#year}
     * @param available {@link Book#available}
     * @return int This returns sum of numA and numB.
     */
    public Book(Long id, String title, int year, int available) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.available = available;
    }
    public Book(){}

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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return Objects.equals(id, book.id) &&
                Objects.equals(title, book.title) && Objects.equals(year, book.year);
    }

    @Override
    public String toString() {
        return String.format("Book[id_book=%s, title=%s, year=%s, count available=%s]",
                id, title, year, available);
    }

}