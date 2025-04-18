package com.example.coinapp.repository;

import com.example.coinapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository interface for managing `User` entities.
 * Extends JpaRepository to provide CRUD operations and additional query methods.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a `User` entity by its username.
     *
     * @param username the username of the user to find
     * @return an `Optional` containing the found `User`, or empty if no user is found
     */
    Optional<User> findByUsername(String username);
}
