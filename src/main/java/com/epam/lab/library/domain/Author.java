package com.epam.lab.library.domain;

import java.util.Objects;

public class Author {
    private Long id;
    private String name;

    public Author() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Author aut = (Author) obj;
        return Objects.equals(id, aut.id) &&
                Objects.equals(name, aut.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
