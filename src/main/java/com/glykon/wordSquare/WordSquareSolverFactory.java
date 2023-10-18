package com.glykon.wordSquare;

import java.util.List;

public class WordSquareSolverFactory {

    public WordSquareSolverFactory() {
    }

    public WordSquareSolver buildWordSquareSolver(String[] inputs, List<String> dict) {
        return new WordSquareSolver(Integer.parseInt(inputs[0]), inputs[1], dict);
    }
}
