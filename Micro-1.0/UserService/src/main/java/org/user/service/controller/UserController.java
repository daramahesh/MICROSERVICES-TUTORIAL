package org.user.service.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.user.service.entities.User;
import org.user.service.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    //create
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = this.userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //getAll
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> user = this.userService.getAll();
        return ResponseEntity.status(HttpStatus.FOUND).body(user);
    }

    int retryCount = 1;
    //getUser
    @GetMapping("/{userId}")
    //@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUser(@PathVariable("userId") String userId) {
        System.out.println("no.of times retry:" + retryCount);
        retryCount++;
        User user = this.userService.getUser(userId);


        return ResponseEntity.status(HttpStatus.FOUND).body(user);
    }

    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
        User user = User.builder()
                .userId("548746")
                .name("dummy")
                .email("dummy@gmail.com")
                .about("this user id created because one of the services are down")
                .build();
        return ResponseEntity.status(HttpStatus.FOUND).body(user);
    }
}
