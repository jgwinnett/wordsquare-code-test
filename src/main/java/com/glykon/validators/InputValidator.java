package com.glykon.validators;

import com.glykon.exception.InvalidInputException;

public class InputValidator {


    public boolean isValid(String input) throws InvalidInputException {

        String trimmedInput = input.trim();
        boolean valid = true;
        try {
            String[] inputs = trimmedInput.split(" ");
            validateLength(inputs);
            validateFirstArgIsInteger(inputs[0]);
            validateSecondArgIsSquareLength(inputs);
        } catch (InvalidInputException e) {
            valid = false;
        }

        return valid;
    }

    private void validateLength(String[] inputs) throws InvalidInputException {
        if (inputs.length != 2) {
            throw new InvalidInputException("ERROR: Your input is invalid, please check that it follows the format \"integerN stringOfLengthNSquared\"");
        }
    }

    private void validateFirstArgIsInteger(String input0) throws InvalidInputException {
        try {
            Integer.parseInt(input0);
        } catch ( NumberFormatException e) {
            throw new InvalidInputException("ERROR: Your input did not contain a valid number at the start");
        }
    }

    private void validateSecondArgIsSquareLength(String[] inputs ){
        int wordLength = Integer.parseInt(inputs[0]);

        if (inputs[1].length() != Math.pow(wordLength, 2)) {
            throw new InvalidInputException("ERROR: your character sequence was the incorrect length");
        }
    }

}
