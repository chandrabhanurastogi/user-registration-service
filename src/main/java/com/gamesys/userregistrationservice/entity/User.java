package com.gamesys.userregistrationservice.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String username;
    private char[] password;
    private Date dob;
    private String paymentCardNumber;
}
