package com.gamesys.user.registration.model.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentIssuer {

    @Id
    @GeneratedValue
    private long id;

    private String issuerNumber;

    private String status;
}
