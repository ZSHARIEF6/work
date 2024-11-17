package com.bank.bankingApp.controller;

import com.bank.bankingApp.model.User;
import com.bank.bankingApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
//@RequestMapping("/api/users")
//@CrossOrigin(origins = "http://localhost:3000") // Adjust if frontend is on a different URL
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        Optional<User> foundUser = userService.loginUser(user.getEmail(), user.getPassword());
        if (foundUser.isPresent()) {
            return ResponseEntity.ok(foundUser.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}
