package com.example.coinapp.data.seeder;

import com.example.coinapp.model.User;
import com.example.coinapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class TestUserDataSeeder {

    private final Logger logger = Logger.getLogger(TestUserDataSeeder.class.getName());
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for TestUserDataSeeder.
     *
     * @param userRepository  the repository for managing User entities
     * @param passwordEncoder the encoder for hashing passwords
     */
    public TestUserDataSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        logger.info("🔍 Initializing TestUserDataSeeder");
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        logger.info("✅🟩 TestUserDataSeeder initialized");
    }

    /**
     * Deletes all users from the database.
     */
    public void clearUsers() {
        logger.info("🔍 Clearing all users from the database");
        userRepository.deleteAll();
        logger.info("✅🟩 All users cleared from the database");
    }

    /**
     * Seeds a user with the specified username, raw password, and role.
     *
     * @param username   the username of the user
     * @param rawPassword the raw (unencrypted) password of the user
     * @param role       the role of the user
     */
    public void seedUser(String username, String rawPassword, String role) {
        logger.info("🔍 Seeding user with username: " + username + ", role: " + role);
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(role);
        logger.info("Saving username: " + user.getUsername() + ", role: " +
                user.getRole() + ", password: " + rawPassword + " (encoded)" + user.getPassword());
        userRepository.save(user);
        logger.info("✅🟩 User seeded: " + username);
    }

    /**
     * Seeds a default user with the username "user", password "password123", and role "USER".
     */
    public void seedDefaultUser() {
        logger.info("🔍 Seeding default user");
        seedUser("user", "password123", "USER");
        logger.info("✅🟩 Default user seeded");
    }

    /**
     * Seeds an admin user with the username "admin", password "password123", and role "ADMIN".
     */
    public void seedAdminUser(){
        logger.info("🔍 Seeding admin user");
        seedUser("admin", "password123", "ADMIN");
        logger.info("✅🟩 Admin user seeded");
    }
}
