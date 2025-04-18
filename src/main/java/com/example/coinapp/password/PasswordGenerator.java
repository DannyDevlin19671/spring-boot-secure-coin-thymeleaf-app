package com.example.coinapp.password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * This class is responsible for generating encoded passwords using the BCrypt hashing algorithm.
 */
public class PasswordGenerator {

    /**
     * The main method serves as the entry point of the application.
     * It demonstrates the encoding of a raw password using BCryptPasswordEncoder.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "password123"; // The raw password to be encoded
        String encodedPassword = encoder.encode(rawPassword); // Encodes the raw password
        System.out.println("Encoded password: " + encodedPassword); // Prints the encoded password
    }
}
