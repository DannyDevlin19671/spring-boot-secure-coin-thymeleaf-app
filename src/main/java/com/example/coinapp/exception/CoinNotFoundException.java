package com.example.coinapp.exception;

/**
 * Exception thrown when a coin with the specified ID is not found.
 */
public class CoinNotFoundException extends RuntimeException {

    /**
     * Constructs a new CoinNotFoundException with the specified coin ID.
     *
     * @param id the ID of the coin that could not be found
     */
    public CoinNotFoundException(Long id) {
        super("Could not find coin " + id);
    }
}
