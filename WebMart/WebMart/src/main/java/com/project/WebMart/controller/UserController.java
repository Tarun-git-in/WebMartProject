package com.project.WebMart.controller;

import com.project.WebMart.model.Users;
import com.project.WebMart.service.UserService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/webmart/admin")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Users register(@RequestBody Users user){

        return userService.register(user);

    }

}
