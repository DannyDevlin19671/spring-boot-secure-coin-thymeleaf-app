package com.example.coinapp.model;

import jakarta.persistence.*;
import java.util.List;

/**
 * Represents a mint, which is responsible for producing coins.
 */
@Entity
public class Mint {

    /**
     * The unique identifier for the mint.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The country where the mint is located.
     */
    private String country;

    /**
     * The list of coins produced by the mint.
     * This is a one-to-many relationship with the Coin entity.
     */
    @OneToMany(mappedBy = "mint", cascade = CascadeType.ALL)
    private List<Coin> coins;

    /**
     * Default constructor for the Mint class.
     */
    public Mint() {}

    /**
     * Constructs a Mint with the specified country.
     *
     * @param country the country where the mint is located
     */
    public Mint(String country) {
        this.country = country;
    }

    /**
     * Gets the unique identifier of the mint.
     *
     * @return the ID of the mint
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the mint.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the country where the mint is located.
     *
     * @return the country of the mint
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country where the mint is located.
     *
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the list of coins produced by the mint.
     *
     * @return the list of coins
     */
    public List<Coin> getCoins() {
        return coins;
    }

    /**
     * Sets the list of coins produced by the mint.
     *
     * @param coins the list of coins to set
     */
    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }
}
