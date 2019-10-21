package com.gamesys.user.registration.service;

import com.gamesys.user.registration.exception.DuplicateUserException;
import com.gamesys.user.registration.exception.PaymentIssuerBlockedException;
import com.gamesys.user.registration.model.IssuerStatusEnum;
import com.gamesys.user.registration.model.persistence.User;
import com.gamesys.user.registration.model.ui.UserForm;
import com.gamesys.user.registration.repository.PaymentIssuerRepository;
import com.gamesys.user.registration.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserRegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentIssuerRepository paymentIssuerRepository;

    /**
     * Creates new {@link User } object if {@code UserForm} has unique username.
     * The {@link User } object is build from {@code UserForm} argument and passed
     * {link {@link UserRepository} repository to persis in database
     *
     * @param userForm user details for registration
     * @return
     * @throws DuplicateUserException        if the {@code UserForm.username}
     *                                       argument is already registered with another account.
     * @throws PaymentIssuerBlockedException if the IIN is blocked
     */
    public UserForm registerUser(UserForm userForm) {
        if (userNameAlreadyExist(userForm.getUsername())) {
            log.error("Username already exist. Stop Registering new user");
            //TODO: Avoid hard coding error message and read it from relevant source
            throw new DuplicateUserException("USER NAME ALREADY EXIST");
        } else if (isIssuerNumberBlocked(userForm.getPaymentCardNumber())) {
            log.error("Issuer Identification Number is blocked");
            throw new PaymentIssuerBlockedException(" issuer identification number is blocked");
        } else {
            log.info("Username is unique. Registering new account");
            User savedUser = userRepository.save(mapUserFrom(userForm));
            log.info("User is successfully saved with the id {}", savedUser.getId());
            return userForm;
        }
    }

    private boolean isIssuerNumberBlocked(String paymentCardNumber) {
        String issuerIdentificationNumber = paymentCardNumber.substring(0, 6);
        return paymentIssuerRepository.findByIssuerNumberAndStatus(issuerIdentificationNumber, IssuerStatusEnum.BLOCKED.name()).isPresent();
    }

    /**
     * userNameAlreadyExist checks if given {@link UserForm#getUsername()} is present in database
     *
     * @param username given username to register.
     * @return {@code true} if the given username if username already exist in database
     * {@code false} otherwise
     */
    private boolean userNameAlreadyExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    /**
     * Converts {@link UserForm} object into {@link User} entity object.
     * password is hashed into secure char [] while conversion.
     *
     * @param userForm user details for registration
     * @return {@link UserForm} object which hashed password and all
     * other fields as present in {@code UserForm} argument.
     */
    private User mapUserFrom(UserForm userForm) {
        return User.builder()
                .username(userForm.getUsername())
                .password(hashString(userForm.getPassword()))
                .dob(userForm.getDob())
                .paymentCardNumber(userForm.getPaymentCardNumber())
                .build();
    }

    /**
     * generates hash from {code char[]  password} argument using salt.
     *
     * @param input provided from user
     * @return encoded password char []
     */
    private char[] hashString(char[] input) {
        String salt = EncodingService.getSalt(30);
        String mySecurePassword = EncodingService.generateSecurePassword(input, salt);
        return mySecurePassword.toCharArray();
    }
}

