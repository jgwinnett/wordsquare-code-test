package com.glykon.wordSquare;

import com.glykon.exception.InvalidInputException;
import com.glykon.validators.InputValidator;

import java.util.List;

public class WordSquareService {

    private final List<String> dictionary;
    private final InputValidator inputValidator;

    private final WordSquareSolverFactory wordSquareSolverFactory;

    public WordSquareService(List<String> dictionary, InputValidator inputValidator, WordSquareSolverFactory wordSquareSolverFactory) {
        this.dictionary = dictionary;
        this.inputValidator = inputValidator;
        this.wordSquareSolverFactory = wordSquareSolverFactory;
    }

    public List<List<String>> getWordSquaresForInput(String input) throws Exception {
        String[] inputs = handleInput(input);
        WordSquareSolver wordSquareSolver = wordSquareSolverFactory.buildWordSquareSolver(inputs, dictionary);
        return wordSquareSolver.solveWordSquareWithTrie();
    }

    public void printWordSquare(List<String> square) {
        for (String word : square) {
            System.out.println(word);
        }
    }

    private String[] handleInput(String input) throws Exception {
        try {
            inputValidator.validateInput(input);
        } catch (InvalidInputException invalidInputException) {
            throw new Exception(invalidInputException.getMessage());
        }
        return input.split(" ");
    }
}
