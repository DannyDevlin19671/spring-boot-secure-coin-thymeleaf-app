package com.example.coinapp.entity;

import com.example.coinapp.model.User;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private final Logger logger = Logger.getLogger(UserTest.class.getName());

    @Test
    public void testUserProperties() {
        logger.info("🔍 Testing User properties...");
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password123");

        assertEquals("admin", user.getUsername());
        assertEquals("password123", user.getPassword());
        logger.info("✅ User properties test passed.");
    }
}
