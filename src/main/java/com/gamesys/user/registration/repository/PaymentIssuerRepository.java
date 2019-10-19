package com.gamesys.user.registration.repository;

import com.gamesys.user.registration.model.persistence.PaymentIssuer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentIssuerRepository extends CrudRepository<PaymentIssuer, Long> {
    Optional<PaymentIssuer> findByIssuerNumberAndStatus(String issuerNumber, String status);
}
