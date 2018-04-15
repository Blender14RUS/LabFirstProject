package com.epam.lab.library.domain;

import java.util.Objects;

public class Order {
    private Long id;
    private Long userId;
    private Long bookId;
    private String location;
    private String status;

    public Order() { }

    public Order(Long id, Long userId, Long bookId, String location, String status) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.location = location;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id_user) {
        this.userId = id_user;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(userId, order.userId) &&
                Objects.equals(bookId, order.bookId) &&
                Objects.equals(location, order.location) &&
                Objects.equals(status, order.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, bookId, location, status);
    }
}
