package com.jventajas.enigma_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class EnigmaController {
    private final EnigmaService enigmaService;

    @Autowired
    public EnigmaController(EnigmaService enigmaService) {
        this.enigmaService = enigmaService;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("plaintext", "");
        model.addAttribute("ciphertext", "");
        model.addAttribute("error", null);
        model.addAttribute("letters", getLetters());
        return "home";
    }

    @PostMapping("/")
    public String handleEncryption(
            @RequestParam("plaintext") String plaintext,
            @RequestParam("left_rotor") String leftRotor,
            @RequestParam("center_rotor") String centerRotor,
            @RequestParam("right_rotor") String rightRotor,
            @RequestParam("left_initial_position") String leftPosition,
            @RequestParam("center_initial_position") String centerPosition,
            @RequestParam("right_initial_position") String rightPosition,
            @RequestParam("left_ring_setting") String leftRing,
            @RequestParam("center_ring_setting") String centerRing,
            @RequestParam("right_ring_setting") String rightRing,
            @RequestParam("reflector") String reflector,
            @RequestParam(value = "plugboard", required = false) String plugboard,
            Model model) {
        try {
            String ciphertext = enigmaService.encryptMessage(
                    plaintext,
                    leftRotor, centerRotor, rightRotor,
                    leftPosition, centerPosition, rightPosition,
                    leftRing, centerRing, rightRing,
                    reflector,
                    plugboard
            );

            model.addAttribute("plaintext", plaintext);
            model.addAttribute("ciphertext", ciphertext);
            model.addAttribute("error", null);

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("plaintext", plaintext);
            model.addAttribute("ciphertext", "");
        }

        model.addAttribute("letters", getLetters());
        return "home";
    }

    private List<String> getLetters() {
        return Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
    }
}