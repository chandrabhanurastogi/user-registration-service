package com.gamesys.userregistrationservice.controller;

import com.gamesys.userregistrationservice.entity.User;
import com.gamesys.userregistrationservice.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserRegistrationController {
    @Autowired UserRegistrationService userRegistrationService;
    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Valid User user){

        System.out.println(user.toString());
        userRegistrationService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registration Success");
    }
}
