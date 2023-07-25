package com.movie.controller;

import com.movie.documents.Movie;
import com.movie.service.MovieService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")
@CrossOrigin("*")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @GetMapping
    public ResponseEntity<List<Movie>> allMovies() {
        List<Movie> movies = movieService.allMovies();
        return ResponseEntity.ok(movies);
    }
    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<Movie>> getSingleMovie(@PathVariable("imdbId") String imdbId) {
        Optional<Movie> singleMovie = movieService.getSingleMovie(imdbId);
        return ResponseEntity.ok(singleMovie);
    }

}
