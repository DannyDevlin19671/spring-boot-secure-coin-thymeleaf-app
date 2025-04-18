package com.example.coinapp.repository;

import com.example.coinapp.model.Mint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing `Mint` entities.
 * Extends `JpaRepository` to provide CRUD operations and more.
 */
public interface MintRepository extends JpaRepository<Mint, Long> {
    // Find a Mint by exact country name
    Optional<Mint> findByCountry(String country);

    // Find all mints from a country, case insensitive
    List<Mint> findByCountryIgnoreCase(String country);

    // Find all mints with a country name containing the given keyword
    List<Mint> findByCountryContaining(String keyword);

    // Check if a mint exists by country name
    boolean existsByCountry(String country);

    // Count how many mints are registered for a specific country
    long countByCountry(String country);

    // Delete mints by country
    void deleteByCountry(String country);

    // Find the first mint in alphabetical order
    Optional<Mint> findFirstByOrderByCountryAsc();
}
