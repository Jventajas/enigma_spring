package com.jventajas.enigma_spring.enigma;


public class Rotor {
    private final String wiring;
    private final char notch;
    private int position;
    private final int ring;

    public Rotor(String wiring, char notch, char initialPosition, char ringSetting) {
        this.wiring = wiring;
        this.notch = notch;
        this.position = Config.ALPHABET.indexOf(initialPosition);
        this.ring = Config.ALPHABET.indexOf(ringSetting);
    }

    public void rotate() {
        this.position = (this.position + 1) % 26;
    }

    public char encodeForward(char letter) {
        int offset = position - ring;
        int inputIndex = Config.ALPHABET.indexOf(letter);
        int adjustedInputIndex = (inputIndex + offset + 26) % 26;
        char encodedLetter = wiring.charAt(adjustedInputIndex);
        int encodedIndex = Config.ALPHABET.indexOf(encodedLetter);
        int finalIndex = (encodedIndex - offset + 26) % 26;
        return Config.ALPHABET.charAt(finalIndex);
    }

    public char encodeBackward(char letter) {
        int offset = position - ring;
        int inputIndex = Config.ALPHABET.indexOf(letter);
        int adjustedInputIndex = (inputIndex + offset + 26) % 26;
        char adjustedLetter = Config.ALPHABET.charAt(adjustedInputIndex);

        int wiringIndex = wiring.indexOf(adjustedLetter);
        int finalIndex = (wiringIndex - offset + 26) % 26;
        return Config.ALPHABET.charAt(finalIndex);
    }

    public boolean atNotch() {
        return Config.ALPHABET.charAt(position) == notch;
    }
}
