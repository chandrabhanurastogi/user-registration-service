package com.gamesys.userregistrationservice.service;

import com.gamesys.userregistrationservice.entity.User;
import com.gamesys.userregistrationservice.repository.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    public User registerUser(User user) {

        if (usernameUnique(user.getUsername())) {
            user = userRegistrationRepository.save(user);

        } else {
            System.out.println("----------------- USER NAME ALREADY EXIST----------------");
//            throw new UserNameNonUniqueException(){
        }
        return user;
    }

    private boolean usernameUnique(String username) {
        return !userRegistrationRepository.findByUsername(username).isPresent();
    }
}

