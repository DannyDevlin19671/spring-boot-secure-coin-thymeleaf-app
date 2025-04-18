package com.example.coinapp.controller;

import com.example.coinapp.exception.CoinNotFoundException;
import com.example.coinapp.model.Coin;
import com.example.coinapp.model.Mint;
import com.example.coinapp.repository.CoinRepository;
import com.example.coinapp.repository.MintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CoinController handles requests related to coins.
 * It provides methods to view, add, edit, and delete coins.
 */
@Controller
@RequestMapping("/coins")
public class CoinController {

    @Autowired
    private CoinRepository coinRepository;

    @Autowired
    private MintRepository mintRepository;

    /**
     * Displays a list of all coins.
     * Redirects to the login page if the user is not authenticated.
     *
     * @param model the model to add attributes to
     * @return the view name for the coin list
     */
    @GetMapping()
    public String viewCoins(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth instanceof AnonymousAuthenticationToken || !auth.isAuthenticated()) {
            return "redirect:/login";
        }
        List<Coin> coins = coinRepository.findAll();
        model.addAttribute("coins", coins);
        return "coins/list";
    }

    /**
     * Displays the form for adding a new coin.
     *
     * @param model the model to add attributes to
     * @return the view name for the coin form
     */
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("coin", new Coin());
        model.addAttribute("mints", mintRepository.findAll());
        return "coins/form";
    }

    /**
     * Saves a new or updated coin to the repository.
     *
     * @param coin the coin to save
     * @return a redirect to the coin list view
     */
    @PostMapping
    public String saveCoin(@ModelAttribute Coin coin) {
        coinRepository.save(coin);
        return "redirect:/coins";
    }

    /**
     * Displays the form for editing an existing coin.
     *
     * @param id    the ID of the coin to edit
     * @param model the model to add attributes to
     * @return the view name for the coin form
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Coin coin = coinRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid coin Id:" + id));
        model.addAttribute("coin", coin);
        model.addAttribute("mints", mintRepository.findAll());
        return "coins/form";
    }

    /**
     * Deletes a coin by its ID.
     *
     * @param id the ID of the coin to delete
     * @return a redirect to the coin list view
     */
    @GetMapping("/delete/{id}")
    public String deleteCoin(@PathVariable Long id) {
        coinRepository.deleteById(id);
        return "redirect:/coins";
    }

    /**
     * Displays the details of a specific coin.
     *
     * @param id    the ID of the coin to view
     * @param model the model to add attributes to
     * @return the view name for the coin details
     */
    @GetMapping("/coins/{id}")
    public String viewCoinDetails(@PathVariable Long id, Model model) {
        Coin coin = coinRepository.findById(id).orElseThrow(() -> new RuntimeException("Coin not found"));
        model.addAttribute("coin", coin);
        return "coins/coin-details";
    }

    /**
     * Displays the details of a specific coin.
     * Throws a custom exception if the coin is not found.
     *
     * @param id    the ID of the coin to view
     * @param model the model to add attributes to
     * @return the view name for the coin details
     */
    @GetMapping("/details/{id}")
    public String showCoinDetails(@PathVariable Long id, Model model) {
        Coin coin = coinRepository.findById(id).orElseThrow(() -> new CoinNotFoundException(id));
        model.addAttribute("coin", coin);
        return "coin-details"; // maps to templates/coins/details.html
    }
}
