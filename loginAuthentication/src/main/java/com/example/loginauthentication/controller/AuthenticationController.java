package com.example.loginauthentication.controller;

import com.example.loginauthentication.UserDTO;
import com.example.loginauthentication.UserDetailServiceImpl;
import com.example.loginauthentication.model.User;
import com.example.loginauthentication.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UserDetailServiceImpl userDetailsService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncode;

    public AuthenticationController(AuthenticationManager authenticationManager, UserDetailServiceImpl userDetailsService, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, PasswordEncoder passwordEncode) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.passwordEncode = passwordEncode;
    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody UserDTO userRequest) throws Exception {
        try {
            if (!passwordEncode.matches(userRequest.getPassword(), userDetailsService.loadUserByUsername(userRequest.getUsername()).getPassword())) {
                throw new UsernameNotFoundException("Username or password is incorrect");
            }
            authenticate(userRequest.getUsername(), userRequest.getPassword());
        } catch (UsernameNotFoundException e) {
            System.out.println("User not found");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user = userRepository.findByUsername(userRequest.getUsername());
        UserResponse userResponse = new UserResponse(user.getUsername(),user.getId(),user.isActive());
        return new ResponseEntity(userResponse, HttpStatus.OK);

    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("User disabled");
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Credentials" + e.getMessage());
        } catch (Exception e) {
            throw new Exception();
        }
    }

}

class UserResponse {

    private String username;
    private Long id;
    private boolean isActive;

    public UserResponse() {
    }

    public UserResponse(String username, Long id, boolean isActive) {
        this.username = username;
        this.id = id;
        this.isActive = isActive;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}