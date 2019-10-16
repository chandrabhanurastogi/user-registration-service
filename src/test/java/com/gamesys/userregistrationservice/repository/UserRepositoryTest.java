package com.gamesys.userregistrationservice.repository;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Autowired
    private TestEntityManager entityManager;
}
