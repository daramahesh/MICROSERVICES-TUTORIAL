package org.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.user.service.entities.Hotel;
import org.user.service.entities.Rating;
import org.user.service.entities.User;
import org.user.service.exception.ResourceNotFoundException;
import org.user.service.external.services.HotelService;
import org.user.service.repositories.UserRepository;
import org.user.service.services.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /*@Autowired
    private RestTemplate restTemplate;*/

    @Autowired
    private HotelService hotelService;

    @Autowired
    private WebClient webClient;
    @Override
    public User saveUser(User user) {

        //generating unique userId
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);

        return this.userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        //getting single user

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));

        //getting ratings given by particular user
        //http://localhost:1432/ratings/get/cd69a47c-dae6-40c8-96c7-ec671b460d03

        Rating[] ratingsOfUser = webClient.get().uri("http://RATING-SERVICE/ratings/rating/" + userId).retrieve().bodyToMono(Rating[].class).block();

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();


        List<Rating> ratingList = ratings.stream().map(rating -> {
            Hotel hotel = webClient.get()
                    .uri("http://HOTEL-SERVICE/hotels/" + rating.getHotelId())
                    .retrieve()
                    .bodyToMono(Hotel.class)
                    .block();
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;

    }
}
