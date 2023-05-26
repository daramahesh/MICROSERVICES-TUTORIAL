package org.rating.service.impl;

import org.rating.service.entities.Rating;
import org.rating.service.repositories.RatingRepository;
import org.rating.service.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating create(Rating rating) {
        String randomId = UUID.randomUUID().toString();
        rating.setRatingId(randomId);
        return this.ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        return this.ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingsByUserId(String userId) {
        return this.ratingRepository.findByUserId(userId);
    }
    @Override
    public List<Rating> getRatingsByHotelId(String hotelId) {
        return this.ratingRepository.findByHotelId(hotelId);
    }
}
