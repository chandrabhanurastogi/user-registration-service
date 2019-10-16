package com.gamesys.userregistrationservice.service;

import com.gamesys.userregistrationservice.entity.User;
import com.gamesys.userregistrationservice.repository.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {
    @Autowired private
    UserRegistrationRepository userRegistrationRepository;
    public User registerUser(User user){
        userRegistrationRepository.save(user);
        return user;
    }
}
