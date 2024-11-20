package com.adsfatec.lime.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adsfatec.lime.models.Comment;
import com.adsfatec.lime.models.enums.MediaType;
import com.adsfatec.lime.services.CommentService;

@Controller
@RequestMapping("/comments")
public class CommentsController {
    private final CommentService service;

    @Autowired
    public CommentsController(CommentService service) {
        this.service = service;
    }

    @GetMapping("/{mediaType}/{mediaId}")
    public String getComments(
            @PathVariable MediaType mediaType,
            @PathVariable String mediaId,
            Model model) {

        if (mediaType == MediaType.MOVIE) {
            List<Comment> comments = service.listAllByMovieId(mediaId);
            model.addAttribute("comments", comments);
        } else if (mediaType == MediaType.BOOK) {
            List<Comment> comments = service.listAllByBookId(mediaId);
            model.addAttribute("comments", comments);
        }

        model.addAttribute("mediaId", mediaId);

        return "comments/comments :: list";
    }

    @PostMapping("/{mediaType}/{mediaId}")
    public String insertComment(
            @PathVariable MediaType mediaType,
            @PathVariable String mediaId,
            @ModelAttribute Comment comment, Model model) {
        comment.setMediaType(mediaType);
        comment.setMediaId(mediaId);

        service.insert(comment);

        if (comment.getMediaType() == MediaType.MOVIE) {
            List<Comment> comments = service.listAllByMovieId(mediaId);
            model.addAttribute("comments", comments);
        } else if (comment.getMediaType() == MediaType.BOOK) {
            List<Comment> comments = service.listAllByBookId(mediaId);
            model.addAttribute("comments", comments);
        }

        return "comments/comments :: list";
    }

    @DeleteMapping("/{commentId}/{mediaType}/{mediaId}")
    public String deleteComment(
            @PathVariable String commentId,
            @PathVariable MediaType mediaType,
            @PathVariable String mediaId,
            Model model) {
        service.delete(commentId);

        if (mediaType == MediaType.MOVIE) {
            List<Comment> comments = service.listAllByMovieId(mediaId);
            model.addAttribute("comments", comments);
        } else if (mediaType == MediaType.BOOK) {
            List<Comment> comments = service.listAllByBookId(mediaId);
            model.addAttribute("comments", comments);
        }

        return "comments/comments :: list";
    }
}
