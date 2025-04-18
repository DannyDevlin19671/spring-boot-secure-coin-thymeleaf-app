package com.example.coinapp.repository;

import com.example.coinapp.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing `Coin` entities.
 * Extends `JpaRepository` to provide CRUD operations and more.
 */
public interface CoinRepository extends JpaRepository<Coin, Long> {
}
