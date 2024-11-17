package com.adsfatec.lime.models;

import java.time.Instant;

public class Book {
    private String id, title, author, publisher, isbn, synopsis;
    private Integer year, pagesCount;
    private Instant createdAt, updatedAt;

    // Formulário de cadastro
    public Book() {
    }

    // Atualização
    public Book(String id, String title, String author, String publisher, Integer year, String isbn, Integer pagesCount,
            String synopsis) {
        this.id = id;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.isbn = isbn;
        this.pagesCount = pagesCount;
        this.synopsis = synopsis;
    }

    // Inserção
    public Book(String title, String author, String publisher, Integer year, String isbn, Integer pagesCount,
            String synopsis) {
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.isbn = isbn;
        this.pagesCount = pagesCount;
        this.synopsis = synopsis;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public Integer getYear() {
        return year;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getPagesCount() {
        return pagesCount;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPagesCount(Integer pagesCount) {
        this.pagesCount = pagesCount;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String plot) {
        this.synopsis = plot;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
