package com.gamesys.userregistrationservice.service;

import com.gamesys.userregistrationservice.entity.User;
import com.gamesys.userregistrationservice.repository.UserRegistrationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserRegistrationServiceTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public UserRegistrationService userRegistrationService() {
            return new UserRegistrationService();
        }
    }

    @Autowired
    private UserRegistrationService userRegistrationService;

    @MockBean
    private UserRegistrationRepository userRegistrationRepository;

    @Before
    public void setUp() {
        User alex = User.builder()
                .username("alex")
                .password("Password1".toCharArray())
                .dob(LocalDate.now().minusYears(19))
                .paymentCardNumber("123456789123456").build();

        Mockito.when(userRegistrationRepository.findByUsername(alex.getUsername()))
                .thenReturn(Optional.of(alex));
    }

    @Test
    public void whenValidName_thenUserShouldBeFound() {
        String name = "alex";
        Optional<User> found = userRegistrationRepository.findByUsername(name);

        assertThat(found.get().getUsername())
                .isEqualTo(name);
    }

}
