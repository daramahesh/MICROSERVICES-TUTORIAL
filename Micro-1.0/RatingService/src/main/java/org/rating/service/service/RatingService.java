package org.rating.service.service;

import org.rating.service.entities.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating create(Rating rating);

    //getAllRatings
    List<Rating> getRatings();

    //getAllRatingsByUserId
    List<Rating> getRatingsByUserId(String userId);

    //getAllRatingsByHotelId
    List<Rating> getRatingsByHotelId(String hotelId);

}
