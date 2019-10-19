package com.gamesys.user.registration.repository;

import com.gamesys.user.registration.model.IssuerStatusEnum;
import com.gamesys.user.registration.model.persistence.PaymentIssuer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @RunWith(SpringRunner.class) is used to provide a bridge between Spring Boot test features and JUnit.
 * Whenever we are using any Spring Boot testing features in our JUnit tests, this annotation will be required.
 * @DataJpaTest provides some standard setup needed for testing the persistence layer:
 * <p>
 * configuring H2, an in-memory database
 * setting Hibernate, Spring Data, and the DataSource
 * performing an @EntityScan
 * turning on SQL logging
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PaymentIssuerRepositoryTest {

    @Autowired
    PaymentIssuerRepository paymentIssuerRepository;
    /**
     * The TestEntityManager provided by Spring Boot is an alternative to the standard JPA EntityManager that provides methods commonly used when writing tests.
     */

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void whenFindByIssuerIdentificationNumber_thenReturnIssuer() {
        // given
        PaymentIssuer paymentIssuer = PaymentIssuer.builder()
                .issuerNumber("123456")
                .status(IssuerStatusEnum.BLOCKED.name())
                .build();

        entityManager.persist(paymentIssuer);
        entityManager.flush();

        // when
        Optional<PaymentIssuer> issuerNumber = paymentIssuerRepository.findByIssuerNumberAndStatus(paymentIssuer.getIssuerNumber(), IssuerStatusEnum.BLOCKED.name());

        // then
        assertThat(issuerNumber.get().getStatus())
                .isEqualTo(IssuerStatusEnum.BLOCKED.name());
    }
}
