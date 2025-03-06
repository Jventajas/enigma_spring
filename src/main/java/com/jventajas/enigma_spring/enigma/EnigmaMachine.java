package com.jventajas.enigma_spring.enigma;


public class EnigmaMachine {
    private final Rotor[] rotors;
    private final Reflector reflector;
    private final Plugboard plugboard;

    public EnigmaMachine(Rotor[] rotors, Reflector reflector, Plugboard plugboard) {
        if (rotors.length != 3) {
            throw new IllegalArgumentException("Exactly three rotors are required.");
        }
        this.rotors = rotors;
        this.reflector = reflector;
        this.plugboard = plugboard;
    }

    public void stepRotors() {
        Rotor left = rotors[0];
        Rotor middle = rotors[1];
        Rotor right = rotors[2];

        boolean middleAtNotch = middle.atNotch();
        boolean rightAtNotch = right.atNotch();

        if (middleAtNotch) {
            left.rotate();
        }

        if (middleAtNotch || rightAtNotch) {
            middle.rotate();
        }

        right.rotate();
    }

    public String process(String text) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);
            if (Config.ALPHABET.indexOf(letter) == -1) {
                output.append(letter);
                continue;
            }

            stepRotors();

            // Pass through plugboard
            letter = plugboard.swap(letter);

            // Forward through rotors (right to left)
            for (int r = 2; r >= 0; r--) {
                letter = rotors[r].encodeForward(letter);
            }

            // Reflect
            letter = reflector.reflect(letter);

            // Backward through rotors (left to right)
            for (int r = 0; r < 3; r++) {
                letter = rotors[r].encodeBackward(letter);
            }

            // enigma.Plugboard again
            letter = plugboard.swap(letter);

            output.append(letter);
        }
        return output.toString();
    }
}