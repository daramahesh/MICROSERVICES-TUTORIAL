package org.rating.service.controllers;

import org.rating.service.entities.Rating;
import org.rating.service.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<Rating> create(@RequestBody Rating rating) {
        Rating rating1 = this.ratingService.create(rating);
        return  ResponseEntity.status(HttpStatus.CREATED).body(rating1);
    }

    @GetMapping
    public ResponseEntity<List<Rating>> get() {
        List<Rating> ratings = this.ratingService.getRatings();
        return ResponseEntity.status(HttpStatus.FOUND).body(ratings);
    }

    @GetMapping("rating/{userId}")
    public ResponseEntity<List<Rating>> getByUserId(@PathVariable("userId") String userId) {
        List<Rating> ratings = this.ratingService.getRatingsByUserId(userId);
        return ResponseEntity.status(HttpStatus.FOUND).body(ratings);
    }

    @GetMapping("hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getByHotelId(@PathVariable("hotelId") String hotelId) {
        List<Rating> ratings = this.ratingService.getRatingsByHotelId(hotelId);
        return ResponseEntity.status(HttpStatus.FOUND).body(ratings);
    }
}
