package com.glykon.validators;

import com.glykon.exception.InvalidInputException;

public class InputValidator {

    public void validateInput(String input) throws InvalidInputException {
        String trimmedInput = input.trim();
        String[] inputs = trimmedInput.split(" ");
        validateLength(inputs);
        validateFirstArgIsInteger(inputs[0]);
        validateSecondArgIsSquareLength(inputs);
        validateSecondArgumentContainsOnlyAlpha(inputs);
    }

    private void validateLength(String[] inputs) throws InvalidInputException {
        if (inputs.length != 2) {
            throw new InvalidInputException("ERROR: Your input is invalid, please check that it follows the format \"integerN stringOfLengthNSquared\"");
        }
    }

    private void validateFirstArgIsInteger(String input0) throws InvalidInputException {
        try {
            Integer.parseInt(input0);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("ERROR: Your input did not contain a valid number at the start");
        }
    }

    private void validateSecondArgIsSquareLength(String[] inputs) {
        int wordLength = Integer.parseInt(inputs[0]);

        if (inputs[1].length() != Math.pow(wordLength, 2)) {
            throw new InvalidInputException("ERROR: your character sequence was the incorrect length");
        }
    }

    private void validateSecondArgumentContainsOnlyAlpha(String[] inputs) {
        boolean allLetters = inputs[1].chars().allMatch(Character::isLetter);

        if (!allLetters) {
            throw new InvalidInputException("ERROR: your input string contained non alphabet characters");
        }
    }
}
