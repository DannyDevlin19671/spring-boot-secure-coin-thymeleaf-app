package com.example.coinapp.authentication;

import com.example.coinapp.data.seeder.TestUserDataSeeder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.logging.Logger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationTests {

    // Logger for logging test information
    Logger logger = Logger.getLogger(AuthenticationTests.class.getName());

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestUserDataSeeder testUserDataSeeder;

    /**
     * Sets up the test environment by clearing and seeding the database
     * with default and admin user data before each test.
     */
    @BeforeEach
    public void setup() {
        testUserDataSeeder.clearUsers();
        testUserDataSeeder.seedDefaultUser();
        testUserDataSeeder.seedAdminUser();
    }

    /**
     * Tests that attempting to log in with invalid credentials
     * results in a redirection to the login page with an error.
     *
     * @throws Exception if the request fails
     */
    @Test
    public void loginWithInvalidCredentialsShouldFail() throws Exception {
        logger.info("🔍 Testing login with invalid credentials");
        mockMvc.perform(post("/login")
                        .param("username", "wrong")
                        .param("password", "wrong"))
                .andExpect(status().isFound()) // 302 redirect
                .andExpect(redirectedUrl("/login?error")); // login failure
        logger.info("✅🟩 Test for invalid credentials passed");
    }

    /**
     * Tests that logging in with valid credentials redirects
     * the user to the "/coins" page.
     *
     * @throws Exception if the request fails
     */
    @Test
    public void loginWithValidCredentialsShouldRedirectToCoins() throws Exception {
        logger.info("🔍 Testing login with valid credentials");
        mockMvc.perform(post("/login")
                        .param("username", "admin")  // Replace with valid user
                        .param("password", "password123")  // Replace with valid password
                ).andExpect(status().isFound())
                .andExpect(redirectedUrl("/coins")); // default success URL
        logger.info("✅🟩 Test for valid credentials passed");
    }

    /**
     * Tests that logging out invalidates the session and redirects
     * the user to the "/logout" page.
     *
     * @throws Exception if the request fails
     */
    @Test
    @WithMockUser(username = "user")
    public void logoutShouldInvalidateSessionAndRedirect() throws Exception {
        logger.info("🔍 Testing logout functionality");
        mockMvc.perform(post("/logout"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/logout-success"));
        logger.info("✅🟩 Test for logout functionality passed");
    }

    /**
     * Tests that unauthenticated access to the "/coins" page
     * redirects the user to the login page.
     *
     * @throws Exception if the request fails
     */
    @Test
    public void unauthenticatedAccessToCoinsRedirectsToLogin() throws Exception {
        logger.info("🔍 Testing unauthenticated access to /coins");
        mockMvc.perform(get("/coins"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
        logger.info("✅🟩 Test for unauthenticated access passed");
    }
}
