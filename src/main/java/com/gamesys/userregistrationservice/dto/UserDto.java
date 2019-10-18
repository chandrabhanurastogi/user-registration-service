package com.gamesys.userregistrationservice.dto;

import com.gamesys.userregistrationservice.validator.Age;
import com.gamesys.userregistrationservice.validator.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "Username is mandatory")
    @Pattern(regexp = "^[A-Za-z0-9]*$")
    private String username;


    @ValidPassword
    private char[] password;

    @NotNull
    @Age(value=18)
    private LocalDate dob;

    @NotBlank
    @Size(min = 15, max = 19)
    private String paymentCardNumber;
}
