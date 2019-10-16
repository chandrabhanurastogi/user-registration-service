package com.gamesys.userregistrationservice.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@ToString
public class User {

    @Id
    private String username;
    private char[] password;
    private Date dob;
    private String paymentCardNumber;
}
