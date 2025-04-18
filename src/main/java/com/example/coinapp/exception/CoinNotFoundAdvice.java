package com.example.coinapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class provides global exception handling for the application.
 * It specifically handles the `CoinNotFoundException` and returns
 * a user-friendly error page.
 */
@ControllerAdvice
public class CoinNotFoundAdvice {

    /**
     * Handles `CoinNotFoundException` by adding an error message to the model
     * and returning the path to the error page.
     *
     * @param ex    the exception that was thrown
     * @param model the model to which the error message is added
     * @return the path to the error page
     */
    @ExceptionHandler(CoinNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleCoinNotFound(CoinNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/coin-not-found"; // you'll create this HTML page next
    }
}
