package com.jventajas.enigma_spring.enigma;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents the plugboard component of the Enigma machine.
 * The plugboard allows for additional letter substitutions before and after the rotor encryption.
 */
public class Plugboard {
    private final char[] mapping;
    private static final int ALPHABET_SIZE = 26;

    /**
     * Creates a new Plugboard with the specified connections.
     *
     * @param connections List of two-character strings representing letter pairs to be swapped
     * @throws IllegalArgumentException if the connections are invalid
     */
    public Plugboard(List<String> connections) {
        mapping = new char[ALPHABET_SIZE];
        initializeIdentityMapping();

        if (connections == null) {
            throw new IllegalArgumentException("Connections list cannot be null");
        }

        if (!connections.isEmpty()) {
            validateConnections(connections);
            setupConnections(connections);
        }
    }

    private void initializeIdentityMapping() {
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            mapping[i] = Config.ALPHABET.charAt(i);
        }
    }

    private void validateConnections(List<String> connections) {
        Set<Character> usedLetters = new HashSet<>();

        for (String pair : connections) {
            validatePair(pair, usedLetters);
        }
    }

    private void validatePair(String pair, Set<Character> usedLetters) {
        if (pair == null || pair.length() != 2) {
            throw new IllegalArgumentException(
                    "Each plugboard pair must consist of exactly two letters, invalid pair: " + pair);
        }

        char a = pair.charAt(0);
        char b = pair.charAt(1);

        validateLetter(a, "First");
        validateLetter(b, "Second");

        if (a == b) {
            throw new IllegalArgumentException(
                    "Plugboard connections cannot connect a letter to itself: " + pair);
        }

        if (!usedLetters.add(a) || !usedLetters.add(b)) {
            throw new IllegalArgumentException(
                    "Duplicate letter in plugboard connections: " + pair);
        }
    }

    private void validateLetter(char letter, String position) {
        if (Config.ALPHABET.indexOf(letter) == -1) {
            throw new IllegalArgumentException(
                    position + " letter '" + letter + "' is not a valid alphabet character");
        }
    }

    private void setupConnections(List<String> connections) {
        for (String pair : connections) {
            char a = pair.charAt(0);
            char b = pair.charAt(1);
            int idxA = Config.ALPHABET.indexOf(a);
            int idxB = Config.ALPHABET.indexOf(b);
            mapping[idxA] = b;
            mapping[idxB] = a;
        }
    }

    /**
     * Swaps a letter according to the plugboard connections.
     *
     * @param letter The input letter to be swapped
     * @return The swapped letter, or the same letter if no connection exists
     */
    public char swap(char letter) {
        int index = Config.ALPHABET.indexOf(letter);
        if (index == -1) {
            return letter; // Return unchanged if not in alphabet
        }
        return mapping[index];
    }
}