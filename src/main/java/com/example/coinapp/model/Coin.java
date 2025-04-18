package com.example.coinapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Represents a Coin entity with various attributes such as name, description, mint, price, metal type,
 * year of minting, fineness, and weight. This class is mapped to a database table using JPA annotations.
 */
@Entity
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the coin. This field is mandatory.
     */
    @NotBlank(message = "Name is required")
    private String name;

    /**
     * A brief description of the coin.
     */
    private String description;

    /**
     * The mint where the coin was produced. This field is mandatory and is a many-to-one relationship.
     */
    @ManyToOne
    @JoinColumn(name = "mint_id", nullable = false)
    @NotNull(message = "Mint is required")
    private Mint mint;

    /**
     * The price of the coin. Must be greater than 0.
     */
    @Positive(message = "Price must be greater than 0")
    private double price;

    /**
     * The type of metal the coin is made of. This field is mandatory.
     */
    @NotBlank(message = "Metal type is required")
    private String metal;

    /**
     * The year the coin was minted.
     */
    @Column(name = "coin_year")
    private Integer coinYear;

    /**
     * The fineness of the coin's metal. Must be greater than 0.
     */
    @Positive(message = "Fineness must be greater than 0")
    private double fineness;

    /**
     * The weight of the coin. Must be greater than 0.
     */
    @Positive(message = "Weight must be greater than 0")
    private double weight;

    /**
     * Default constructor for the Coin class.
     */
    public Coin() {
    }

    /**
     * Constructs a Coin with the specified attributes.
     *
     * @param name        the name of the coin
     * @param description a brief description of the coin
     * @param mint        the mint where the coin was produced
     * @param price       the price of the coin
     * @param metal       the type of metal the coin is made of
     * @param coinYear    the year the coin was minted
     * @param fineness    the fineness of the coin's metal
     * @param weight      the weight of the coin
     */
    public Coin(String name, String description, Mint mint, double price, String metal, Integer coinYear, double fineness, double weight) {
        this.name = name;
        this.description = description;
        this.mint = mint;
        this.price = price;
        this.metal = metal;
        this.coinYear = coinYear;
        this.fineness = fineness;
        this.weight = weight;
    }

    // Getters and setters

    /**
     * Gets the ID of the coin.
     *
     * @return the ID of the coin
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the coin.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the coin.
     *
     * @return the name of the coin
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the coin.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the coin.
     *
     * @return the description of the coin
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the coin.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the mint where the coin was produced.
     *
     * @return the mint of the coin
     */
    public Mint getMint() {
        return mint;
    }

    /**
     * Sets the mint where the coin was produced.
     *
     * @param mint the mint to set
     */
    public void setMint(Mint mint) {
        this.mint = mint;
    }

    /**
     * Gets the price of the coin.
     *
     * @return the price of the coin
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the coin.
     *
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the type of metal the coin is made of.
     *
     * @return the metal type of the coin
     */
    public String getMetal() {
        return metal;
    }

    /**
     * Sets the type of metal the coin is made of.
     *
     * @param metal the metal type to set
     */
    public void setMetal(String metal) {
        this.metal = metal;
    }

    /**
     * Gets the year the coin was minted.
     *
     * @return the year of the coin
     */
    public Integer getCoinYear() {
        return coinYear;
    }

    /**
     * Sets the year the coin was minted.
     *
     * @param coinYear the year to set
     */
    public void setCoinYear(Integer coinYear) {
        this.coinYear = coinYear;
    }

    /**
     * Gets the fineness of the coin's metal.
     *
     * @return the fineness of the coin
     */
    public double getFineness() {
        return fineness;
    }

    /**
     * Sets the fineness of the coin's metal.
     *
     * @param fineness the fineness to set
     */
    public void setFineness(double fineness) {
        this.fineness = fineness;
    }

    /**
     * Gets the weight of the coin.
     *
     * @return the weight of the coin
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the coin.
     *
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }
}