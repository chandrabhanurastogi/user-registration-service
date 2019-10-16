package com.gamesys.userregistrationservice.controller;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;

/**
 * Since we're only testing the web layer, we use the @WebMvcTest annotation.
 * It allows us to easily test requests and responses using the set of static methods
 * implemented by the MockMvcRequestBuilders and MockMvcResultMatchers classes.
 *
 * https://www.baeldung.com/spring-boot-testing
 */

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class UserRegristrationControllerTest {
    @Autowired
    UserRegistrationController userRegistrationController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenPostRequestToUsersAndValidUser_thenCorrectRespone() throws Exception{
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
        String user = "{\"username\":\"BobFrench\",\"password\":\"Password1\",\"dob\":\"1980-02-21\",\"paymentCardNumber\":\"349293081054422\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(textPlainUtf8));
    }

    @Test
    public void whenPostRequestToUsersAndInValidUser_thenCorrectRespone() throws Exception{
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
        String user = "{\"username\":\"\",\"password\":\"Password1\",\"dob\":\"1980-02-21\",\"paymentCardNumber\":\"349293081054422\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Is.is("Validation error")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void whenPostRequestToUsersAndInValidPaymentCard_thenCorrectRespone() throws Exception{
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
        String user = "{\"username\":\"BobFrench\",\"password\":\"Password1\",\"dob\":\"1980-02-21\",\"paymentCardNumber\":\"12345678912345\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Is.is("Validation error")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8));
    }
}
