package com.jventajas.enigma_spring.enigma;

public class Reflector {
    private final char[] mapping = new char[26];

    public Reflector(String wiring) {
        for (int i = 0; i < 26; i++) {
            mapping[i] = wiring.charAt(i);
        }
    }

    public char reflect(char letter) {
        return mapping[Config.ALPHABET.indexOf(letter)];
    }
}
