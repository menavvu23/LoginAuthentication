package com.example.loginauthentication.controller;

import com.example.loginauthentication.UserDTO;
import com.example.loginauthentication.model.User;
import com.example.loginauthentication.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody UserDTO userdto) throws Exception {
        User user = new User();
        user.setUsername(userdto.getUsername());
        user.setPassword(user.getPassword());
        user.setPhoneNumber(userdto.getPhoneNumber());
        return this.userService.addUser(user);
    }


}

