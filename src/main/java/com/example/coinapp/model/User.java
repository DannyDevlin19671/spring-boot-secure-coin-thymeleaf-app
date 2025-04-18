package com.example.coinapp.model;

import jakarta.persistence.*;

/**
 * Represents a user entity in the system.
 * This class is mapped to the "users" table in the database.
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * The unique identifier for the user.
     * This is the primary key and is auto-generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The username of the user.
     * This field is unique and cannot be null.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * The password of the user.
     * This field cannot be null.
     */
    @Column(nullable = false)
    private String password;

    /**
     * The role of the user (e.g., ADMIN, USER).
     * This field cannot be null.
     */
    @Column(nullable = false)
    private String role;

    /**
     * Default constructor for JPA.
     */
    public User() {}

    /**
     * Constructs a new User with the specified username, password, and role.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @param role the role of the user
     */
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Gets the unique identifier of the user.
     *
     * @return the user ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param id the user ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the username of the user.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the role of the user.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }
}
