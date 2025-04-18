package com.example.coinapp.entity;

import org.junit.jupiter.api.Test;
import com.example.coinapp.model.Coin;
import com.example.coinapp.model.Mint;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class CoinTest {

    private final Logger logger = Logger.getLogger(CoinTest.class.getName());

    @Test
    public void testCoinConstructorAndGetters() {
        logger.info("🔍 Testing Coin constructor and getters");
        Mint mint = new Mint("UK");
        Coin coin = new Coin("Test Coin", "Test Desc", mint, 99.99, "Silver", 2022, 0.999, 31.1);

        assertEquals("Test Coin", coin.getName());
        assertEquals("Test Desc", coin.getDescription());
        assertEquals(mint, coin.getMint());
        assertEquals(99.99, coin.getPrice());
        assertEquals("Silver", coin.getMetal());
        assertEquals(2022, coin.getCoinYear());
        assertEquals(0.999, coin.getFineness());
        assertEquals(31.1, coin.getWeight());
        logger.info("✅ Coin constructor and getters test passed");
    }

    @Test
    public void testSetters() {
        logger.info("🔍 Testing Coin setters");
        Coin coin = new Coin();
        coin.setName("Gold Coin");
        coin.setDescription("Limited edition");
        coin.setMetal("Gold");
        coin.setCoinYear(2021);
        coin.setPrice(500.0);
        coin.setFineness(0.995);
        coin.setWeight(1.0);
        coin.setMint(new Mint("France"));

        assertEquals("Gold Coin", coin.getName());
        assertEquals("Limited edition", coin.getDescription());
        assertEquals("Gold", coin.getMetal());
        assertEquals(2021, coin.getCoinYear());
        assertEquals(500.0, coin.getPrice());
        assertEquals(0.995, coin.getFineness());
        assertEquals(1.0, coin.getWeight());
        assertEquals("France", coin.getMint().getCountry());
        logger.info("✅ Coin setters test passed");
    }
}
