package com.cilek.cilekbank;

import com.cilek.cilekbank.controller.AuthenticationController;
import com.cilek.cilekbank.model._User;
import com.cilek.cilekbank.service.abstracts.IAuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


public class AuthenticationControllerTests {

    private MockMvc mockMvc;

    private AuthenticationController authenticationController;

    private IAuthService authService;

    private _User testUser;

    @BeforeEach
    void setUp() throws Exception {

        authenticationController = new AuthenticationController(
            authService
        );

        // create a user for testing
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();

        // create a user for testing
        ResultActions perform = mockMvc.perform(post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "username": "testuser",
                            "password": "testpassword",
                            "email": "test@test.com"
                        }
                        """));

    }

    // Test AuthenticationController
    @ParameterizedTest
    @CsvSource({
            "testuser, testpassword",
            "testuser, wrongpassword",
            "wronguser, testpassword"
    })
    void testCanRegisterAndItReturnsAJWTToken() throws Exception {
        ResultActions perform = mockMvc.perform(post("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "username": "testuser",
                            "password": "testpassword"
                        }
                        """));

        // check if the response contains a token
        perform.andExpect(result -> {
            String contentAsString = result.getResponse().getContentAsString();
            assert contentAsString.contains("token");
        });
    }
    // Test register method
    // Test login method
}
