package com.adsfatec.lime.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adsfatec.lime.daos.BookDAO;
import com.adsfatec.lime.models.Book;

@Service
public class BookService {

    @Autowired
    BookDAO dao;

    public List<Book> listAll() {
        return dao.findAll();
    }

    public List<Book> getTableData() {
        return dao.findAllPartialData();
    }

    public Book getDetails(String id) {
        return dao.findById(id);
    }

    public void insert(Book b) {
        if (b.getTitle().length() == 0) {
            return;
        }

        //isso do Imdb não cabe para livros, certo?
       // if (m.getImdb().length() > 10) {
       //     m.setImdb("");
       // }

        m.setId(this.generateUUID());
        dao.insert(m);
    }

    public void update(String id, Book b) {
       // if (m.getImdb().length() > 10) {
       //     m.setImdb("");
       // }

        dao.updateById(id, b);
    }

    public void delete(String id) {
        dao.deleteById(id);
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }

}
