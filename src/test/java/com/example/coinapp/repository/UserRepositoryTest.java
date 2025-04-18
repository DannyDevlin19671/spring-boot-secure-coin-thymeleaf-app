package com.example.coinapp.repository;

import com.example.coinapp.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldFindUserByUsername() {
        // 🔍 Log the start of the test
        System.out.println("🔍 INFO: Testing finding a user by username.");

        // Given
        User user = new User("testuser", "{bcrypt}dummyHash", "ROLE_USER");
        userRepository.save(user);

        // When
        Optional<User> found = userRepository.findByUsername("testuser");

        // Then
        assertThat(found).isPresent();
        assertThat(found.get().getUsername()).isEqualTo("testuser");

        // ✅ Log the success of the test
        System.out.println("✅ SUCCESS: User found by username successfully.");
    }

    @Test
    public void shouldReturnEmptyIfUsernameNotFound() {
        // 🔍 Log the start of the test
        System.out.println("🔍 INFO: Testing behavior when username is not found.");

        // When
        Optional<User> found = userRepository.findByUsername("unknown");

        // Then
        assertThat(found).isNotPresent();

        // ✅ Log the success of the test
        System.out.println("��� SUCCESS: Correctly returned empty when username not found.");
    }

    @Test
    public void shouldSaveMultipleUsers() {
        // 🔍 Log the start of the test
        System.out.println("🔍 INFO: Testing saving multiple users.");

        // Given
        User user1 = new User("user1", "{bcrypt}hash1", "ROLE_USER");
        User user2 = new User("user2", "{bcrypt}hash2", "ROLE_ADMIN");

        userRepository.save(user1);
        userRepository.save(user2);

        // When
        Optional<User> found1 = userRepository.findByUsername("user1");
        Optional<User> found2 = userRepository.findByUsername("user2");

        // Then
        assertThat(found1).isPresent();
        assertThat(found2).isPresent();

        // ✅ Log the success of the test
        System.out.println("✅ SUCCESS: Multiple users saved and retrieved successfully.");
    }

    @Test
    public void shouldDeleteUserById() {
        // 🔍 Log the start of the test
        System.out.println("🔍 INFO: Testing deleting a user by ID.");

        // Given
        User user = new User("todelete", "{bcrypt}hash", "ROLE_USER");
        User saved = userRepository.save(user);
        Long userId = saved.getId();

        // When
        userRepository.deleteById(userId);

        // Then
        Optional<User> found = userRepository.findById(userId);
        assertThat(found).isEmpty();

        // ✅ Log the success of the test
        System.out.println("✅ SUCCESS: User deleted by ID successfully.");
    }

    @Test
    public void shouldUpdateUserRole() {
        // 🔍 Log the start of the test
        System.out.println("🔍 INFO: Testing updating a user's role.");

        // Given
        User user = new User("testuser", "{bcrypt}hash", "ROLE_USER");
        User saved = userRepository.save(user);

        // When
        saved.setRole("ROLE_ADMIN");
        userRepository.save(saved);

        // Then
        Optional<User> updated = userRepository.findByUsername("testuser");
        assertThat(updated).isPresent();
        assertThat(updated.get().getRole()).isEqualTo("ROLE_ADMIN");

        // ✅ Log the success of the test
        System.out.println("✅ SUCCESS: User role updated successfully.");
    }

    @Test
    public void shouldReturnPagedUsersByRole() {
        // 🔍 Log the start of the test
        System.out.println("🔍 INFO: Testing retrieving paged users by role.");

        // Given
        for (int i = 1; i <= 10; i++) {
            userRepository.save(new User("user" + i, "{bcrypt}hash", "ROLE_USER"));
        }

        Pageable pageable = PageRequest.of(0, 5); // page 0, size 5

        // When
        Page<User> page = userRepository.findAllByRole("ROLE_USER", pageable);

        // Then
        assertThat(page.getContent().size()).isEqualTo(5);
        assertThat(page.getTotalElements()).isEqualTo(10);
        assertThat(page.getTotalPages()).isEqualTo(2);

        // ✅ Log the success of the test
        System.out.println("✅ SUCCESS: Paged users by role retrieved successfully.");
    }
}