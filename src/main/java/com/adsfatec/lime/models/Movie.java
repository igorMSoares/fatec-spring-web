package com.adsfatec.lime.models;

import java.time.Instant;

public class Movie {
    private String id, title, genre, synopsis, director, casting, imdb;
    private Integer year;
    private Instant createdAt, updatedAt;

    // Formulário de cadastro
    public Movie() {
    }

    // Atualização e Exibição
    public Movie(
            String id,
            String title,
            Integer year,
            String director,
            String casting,
            String genre,
            String synopsis,
            String imdb) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.director = director;
        this.casting = casting;
        this.genre = genre;
        this.synopsis = synopsis;
        this.imdb = imdb;
    }

    // Inserção
    public Movie(
            String title,
            Integer year,
            String director,
            String casting,
            String genre,
            String synopsis,
            String imdb) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.casting = casting;
        this.genre = genre;
        this.synopsis = synopsis;
        this.imdb = imdb;
    }

    // Listagem parcial
    public Movie(String id, String title, String genre, Integer year) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public String getCasting() {
        return casting;
    }

    public String getGenre() {
        return genre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getImdb() {
        return imdb;
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

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setCasting(String casting) {
        this.casting = casting;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
