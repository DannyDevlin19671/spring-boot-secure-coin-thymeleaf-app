package com.example.coinapp.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Global exception handler for the application.
 * Provides methods to handle specific and generic exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles exceptions of type UsernameNotFoundException.
     * Adds the exception message to the model and returns the "home" view.
     *
     * @param ex    the UsernameNotFoundException thrown
     * @param model the Model to add attributes to
     * @return the name of the view to render
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public String handleUserNotFound(UsernameNotFoundException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "home";
    }

    /**
     * Handles all other exceptions.
     * Adds a generic error message to the model and returns the "home" view.
     *
     * @param ex    the Exception thrown
     * @param model the Model to add attributes to
     * @return the name of the view to render
     */
    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex, Model model) {
        model.addAttribute("error", "An unexpected error occurred.");
        return "home";
    }
}
