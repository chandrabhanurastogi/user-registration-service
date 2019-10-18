package com.gamesys.userregistrationservice.service;

import com.gamesys.userregistrationservice.dto.UserDto;
import com.gamesys.userregistrationservice.entity.User;
import com.gamesys.userregistrationservice.exception.custom.UserNameNonUniqueException;
import com.gamesys.userregistrationservice.repository.UserRegistrationRepository;
import com.gamesys.userregistrationservice.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    public User registerUser(UserDto userDto) {
        User savedUser = null;
        if (userNameAlreadyExist(userDto.getUsername())) {
            throw new UserNameNonUniqueException("USER NAME ALREADY EXIST");
        } else {
            savedUser = userRegistrationRepository.save(convertFrom(userDto));

        }
        return savedUser;
    }

    private boolean userNameAlreadyExist(String username) {
        return userRegistrationRepository.findByUsername(username).isPresent();
    }

    private char[] protectPassword(char [] password){
        String salt = PasswordUtils.getSalt(30);

        String mySecurePassword = PasswordUtils.generateSecurePassword(password, salt);
        return mySecurePassword.toCharArray();
    }

    private User convertFrom(UserDto userDto){
        return User.builder()
                .username(userDto.getUsername())
                .password(protectPassword(userDto.getPassword()))
                .dob(userDto.getDob())
                .paymentCardNumber(userDto.getPaymentCardNumber())
                .build();
    }
}

