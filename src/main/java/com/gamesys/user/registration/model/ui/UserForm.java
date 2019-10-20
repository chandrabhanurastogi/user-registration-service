package com.gamesys.user.registration.model.ui;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gamesys.user.registration.validator.Age;
import com.gamesys.user.registration.validator.ValidPassword;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

    @NotBlank(message = "Username is mandatory")
    @Pattern(regexp = "^[A-Za-z0-9]*$")
    private String username;


    @ValidPassword
    private char[] password;

    @NotNull
    @Age(value = 18)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @NotBlank
//    @Size(min = 15, max = 19)
    @Pattern(regexp = "^[0-9][0-9]{14,18}$")
    private String paymentCardNumber;
}
