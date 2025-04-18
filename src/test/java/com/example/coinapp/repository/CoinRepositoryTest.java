package com.example.coinapp.repository;

import com.example.coinapp.model.Coin;
import com.example.coinapp.model.Mint;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CoinRepositoryTest {

    @Autowired
    private CoinRepository coinRepository;

    @Autowired
    private MintRepository mintRepository;

    @Test
    public void shouldSaveAndFindCoins() {
        // Given
        Mint ukMint = new Mint("United Kingdom");
        mintRepository.save(ukMint);

        Coin coin = new Coin("Test Coin", "A test coin", "2024", "Gold", "1 oz", ukMint);
        coinRepository.save(coin);

        // When
        List<Coin> coins = coinRepository.findAll();

        // Then
        assertThat(coins).isNotEmpty();
        assertThat(coins.get(0).getMint().getCountry()).isEqualTo("United Kingdom");
    }
}
