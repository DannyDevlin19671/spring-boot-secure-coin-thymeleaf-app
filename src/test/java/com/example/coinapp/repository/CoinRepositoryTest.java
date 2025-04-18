package com.example.coinapp.repository;

import com.example.coinapp.model.Coin;
import com.example.coinapp.model.Mint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class CoinRepositoryTest {

    @Autowired
    private CoinRepository coinRepository;

    @Autowired
    private MintRepository mintRepository;

    private Mint ukMint;

    @BeforeEach
    public void setUp() {
        coinRepository.deleteAll();
        mintRepository.deleteAll();

        ukMint = mintRepository.save(new Mint("United Kingdom"));

        coinRepository.save(new Coin("Sovereign", "Gold coin", ukMint, 300.0, "Gold", 2020, 0.916, 7.98));
        coinRepository.save(new Coin("Britannia", "Silver coin", ukMint, 50.0, "Silver", 2021, 0.999, 31.1));
        coinRepository.save(new Coin("Half Sovereign", "Gold coin", ukMint, 150.0, "Gold", 2020, 0.916, 3.99));
    }

    @Test
    public void shouldFindByName() {
        System.out.println("🔍 Testing: shouldFindByName");
        Optional<Coin> result = coinRepository.findByName("Sovereign");
        assertThat(result).isPresent();
        System.out.println("✅ Success: shouldFindByName");
    }

    @Test
    public void shouldFindByMetal() {
        System.out.println("🔍 Testing: shouldFindByMetal");
        List<Coin> results = coinRepository.findByMetal("Gold");
        assertThat(results).hasSize(2);
        System.out.println("✅ Success: shouldFindByMetal");
    }

    @Test
    public void shouldFindByCoinYear() {
        System.out.println("🔍 Testing: shouldFindByCoinYear");
        List<Coin> results = coinRepository.findByCoinYear(2020);
        assertThat(results).hasSize(2);
        System.out.println("✅ Success: shouldFindByCoinYear");
    }

    @Test
    public void shouldFindByMetalAndCoinYear() {
        System.out.println("🔍 Testing: shouldFindByMetalAndCoinYear");
        List<Coin> results = coinRepository.findByMetalAndCoinYear("Gold", 2020);
        assertThat(results).hasSize(2);
        System.out.println("✅ Success: shouldFindByMetalAndCoinYear");
    }

    @Test
    public void shouldFindByPriceGreaterThan() {
        System.out.println("🔍 Testing: shouldFindByPriceGreaterThan");
        List<Coin> results = coinRepository.findByPriceGreaterThan(100);
        assertThat(results).hasSize(2);
        System.out.println("✅ Success: shouldFindByPriceGreaterThan");
    }

    @Test
    public void shouldFindByPriceBetween() {
        System.out.println("🔍 Testing: shouldFindByPriceBetween");
        List<Coin> results = coinRepository.findByPriceBetween(100, 300);
        assertThat(results).hasSize(2);
        System.out.println("✅ Success: shouldFindByPriceBetween");
    }

    @Test
    public void shouldFindByNameContainingIgnoreCase() {
        System.out.println("🔍 Testing: shouldFindByNameContainingIgnoreCase");
        List<Coin> results = coinRepository.findByNameContainingIgnoreCase("sovereign");
        assertThat(results).hasSize(2);
        System.out.println("✅ Success: shouldFindByNameContainingIgnoreCase");
    }

    @Test
    public void shouldCountByMetal() {
        System.out.println("🔍 Testing: shouldCountByMetal");
        long count = coinRepository.countByMetal("Gold");
        assertThat(count).isEqualTo(2);
        System.out.println("✅ Success: shouldCountByMetal");
    }

    @Test
    public void shouldCheckExistsByName() {
        System.out.println("🔍 Testing: shouldCheckExistsByName");
        boolean exists = coinRepository.existsByName("Britannia");
        assertThat(exists).isTrue();
        System.out.println("✅ Success: shouldCheckExistsByName");
    }
}