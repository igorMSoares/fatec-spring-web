package com.adsfatec.lime.daos;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.adsfatec.lime.models.Book;

import jakarta.annotation.PostConstruct;

@Repository
public class BookDAO {
    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public List<Book> findAll() {
        String sql = "SELECT * FROM book ORDER BY updated_at DESC, created_at DESC;";

        return jdbc.query(sql, (rs, i) -> {
            return new Movie(
                    rs.getString("id"),
                    rs.getString("title"),
                    rs.getInt("year"),
                    rs.getString("author"),
                    rs.getInt("pagesCount"),
                    rs.getString("publisher"),
                    rs.getString("isbn"),
                    rs.getString("synopsis"));
        });
    }

    public Book findById(String id) {
        String sql = "SELECT * FROM book WHERE id = ?;";

        return jdbc.queryForObject(sql, (rs, i) -> {
            return new Movie(
                    rs.getString("id"),
                    rs.getString("title"),
                    rs.getInt("year"),
                    rs.getString("author"),
                    rs.getInt("pagesCount"),
                    rs.getString("publisher"),
                    rs.getString("isbn"),
                    rs.getString("synopsis"));
        }, id);
    }

    public List<Book> findAllPartialData() {
        String sql = """
                SELECT id, title, year, author
                    FROM book
                    ORDER BY updated_at DESC, created_at DESC;
                """;

        return jdbc.query(sql, (rs, i) -> {
            return new Book(
                    rs.getString("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getInt("year"));
        });
    }

    public void insert(Book m) {
        String sql = """
                INSERT INTO book(
                    id,
                    title,
                    year,
                    author,
                    pagesCount,
                    publisher,
                    isbn,
                    synopsis,
                    created_at,
                    updated_at
                ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        Instant now = Instant.now();

        jdbc.update(sql,
                m.getId(),
                m.getTitle(),
                m.getYear(),
                m.getAuthor(),
                m.getPagesCount(),
                m.getPublisher(),
                m.getIsbn(),
                m.getSynopsis(),
                Timestamp.from(now),
                Timestamp.from(now));
    }

    public void updateById(String id, Book b) {
        m.setUpdatedAt(Instant.now());

        String sql = """
                UPDATE book SET
                     title = ?,
                     year = ?,
                     author = ?,
                     pagesCount = ?,
                     publisher = ?,
                     isbn = ?,
                     synopsis = ?,
                     updated_at = ?
                 WHERE id = ?
                """;

        jdbc.update(sql,
                m.getTitle(),
                m.getYear(),
                m.getAuthor(),
                m.getPagesCount(),
                m.getPublisher(),
                m.getIsbn(),
                m.getSynopsis(),
                Timestamp.from(m.getUpdatedAt()),
                id);
    }

    public void deleteById(String id) {
        String sql = "DELETE FROM book WHERE id = ?";

        jdbc.update(sql, id);
    }
}
