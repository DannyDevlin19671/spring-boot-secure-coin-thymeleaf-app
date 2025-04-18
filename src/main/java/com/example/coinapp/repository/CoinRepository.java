package com.example.coinapp.repository;

import com.example.coinapp.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing `Coin` entities.
 * Extends `JpaRepository` to provide CRUD operations and query methods.
 */
public interface CoinRepository extends JpaRepository<Coin, Long> {

    // Find coins by exact name
    Optional<Coin> findByName(String name);

    // Find all coins by metal (e.g., Gold, Silver)
    List<Coin> findByMetal(String metal);

    // Find coins minted in a specific year
    List<Coin> findByCoinYear(int coinYear);

    // Find coins by metal and year
    List<Coin> findByMetalAndCoinYear(String metal, int coinYear);

    // Find coins with price greater than a certain amount
    List<Coin> findByPriceGreaterThan(double price);

    // Find coins with a price range
    List<Coin> findByPriceBetween(double minPrice, double maxPrice);

    // Find coins containing a substring in the name, case-insensitive
    List<Coin> findByNameContainingIgnoreCase(String name);

    // Count how many coins belong to a certain metal
    long countByMetal(String metal);

    // Check existence by name
    boolean existsByName(String name);
}
