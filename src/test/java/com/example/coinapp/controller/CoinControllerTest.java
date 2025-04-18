package com.example.coinapp.controller;

import java.util.Optional;

import com.example.coinapp.exception.CoinNotFoundException;
import com.example.coinapp.model.Coin;
import com.example.coinapp.model.Mint;
import com.example.coinapp.repository.CoinRepository;
import com.example.coinapp.repository.MintRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.hasProperty; // ✅ correct
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(CoinController.class)
@ActiveProfiles("test")
class CoinControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(CoinControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoinRepository coinRepository;

    @MockBean
    private MintRepository mintRepository;

    @Test
    @WithMockUser
    void viewCoins_displaysListOfCoins() throws Exception {
        logger.info("🔍 Testing coin list view");

        Mint mint = new Mint();
        mint.setId(1L);
        mint.setCountry("UK"); // ✅ Required for ${coin.mint.country}

        Coin coin = new Coin();
        coin.setId(1L);
        coin.setName("Test Coin");
        coin.setMint(mint); // ✅ Important: avoids null pointer in template

        given(coinRepository.findAll()).willReturn(Arrays.asList(coin));

        mockMvc.perform(get("/coins"))
                .andExpect(status().isOk())
                .andExpect(view().name("coins/list"))
                .andExpect(model().attributeExists("coins"))
                .andExpect(model().attribute("coins", hasSize(1)))
                .andExpect(model().attribute("coins", hasItem(
                        hasProperty("name", is("Test Coin"))
                )));

        logger.info("✅ Coin list view test passed.");
    }

    @Test
    @WithMockUser
    void showAddForm_displaysCoinForm() throws Exception {
        logger.info("🔍 Testing show add form");

        given(mintRepository.findAll()).willReturn(Arrays.asList(new Mint()));

        mockMvc.perform(get("/coins/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("coins/form"))
                .andExpect(model().attributeExists("coin", "mints"));

        logger.info("✅ Show add form test passed.");
    }

    @Test
    @WithMockUser
    void saveCoin_redirectsToCoinList() throws Exception {
        logger.info("🔍 Testing save coin functionality (form encoded)");

        Mint mint = new Mint();
        mint.setId(1L);
        mint.setCountry("UK");

        // Optionally stub out mintRepository if needed for form rendering
        given(mintRepository.findAll()).willReturn(Arrays.asList(mint));

        mockMvc.perform(post("/coins")
                        .with(csrf())
                        .param("name", "New Coin")
                        .param("description", "Test Coin Description")
                        .param("metal", "Gold")
                        .param("price", "10.5")
                        .param("coinYear", "2020")
                        .param("fineness", "0.999")
                        .param("weight", "31.1")
                        .param("mint.id", "1")) // 🟢 Required to bind to `mint` field
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/coins"));

        logger.info("✅ Save coin test passed.");
    }

    @Test
    @WithMockUser
    void showEditForm_loadsCoinAndForm() throws Exception {
        logger.info("🔍 Testing show edit form");

        Coin coin = new Coin();
        coin.setId(1L);
        coin.setName("Edit Coin");

        given(coinRepository.findById(1L)).willReturn(Optional.of(coin));
        given(mintRepository.findAll()).willReturn(Arrays.asList(new Mint()));

        mockMvc.perform(get("/coins/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("coins/form"))
                .andExpect(model().attribute("coin", hasProperty("name", is("Edit Coin"))))
                .andExpect(model().attributeExists("mints"));

        logger.info("✅ Edit form test passed.");
    }

    @Test
    @WithMockUser
    void deleteCoin_removesCoinAndRedirects() throws Exception {
        logger.info("🔍 Testing delete coin functionality");

        mockMvc.perform(get("/coins/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/coins"));

        logger.info("✅ Delete coin test passed.");
    }

    @Test
    @WithMockUser
    void showCoinDetails_displaysCorrectCoin() throws Exception {
        logger.info("🔍 Testing show coin details");

        Mint mint = new Mint();
        mint.setId(1L);
        mint.setCountry("UK");

        Coin coin = new Coin();
        coin.setId(1L);
        coin.setName("Detail Coin");
        coin.setMint(mint); // ✅ Important: avoids null pointer in template

        given(coinRepository.findById(1L)).willReturn(Optional.of(coin));

        mockMvc.perform(get("/coins/details/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("coin-details"))
                .andExpect(model().attribute("coin", hasProperty("name", is("Detail Coin"))));

        logger.info("✅ Coin detail view test passed.");
    }

    @Test
    @WithMockUser
    void showCoinDetails_coinNotFound_throwsException() throws Exception {
        logger.info("🔍 Testing coin not found scenario");

        given(coinRepository.findById(99L)).willThrow(new CoinNotFoundException(99L));

        mockMvc.perform(get("/coins/details/99"))
                .andExpect(status().isNotFound());

        logger.info("✅ Coin not found exception test passed.");
    }
}