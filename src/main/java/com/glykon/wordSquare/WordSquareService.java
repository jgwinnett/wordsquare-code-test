package com.glykon.wordSquare;

import com.glykon.exception.InvalidInputException;
import com.glykon.validators.InputValidator;

import java.util.List;

public class WordSquareService {

    List<String> dictionary;
    InputValidator inputValidator;

    public WordSquareService( List<String> dictionary) {
        this.dictionary = dictionary;
        this.inputValidator = new InputValidator();
    }

    public List<List<String>> getWordSquaresForInput(String input) throws Exception {

        String[] inputs = handleInput(input);
        WordSquareSolver wordSquareSolver = new WordSquareSolver(Integer.parseInt(inputs[0]), inputs[1], dictionary);
        return wordSquareSolver.solveWordSquareWithTrie();
    }

    public void printWordSquare(List<String> square) {

        for (String word: square) {
            System.out.println(word);
        }
    }

    private String[] handleInput(String input) throws Exception {
        String trimmedInput = input.trim();
        boolean valid = inputValidator.isValid(trimmedInput);
        if (!valid) throw new Exception("Input is invalid");
        return input.split(" ");
    }

}
