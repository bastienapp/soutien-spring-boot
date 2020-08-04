package com.wildcodechool.moviefav.controller;

import com.wildcodechool.moviefav.entity.Movie;
import com.wildcodechool.moviefav.entity.User;
import com.wildcodechool.moviefav.repository.MovieRepository;
import com.wildcodechool.moviefav.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/movies")
    public List<Movie> getAll() {

        return movieRepository.findAll();
    }

    @GetMapping("/movies/{id}")
    public Optional<Movie> getOne(@PathVariable Long id) {

        return movieRepository.findById(id);
    }

    @PostMapping("/users/{username}/movies/{id}")
    public User addToFavourites(
            @PathVariable String username,
            @PathVariable(name = "id") Long movieId
    ) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        if (optionalUser.isPresent() && optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            User user = optionalUser.get();
            user.getFavourites().add(movie);
            return userRepository.save(user);
        }
        return null;
    }
}
