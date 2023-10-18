package com.glykon.validators;

import com.glykon.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

    InputValidator validator;

    @BeforeEach
    public void setup() {
        validator = new InputValidator();
    }

    @ParameterizedTest()
    @ValueSource(strings = {"", "  ", "1nowhereismystring", "3 my string"})
    void shouldThrowInvalidInputExceptionIfInputArgsAreNotLengthTwo(String input) {
        InvalidInputException thrown = assertThrows(InvalidInputException.class,
                () -> validator.validateInput(input), "Expected validator to throw a length based exception");

        assertEquals("ERROR: Your input is invalid, please check that it follows the format \"integerN stringOfLengthNSquared\"", thrown.getMessage());
    }

    @ParameterizedTest()
    @ValueSource(strings = {"a abcdefg", "wow input", "% spooky", "three abshbads"})
    void shouldThrowInvalidInputExceptionIfFirstArgumentIsNotAnInteger(String input) {
        InvalidInputException thrown = assertThrows(InvalidInputException.class,
                () -> validator.validateInput(input), "Expected validator to throw an invalid first arg xception");

        assertEquals("ERROR: Your input did not contain a valid number at the start", thrown.getMessage());
    }

    @ParameterizedTest()
    @ValueSource(strings = {"2 abc", "3 abcdef ", "4 abcdefghijkln",})
    void shouldThrowInvalidInputExceptionIfSecondInputIsNotSquareOfFirst(String input) {
        InvalidInputException thrown = assertThrows(InvalidInputException.class,
                () -> validator.validateInput(input), "Expected validator to throw a character length based exception");

        assertEquals("ERROR: your character sequence was the incorrect length", thrown.getMessage());
    }

    @ParameterizedTest()
    @ValueSource(strings = {"2 1abc", "3 abcd1fgyu", "3 abcde%uip", "3 @!$123abc"})
    void shouldThrowInvalidInputExceptionIfSecondArgumentContainsNonAlpha(String input) {
        InvalidInputException thrown = assertThrows(InvalidInputException.class,
                () -> validator.validateInput(input), "Expected validator to throw an invalid character based exception");

        assertEquals("ERROR: your input string contained non alphabet characters", thrown.getMessage());
    }

    @ParameterizedTest()
    @ValueSource(strings = {"2 abcd", "3 abcdefghi", "4 aaaaeeeerrrrtttt"})
    void shouldNotThrowExceptionIdValidInputPassed(String input) {
        assertDoesNotThrow(() -> validator.validateInput(input), "Valid inputs should not throw an exception");
    }


}