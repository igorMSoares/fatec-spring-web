package com.adsfatec.lime.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adsfatec.lime.daos.CommentDAO;
import com.adsfatec.lime.models.Comment;

@Service
public class CommentService {

    @Autowired
    CommentDAO dao;

    public void insert(Comment c) {
        if (c.getMediaId().equals("") || c.getMediaType() == null) {
            return;
        }

        c.setId(this.generateUUID());
        dao.insert(c);
    }

    public List<Comment> listAllByMovieId(String movieId) {
        return dao.findAllByMovieId(movieId);
    }

    public void delete(String id) {
        dao.deleteById(id);
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
