package com.example.coinapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HomeController is responsible for handling requests to the root URL ("/") and the login/logout pages.
 * It checks if the user is authenticated and redirects them accordingly.
 */
@Controller
public class HomeController {

    /**
     * Handles the root URL ("/") and redirects authenticated users to the coins page.
     * If the user is not authenticated, it returns the view name for the landing/login prompt page.
     *
     * @param auth the authentication object representing the current user's authentication state
     * @return a redirect to the coins page if authenticated, or the "home" view name otherwise
     */
    @GetMapping("/")
    public String home(Authentication auth) {
        if (auth != null && auth.isAuthenticated()) {
            return "redirect:/coins";
        }
        return "home"; // the view name for your landing/login prompt page
    }

    /**
     * Handles the "/login" URL and returns the view name for the login page.
     *
     * @return the "login" view name
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout-success")
    public String logoutSuccess() {
        return "logout-success"; // redirect to login page with logout message
    }
}
