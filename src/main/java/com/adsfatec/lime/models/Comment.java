package com.adsfatec.lime.models;

import java.time.Instant;

import com.adsfatec.lime.models.enums.MediaType;

public class Comment {
    private String id, title, content, mediaId;
    private Integer rating;
    private MediaType mediaType;
    private Instant createdAt;

    public Comment() {
    }

    public Comment(
            String id,
            String title,
            String content,
            Integer rating,
            Instant createdAt,
            String mediaId,
            MediaType mediaType) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.rating = rating;
        this.createdAt = createdAt;
        this.mediaId = mediaId;
        this.mediaType = mediaType;
    }

    public Comment(String mediaId, MediaType mediaType) {
        this.mediaId = mediaId;
        this.mediaType = mediaType;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Integer getRating() {
        return rating;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getMediaId() {
        return mediaId;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }
}
