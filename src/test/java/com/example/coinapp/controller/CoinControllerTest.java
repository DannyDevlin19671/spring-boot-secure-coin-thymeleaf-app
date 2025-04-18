package com.example.coinapp.controller;

import com.example.coinapp.model.Coin;
import com.example.coinapp.model.Mint;
import com.example.coinapp.repository.CoinRepository;
import com.example.coinapp.repository.MintRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CoinController.class)
public class CoinControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoinRepository coinRepository;

    @MockBean
    private MintRepository mintRepository;

    @Test
    public void shouldDisplayListPage() throws Exception {
        Coin coin = new Coin("Test Coin", "Description", "2023", "Silver", "1 oz", new Mint("UK"));
        when(coinRepository.findAll()).thenReturn(Collections.singletonList(coin));

        mockMvc.perform(get("/coins"))
            .andExpect(status().isOk())
            .andExpect(view().name("coins/list"))
            .andExpect(model().attributeExists("coins"));
    }

    @Test
    public void shouldShowAddForm() throws Exception {
        when(mintRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/coins/new"))
            .andExpect(status().isOk())
            .andExpect(view().name("coins/form"))
            .andExpect(model().attributeExists("coin"))
            .andExpect(model().attributeExists("mints"));
    }
}
