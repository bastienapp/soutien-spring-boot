package com.wildcodechool.moviefav.controller;

import com.wildcodechool.moviefav.entity.Movie;
import com.wildcodechool.moviefav.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/edit-movie/{id}")
    public String getEditMovie(@PathVariable Long id,
                               Model out) {

        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isEmpty()) {
            // TODO 404
        } else {
            Movie movie = optionalMovie.get();
            out.addAttribute("movie", movie);
        }

        return "movie-create";
    }

    @GetMapping("/create-movie")
    public String getCreateMovie(Model out) {
        out.addAttribute("movie", new Movie());
        return "movie-create";
    }

    @PostMapping("/create-movie")
    public String postCreateMovie(
            @ModelAttribute Movie movie
    ) {
        movieRepository.save(movie);
        return "redirect:/edit-movie/" + movie.getId();
    }

    @GetMapping("/show-movie/{id}")
    public String getShowMovie(@PathVariable Long id,
                               Model out) {

        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isEmpty()) {
            // TODO 404
        } else {
            Movie item = optionalMovie.get();
            out.addAttribute("movie", item);
        }

        return "movie-show";
    }
}
