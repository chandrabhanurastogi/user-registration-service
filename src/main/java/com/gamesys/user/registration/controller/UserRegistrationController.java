package com.gamesys.user.registration.controller;

import com.gamesys.user.registration.model.ui.UserForm;
import com.gamesys.user.registration.service.UserRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
class UserRegistrationController {


    private final UserRegistrationService userRegistrationService;

    private UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Valid UserForm userForm) {
        log.info("Registering new user {}", userForm.getUsername());
        userRegistrationService.registerUser(userForm);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registration Success");
    }
}
