package com.gamesys.userregistrationservice.entity;

import com.gamesys.userregistrationservice.validator.Age;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Data
@ToString
public class User {

    @Id
    @NotBlank(message = "Username is mandatory")
    @Pattern(regexp = "^[A-Za-z0-9]*$")
    private String username;

    @NotBlank
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[0-9]).{4,}$")
    private String password;

    @NotNull
    @Age(value=18)
    private LocalDate dob;

    //    @Pattern(regexp = "^[0-9][0-9]{14,18}$")
    @NotBlank
    @Size(min = 15, max = 19)
    private String paymentCardNumber;
}
