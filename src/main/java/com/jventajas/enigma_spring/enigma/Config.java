package com.jventajas.enigma_spring.enigma;

import java.util.HashMap;
import java.util.Map;

public final class Config {
    private Config() {}

    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final Map<String, RotorConfig> ROTOR_CONFIGS = new HashMap<>();
    static {
        ROTOR_CONFIGS.put("I",   new RotorConfig("EKMFLGDQVZNTOWYHXUSPAIBRCJ", "Q"));
        ROTOR_CONFIGS.put("II",  new RotorConfig("AJDKSIRUXBLHWTMCQGZNPYFVOE", "E"));
        ROTOR_CONFIGS.put("III", new RotorConfig("BDFHJLCPRTXVZNYEIWGAKMUSQO", "V"));
        ROTOR_CONFIGS.put("IV",  new RotorConfig("ESOVPZJAYQUIRHXLNFTGKDCMWB", "J"));
        ROTOR_CONFIGS.put("V",   new RotorConfig("VZBRGITYUPSDNHLXAWMJQOFECK", "Z"));
    }

    public static final Map<String, String> REFLECTOR_CONFIGS = new HashMap<>();
    static {
        REFLECTOR_CONFIGS.put("A", "EJMZALYXVBWFCRQUONTSPIKHGD");  // Adding Reflector A
        REFLECTOR_CONFIGS.put("B", "YRUHQSLDPXNGOKMIEBFZCWVJAT");
        REFLECTOR_CONFIGS.put("C", "FVPJIAOYEDRZXWGCTKUQSBNMHL");
    }
}