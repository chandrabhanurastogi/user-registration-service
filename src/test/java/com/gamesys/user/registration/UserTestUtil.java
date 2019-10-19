package com.gamesys.user.registration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gamesys.user.registration.model.ui.UserForm;

import java.time.LocalDate;

public class UserTestUtil {

    public static UserForm buildInvalidUserForm(UserTestEnum testEnum){

        String userName = testEnum.name().contains("UN") ? testEnum.val() : UserTestEnum.UN_OK.val();
        String password = testEnum.name().contains("PW") ? testEnum.val() : UserTestEnum.PW_OK.val();
        String dob = testEnum.name().contains("DOB") ? testEnum.val() : UserTestEnum.DOB_OK.val();
        String paymentCardNum = testEnum.name().contains("PCN") ? testEnum.val() : UserTestEnum.PCN_OK.val();

        return UserForm.builder()
                .username(userName)
                .password(password.toCharArray())
                .dob(LocalDate.parse(dob))
                .paymentCardNumber(paymentCardNum)
                .build();
    }

    public static UserForm buildValidUserForm() {

        String userName = UserTestEnum.UN_OK.val();
        String password = UserTestEnum.PW_OK.val();
        String dob = UserTestEnum.DOB_OK.val();
        String paymentCardNum = UserTestEnum.PCN_OK.val();

        return UserForm.builder()
                .username(userName)
                .password(password.toCharArray())
                .dob(LocalDate.parse(dob))
                .paymentCardNumber(paymentCardNum)
                .build();
    }

    public static String getJsonOf(UserForm userForm) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper.writeValueAsString(userForm);
    }
}
