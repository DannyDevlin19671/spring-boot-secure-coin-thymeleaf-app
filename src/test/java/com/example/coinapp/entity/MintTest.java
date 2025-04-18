package com.example.coinapp.entity;

import org.junit.jupiter.api.Test;
import com.example.coinapp.model.Coin;
import com.example.coinapp.model.Mint;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class MintTest {

    private final Logger logger = Logger.getLogger(MintTest.class.getName());
    @Test
    public void testMintConstructorAndGetters() {
        logger.info("🔍 Testing Mint constructor and getters");
        Mint mint = new Mint("Germany");
        assertEquals("Germany", mint.getCountry());
        logger.info("✅ Mint constructor and getters test passed");
    }

    @Test
    public void testSettersAndCoinAssociation() {
        logger.info("🔍 Testing Mint setters and coin association");
        Mint mint = new Mint();
        mint.setCountry("USA");

        Coin coin = new Coin("Dime", "Ten cents", mint, 0.1, "Copper-Nickel", 2020, 0.9, 2.5);
        mint.setCoins(List.of(coin));

        assertEquals("USA", mint.getCountry());
        assertEquals(1, mint.getCoins().size());
        assertEquals("Dime", mint.getCoins().get(0).getName());
        logger.info("✅ Mint setters and coin association test passed");
    }
}
