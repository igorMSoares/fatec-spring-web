package com.adsfatec.lime.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adsfatec.lime.daos.MovieDAO;
import com.adsfatec.lime.models.Movie;

@Service
public class MovieService {

    @Autowired
    MovieDAO dao;

    public List<Movie> listAll() {
        return dao.findAll();
    }

    public List<Movie> getTableData() {
        return dao.findAllPartialData();
    }

    public Movie getDetails(String id) {
        return dao.findById(id);
    }

    public void insert(Movie m) {
        if (m.getTitle().length() == 0) {
            return;
        }

        if (m.getImdb().length() > 10) {
            m.setImdb("");
        }

        m.setId(this.generateUUID());
        dao.insert(m);
    }

    public void update(String id, Movie m) {
        if (m.getImdb().length() > 10) {
            m.setImdb("");
        }

        dao.updateById(id, m);
    }

    public void delete(String id) {
        dao.deleteById(id);
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }

}
