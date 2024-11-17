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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adsfatec.lime.models.Movie;
import com.adsfatec.lime.services.MovieService;

@Controller
@RequestMapping("/movies")
public class MoviesController {
    private final MovieService service;

    @Autowired
    public MoviesController(MovieService service) {
        this.service = service;
    }

    @GetMapping
    public String getMovies(Model model) {
        List<Movie> movies = service.getTableData();
        model.addAttribute("movies", movies);

        return "movies/movies :: list";
    }

    @GetMapping("/table")
    public String getMoviesTable(Model model) {
        List<Movie> movies = service.getTableData();
        model.addAttribute("movies", movies);

        return "movies/movies :: table";
    }

    @GetMapping("/new")
    public String getMovieForm(Model model) {
        Movie m = new Movie();
        model.addAttribute("movie", m);

        return "movies/form :: form";
    }

    @GetMapping("/{movieId}/update")
    public String getUpdateMovieForm(@PathVariable String movieId, Model model) {
        Movie m = service.getDetails(movieId);
        model.addAttribute("movie", m);

        return "movies/form :: form";
    }

    @PostMapping
    public String insertMovie(@ModelAttribute Movie movie, Model model) {
        service.insert(movie);

        List<Movie> movies = service.getTableData();
        model.addAttribute("movies", movies);

        return "movies/movies :: table";
    }

    @PutMapping("/{movieId}")
    public String updateMovie(@PathVariable String movieId, @ModelAttribute Movie movie, Model model) {
        service.update(movieId, movie);

        List<Movie> movies = service.getTableData();
        model.addAttribute("movies", movies);

        return "movies/movies :: table";
    }

    @DeleteMapping("/{movieId}")
    public String deleteMovie(@PathVariable String movieId, Model model) {
        service.delete(movieId);

        List<Movie> movies = service.getTableData();
        model.addAttribute("movies", movies);

        return "movies/movies :: table";
    }

    @GetMapping("/{movieId}")
    public String getMovie(@PathVariable String movieId, Model model) {
        Movie movie = service.getDetails(movieId);

        model.addAttribute("movie", movie);

        return "movies/movies :: details";
    }

    @GetMapping("/{movieId}/comments")
    public String getComments(@PathVariable String movieId, Model model) {
        model.addAttribute("movieId", movieId);

        return "movies/movies :: comments";
    }
}
