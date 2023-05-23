package org.user.service.services;

import org.user.service.entities.User;

import java.util.List;

public interface UserService {

    //create
    User saveUser(User user);

    //getAll
    List<User> getAll();

    //getUser
    User getUser(String userId);


}
