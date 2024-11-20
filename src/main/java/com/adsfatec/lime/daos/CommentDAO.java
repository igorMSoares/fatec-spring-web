package com.adsfatec.lime.daos;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.adsfatec.lime.models.Comment;
import com.adsfatec.lime.models.enums.MediaType;

import jakarta.annotation.PostConstruct;

@Repository
public class CommentDAO {
    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public List<Comment> findAllByBookId(String bookId) {
        String sql = """
                SELECT
                    c.id,
                    c.title,
                    c.content,
                    c.rating,
                    c.created_at
                FROM comment c
                JOIN book_comment bc ON c.id = bc.comment_id
                WHERE bc.book_id = ?
                ORDER BY c.created_at DESC;
                """;

        return jdbc.query(sql, (rs, i) -> {
            return new Comment(
                    rs.getString("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getInt("rating"),
                    rs.getTimestamp("created_at").toInstant(),
                    bookId,
                    MediaType.BOOK);
        }, bookId);
    }

    public List<Comment> findAllByMovieId(String movieId) {
        String sql = """
                SELECT
                    c.id,
                    c.title,
                    c.content,
                    c.rating,
                    c.created_at
                FROM comment c
                JOIN movie_comment mc ON c.id = mc.comment_id
                WHERE mc.movie_id = ?
                ORDER BY c.created_at DESC;
                """;

        return jdbc.query(sql, (rs, i) -> {
            return new Comment(
                    rs.getString("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getInt("rating"),
                    rs.getTimestamp("created_at").toInstant(),
                    movieId,
                    MediaType.MOVIE);
        }, movieId);
    }

    public void insert(Comment c) {
        switch (c.getMediaType()) {
            case MOVIE:
                this.insertMovieComment(c);
                break;
            case BOOK:
                this.insertBookComment(c);
                break;
            default:
                return;
        }
    }

    private void insertMovieComment(Comment c) {
        c.setCreatedAt(Instant.now());

        TransactionTemplate transaction = new TransactionTemplate(transactionManager);

        transaction.execute(status -> {
            try {
                String sql = """
                        INSERT INTO comment(
                            id,
                            title,
                            content,
                            rating,
                            created_at
                        ) VALUES (?, ?, ?, ?, ?)
                        """;

                jdbc.update(sql,
                        c.getId(),
                        c.getTitle(),
                        c.getContent(),
                        c.getRating(),
                        Timestamp.from(c.getCreatedAt()));

                sql = """
                        INSERT INTO movie_comment(
                            id,
                            comment_id,
                            movie_id
                        ) VALUES (?, ?, ?)
                        """;

                jdbc.update(sql,
                        UUID.randomUUID().toString(),
                        c.getId(),
                        c.getMediaId());

                return null;
            } catch (Exception e) {
                status.setRollbackOnly();
                throw e;
            }
        });
    }

    private void insertBookComment(Comment c) {
        c.setCreatedAt(Instant.now());

        TransactionTemplate transaction = new TransactionTemplate(transactionManager);

        transaction.execute(status -> {
            try {
                String sql = """
                        INSERT INTO comment(
                            id,
                            title,
                            content,
                            rating,
                            created_at
                        ) VALUES (?, ?, ?, ?, ?)
                        """;

                jdbc.update(sql,
                        c.getId(),
                        c.getTitle(),
                        c.getContent(),
                        c.getRating(),
                        Timestamp.from(c.getCreatedAt()));

                sql = """
                        INSERT INTO book_comment(
                            id,
                            comment_id,
                            book_id
                        ) VALUES (?, ?, ?)
                        """;

                jdbc.update(sql,
                        UUID.randomUUID().toString(),
                        c.getId(),
                        c.getMediaId());

                return null;
            } catch (Exception e) {
                status.setRollbackOnly();
                throw e;
            }
        });
    }

    public void deleteById(String id) {
        String sql = "DELETE FROM comment WHERE id = ?";

        jdbc.update(sql, id);
    }
}
