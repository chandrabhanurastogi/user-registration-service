package com.gamesys.user.registration.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gamesys.user.registration.UserTestEnum;
import com.gamesys.user.registration.model.persistence.User;
import com.gamesys.user.registration.model.ui.UserForm;
import com.gamesys.user.registration.service.UserRegistrationService;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;

/**
 * Since we're only testing the web layer, we use the @WebMvcTest annotation.
 * It allows us to easily test requests and responses using the set of static methods
 * implemented by the MockMvcRequestBuilders and MockMvcResultMatchers classes.
 */

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserFormRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserRegistrationService userRegistrationService;

    @Before
    public void setUp() {
        User user = User.builder().build();
        Mockito.when(userRegistrationService.registerUser(any(UserForm.class)))
                .thenReturn(user);
    }

    @Test
    public void whenRequestToRegisterAndUserValid_thenCorrectResponse() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
        String user = buildUserForm(UserTestEnum.UN_OK);

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(textPlainUtf8));
    }

    @Test
    public void whenRequestToRegisterAndUsernameNonAlphanumeric_thenBadRequest() throws Exception {
        String user = buildUserForm(UserTestEnum.UN_NON_ALPHANUMERIC);
        performTest(user, HttpStatus.BAD_REQUEST);
    }

    @Test
    public void whenRequestToRegisterAndUsernameWithSpace_thenBadRequest() throws Exception {
        String user = buildUserForm(UserTestEnum.UN_SPACE);
        performTest(user, HttpStatus.BAD_REQUEST);
    }

    @Test
    public void whenRequestToRegisterAndUsernameBlank_thenBadRequest() throws Exception {
        String user = buildUserForm(UserTestEnum.UN_BLANK);
        performTest(user, HttpStatus.BAD_REQUEST);
    }

    @Test
    public void whenRequestToRegisterAndPasswordLessThan4Digit_thenBadRequest() throws Exception {
        String user = buildUserForm(UserTestEnum.PW_LENGTH_LESS_THAN_4);
        performTest(user, HttpStatus.BAD_REQUEST);
    }

    @Test
    public void whenRequestToRegisterAndPasswordNoUpperCase_thenBadRequest() throws Exception {
        String user = buildUserForm(UserTestEnum.PW_NO_UPPER_CASE);
        performTest(user, HttpStatus.BAD_REQUEST);
    }

    @Test
    public void whenRequestToRegisterAndPasswordNoDigit_thenBadRequest() throws Exception {
        String user = buildUserForm(UserTestEnum.PW_NO_NUMBER);
        performTest(user, HttpStatus.BAD_REQUEST);
    }

    @Test
    public void whenRequestToRegisterAndPcnLessThan15Digit_thenBadRequest() throws Exception {

        String user = buildUserForm(UserTestEnum.PCN_LESS_THAN_15);
        performTest(user, HttpStatus.BAD_REQUEST);
    }

    @Test
    public void whenRequestToRegisterAndPcnMoreThan19Digit_thenBadRequest() throws Exception {

        String user = buildUserForm(UserTestEnum.PCN_MORE_THAN_19);
        performTest(user, HttpStatus.BAD_REQUEST);
    }

    @Test
    public void whenRequestToRegisterAndPcnNonDigit_thenBadRequest() throws Exception {

        String user = buildUserForm(UserTestEnum.PCN_NON_DIGIT);
        performTest(user, HttpStatus.BAD_REQUEST);
    }

    @Test
    public void whenRequestToRegisterAndAgeLessThan18_thenForbidden() throws Exception {
        String user = buildUserForm(UserTestEnum.DOB_LESS_THAN_18YR);
        performTest(user, HttpStatus.FORBIDDEN);
    }

    private String buildUserForm(UserTestEnum testEnum) throws JsonProcessingException {

        String userName = testEnum.name().contains("UN") ? testEnum.val() : UserTestEnum.UN_OK.val();
        String password = testEnum.name().contains("PW") ? testEnum.val() : UserTestEnum.PW_OK.val();
        String dob = testEnum.name().contains("DOB") ? testEnum.val() : UserTestEnum.DOB_OK.val();
        String paymentCardNum = testEnum.name().contains("PCN") ? testEnum.val() : UserTestEnum.PCN_OK.val();

        UserForm userForm = UserForm.builder()
                .username(userName)
                .password(password.toCharArray())
                .dob(LocalDate.parse(dob))
                .paymentCardNumber(paymentCardNum)
                .build();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper.writeValueAsString(userForm);
    }

    private void performTest(String userJson, HttpStatus status) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .content(userJson)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is(status.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Is.is("Validation error")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8));
    }

}
