package com.jventajas.enigma_spring;

import com.jventajas.enigma_spring.enigma.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EnigmaService {

    public String encryptMessage(String plaintext,
                                 String leftRotorType, String centerRotorType, String rightRotorType,
                                 String leftPosition, String centerPosition, String rightPosition,
                                 String leftRing, String centerRing, String rightRing,
                                 String reflectorType, String plugboardConfig) {

        try {
            Rotor[] rotors = new Rotor[]{
                    createRotor(leftRotorType, leftPosition.charAt(0), leftRing.charAt(0)),
                    createRotor(centerRotorType, centerPosition.charAt(0), centerRing.charAt(0)),
                    createRotor(rightRotorType, rightPosition.charAt(0), rightRing.charAt(0))
            };

            Reflector reflector = createReflector(reflectorType);
            Plugboard plugboard = createPlugboard(plugboardConfig);
            EnigmaMachine machine = new EnigmaMachine(rotors, reflector, plugboard);
            return machine.process(plaintext.toUpperCase());

        } catch (Exception e) {
            throw new RuntimeException("Failed to encrypt message: " + e.getMessage(), e);
        }
    }

    private Rotor createRotor(String type, char initialPosition, char ringSetting) {
        RotorConfig config = Config.ROTOR_CONFIGS.get(type);
        return new Rotor(config.wiring(), config.notch().charAt(0),
                Character.toUpperCase(initialPosition),
                Character.toUpperCase(ringSetting));
    }

    private Reflector createReflector(String type) {
        String wiring = Config.REFLECTOR_CONFIGS.get(type);
        return new Reflector(wiring);
    }

    private Plugboard createPlugboard(String config) {
        if (config == null || config.trim().isEmpty()) {
            return new Plugboard(Collections.emptyList());
        }
        List<String> pairs = Arrays.asList(config.trim().toUpperCase().split("\\s+"));
        return new Plugboard(pairs);
    }
}