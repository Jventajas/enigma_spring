package com.jventajas.enigma_spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EnigmaController {

    @GetMapping("/")
    public String showHomePage(Model model) {
        // Default message for GET request
        model.addAttribute("message", "Hello World!");
        return "home"; // Refers to templates/home.html
    }

    @PostMapping("/")
    public String handlePostRequest(@RequestParam("name") String name, Model model) {
        // Update message dynamically for the POST request
        model.addAttribute("message", "Hello " + name + "!");
        return "home";
    }
}