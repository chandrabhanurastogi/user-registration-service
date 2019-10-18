package com.gamesys.userregistrationservice.repository;

import com.gamesys.userregistrationservice.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @RunWith(SpringRunner.class) is used to provide a bridge between Spring Boot test features and JUnit.
 * Whenever we are using any Spring Boot testing features in our JUnit tests, this annotation will be required.
 *
 * @DataJpaTest provides some standard setup needed for testing the persistence layer:
 *
 * configuring H2, an in-memory database
 * setting Hibernate, Spring Data, and the DataSource
 * performing an @EntityScan
 * turning on SQL logging
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    /**
     * The TestEntityManager provided by Spring Boot is an alternative to the standard JPA EntityManager that provides methods commonly used when writing tests.
     */

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    UserRegistrationRepository userRepository;

    @Test
    public void whenFindByUsername_thenReturnUser() {
        // given
        User alex = User.builder()
                .username("alex")
                .password("Password1".toCharArray())
                .dob(LocalDate.now().minusYears(19))
                .paymentCardNumber("123456789123456").build();

        entityManager.persist(alex);
        entityManager.flush();

        // when
        Optional<User> userFound= userRepository.findByUsername(alex.getUsername());

        // then
        assertThat(userFound.get().getUsername())
                .isEqualTo(alex.getUsername());
    }
}
