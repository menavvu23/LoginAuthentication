package com.example.loginauthentication.controller;

import com.example.loginauthentication.model.User;
import com.example.loginauthentication.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {
        return this.userService.addUser(user);
    }


}

