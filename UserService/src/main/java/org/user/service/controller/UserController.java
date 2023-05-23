package org.user.service.controller;

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

    //getUser
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") String userId) {
        User user = this.userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.FOUND).body(user);
    }
}
