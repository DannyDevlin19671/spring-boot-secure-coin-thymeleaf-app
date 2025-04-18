package com.example.coinapp.controller;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final Logger logger = LoggerFactory.getLogger(HomeControllerTest.class);

    @Test
    public void unauthenticatedAccessToRootReturnsHomeView() throws Exception {
        logger.info("🔍 Testing unauthenticated access to / returns home view");
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
        logger.info("✅ Unauthenticated access returned home view");
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER"})
    public void authenticatedAccessToRootRedirectsToCoins() throws Exception {
        logger.info("🔍 Testing authenticated access to / redirects to /coins");
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/coins"));
        logger.info("✅ Authenticated user redirected to /coins");
    }

    @Test
    public void loginReturnsLoginView() throws Exception {
        logger.info("🔍 Testing /login returns login view");
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
        logger.info("✅ Login page returned successfully");
    }

    @Test
    public void logoutRedirectsToLogoutSuccess() throws Exception {
        logger.info("🔍 Testing /logout redirects to /logout-success");
        mockMvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/logout-success"));
        logger.info("✅ Logout redirected to /logout-success");
    }
}
