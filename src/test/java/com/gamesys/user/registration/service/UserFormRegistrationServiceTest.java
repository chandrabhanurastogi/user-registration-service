package com.gamesys.user.registration.service;

import com.gamesys.user.registration.UserTestUtil;
import com.gamesys.user.registration.exception.DuplicateUserException;
import com.gamesys.user.registration.exception.PaymentIssuerBlockedException;
import com.gamesys.user.registration.model.persistence.PaymentIssuer;
import com.gamesys.user.registration.model.persistence.User;
import com.gamesys.user.registration.model.ui.UserForm;
import com.gamesys.user.registration.repository.PaymentIssuerRepository;
import com.gamesys.user.registration.repository.UserRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class UserFormRegistrationServiceTest {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PaymentIssuerRepository paymentIssuerRepository;

    @Rule
    final public ExpectedException thrown = ExpectedException.none();

    @Test
    public void whenUsernameDuplicate_thenThrowDuplicateUserException() {
        thrown.expect(DuplicateUserException.class);
        thrown.expectMessage("USER NAME ALREADY EXIST");

        Mockito.when(userRepository.findByUsername(any(String.class)))
                .thenReturn(Optional.of(new User()));


        userRegistrationService.registerUser(UserTestUtil.buildValidUserForm());
    }

    @Test
    public void whenIINBlocked_thenThrowPaymentIssuerBlockedException() {
        thrown.expect(PaymentIssuerBlockedException.class);

        Mockito.when(paymentIssuerRepository
                .findByIssuerNumberAndStatus(any(String.class),any(String.class)))
                .thenReturn(Optional.of(new PaymentIssuer()));


        userRegistrationService.registerUser(UserTestUtil.buildValidUserForm());
        thrown.expectMessage("Issuer Identification Number is blocked");
    }

    @Test
    public void whenValidUser_thenUserShouldBeRegistered() {
        String name = "alex";

        User alex = User.builder()
                .username("alex")
                .build();

        UserForm userForm = UserTestUtil.buildValidUserForm();

        Mockito.when(userRepository.findByUsername(any(String.class)))
                .thenReturn(Optional.empty());

        Mockito.when(userRepository.save(any(User.class)))
                .thenReturn(alex);

        Mockito.when(paymentIssuerRepository
                .findByIssuerNumberAndStatus(any(String.class),any(String.class)))
                .thenReturn(Optional.empty());

        User found = userRegistrationService.registerUser(userForm);

        assertThat(found.getUsername().equals(name));
    }



    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public UserRegistrationService userRegistrationService() {
            return new UserRegistrationService();
        }
    }

}
