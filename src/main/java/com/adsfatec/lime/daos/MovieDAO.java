package com.adsfatec.lime.daos;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.adsfatec.lime.models.Movie;

import jakarta.annotation.PostConstruct;

@Repository
public class MovieDAO {
    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public List<Movie> findAll() {
        String sql = "SELECT * FROM movie ORDER BY updated_at DESC, created_at DESC;";

        return jdbc.query(sql, (rs, rowNum) -> {
            Movie movie = new Movie();

            movie.setId(rs.getString("id"));
            movie.setTitle(rs.getString("title"));
            movie.setGenre(rs.getString("genre"));
            movie.setSynopsis(rs.getString("synopsis"));
            movie.setDirector(rs.getString("director"));
            movie.setCasting(rs.getString("casting"));
            movie.setImdb(rs.getString("imdb"));
            movie.setYear(rs.getInt("year"));

            return movie;
        });
    }

    public Movie findById(String id) {
        String sql = "SELECT * FROM movie WHERE id = ?;";

        return jdbc.queryForObject(sql, (rs, rowNum) -> {
            Movie movie = new Movie();

            movie.setId(rs.getString("id"));
            movie.setTitle(rs.getString("title"));
            movie.setGenre(rs.getString("genre"));
            movie.setSynopsis(rs.getString("synopsis"));
            movie.setDirector(rs.getString("director"));
            movie.setCasting(rs.getString("casting"));
            movie.setImdb(rs.getString("imdb"));
            movie.setYear(rs.getInt("year"));

            return movie;
        }, id);
    }

    public List<Movie> findAllPartialData() {
        String sql = "SELECT id, title, year, genre FROM movie ORDER BY updated_at DESC, created_at DESC;";

        return jdbc.query(sql, (rs, rowNum) -> {
            Movie movie = new Movie();

            movie.setId(rs.getString("id"));
            movie.setTitle(rs.getString("title"));
            movie.setGenre(rs.getString("genre"));
            movie.setYear(rs.getInt("year"));

            return movie;
        });
    }

    public void insert(Movie m) {
        m.setCreatedAt(Instant.now());
        m.setUpdatedAt(Instant.now());

        String sql = "INSERT INTO movie(id,title, genre, synopsis, director, imdb, year, casting, created_at, updated_at) VALUES(?,?,?,?,?,?,?,?,?,?)";

        jdbc.update(sql, m.getId(), m.getTitle(), m.getGenre(), m.getSynopsis(), m.getDirector(), m.getImdb(),
                m.getYear(),
                m.getCasting(),
                Timestamp.from(m.getCreatedAt()),
                Timestamp.from(m.getUpdatedAt()));
    }

    public void updateById(String id, Movie m) {
        m.setUpdatedAt(Instant.now());

        String sql = "UPDATE movie SET title = ?, genre = ?, synopsis = ?, director = ?, imdb = ?, year = ?, casting = ?, updated_at = ? WHERE id = ?";

        jdbc.update(sql, m.getTitle(), m.getGenre(), m.getSynopsis(), m.getDirector(), m.getImdb(), m.getYear(),
                m.getCasting(), Timestamp.from(m.getUpdatedAt()), id);
    }

    public void deleteById(String id) {
        String sql = "DELETE FROM movie WHERE id = ?";

        jdbc.update(sql, id);
    }
}
