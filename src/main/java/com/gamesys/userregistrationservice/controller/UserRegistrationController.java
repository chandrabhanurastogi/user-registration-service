package com.gamesys.userregistrationservice.controller;

import com.gamesys.userregistrationservice.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistrationController {

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user){
        System.out.println(user.toString());
        //todo validation
        return ResponseEntity.status(HttpStatus.OK).body("Registration Success");
    }
}
