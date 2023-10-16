package com.glykon;

import java.util.ArrayList;
import java.util.List;

public class WordSquare {

    List<List<String>> results;
    List<String> inputWords;

    public List<String> getWords(String input) {
        return List.of(input);
    }

    public List<List<String>> solveWordSquare(List<String> inputWords) {
        this.results = new ArrayList<>();
        this.inputWords = inputWords;
        wordSquareBacktrack(0, new ArrayList<>(), inputWords);
        return results;
    }

    private void wordSquareBacktrack(int currentRow, List<String> wordSquareList, List<String> wordsLeft) {

        // if the length of wordSquareList is the same as the length of the word then we must be done and can exit
        if (currentRow == inputWords.get(0).length()) {
            results.add(new ArrayList<>(wordSquareList));
        }
        else {
            // no prefix if this is the first word in the square
            StringBuilder prefix = new StringBuilder();
            // if it's the second word then we know it must start with the 2nd character of the original word
            // if it's the third word then it must begin with the 3rd char of w1 + the 3rd char of w2, etc.
            for (String s : wordSquareList) {
                prefix.append(s.charAt(currentRow));
            }

            for (String word : wordsLeft) {
                if (word.startsWith(String.valueOf(prefix))) {
                    wordSquareList.add(word);
                    List<String> reducedWords = wordsLeft.stream().filter(w -> !w.equals(word)).toList();
                    wordSquareBacktrack(currentRow + 1, wordSquareList, reducedWords);
                    wordSquareList.removeLast();
                }
            }
        }
    }

    }
