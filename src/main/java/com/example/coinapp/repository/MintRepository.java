package com.example.coinapp.repository;

import com.example.coinapp.model.Mint;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing `Mint` entities.
 * Extends `JpaRepository` to provide CRUD operations and more.
 */
public interface MintRepository extends JpaRepository<Mint, Long> {
}
