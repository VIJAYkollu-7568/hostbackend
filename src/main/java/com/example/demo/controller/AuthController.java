package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {
    "http://localhost:5173",
    "https://producthost.vercel.app",
    "https://producthost-git-main-vijaykollu-7568s-projects.vercel.app",
    "https://producthost-dk2kksf6k-vijaykollu-7568s-projects.vercel.app"
})
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public Map<String, String> signup(@RequestBody User user) {
        String message = userService.signup(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }

    @PostMapping("/login")
    public Object login(@RequestBody User user) {
        User loggedInUser = userService.login(user.getEmail(), user.getPassword());

        if (loggedInUser == null) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Invalid email or password");
            return error;
        }

        return loggedInUser;
    }
}