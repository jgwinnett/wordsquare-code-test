package com.glykon.wordSquare;

import com.glykon.utils.Utils;
import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordSquareSolver {

    private final List<List<String>> results;
    private final List<String> validWords;
    private final int wordLength;

    public WordSquareSolver(int wordLength, String input, List<String> dictionary) {
        this.results = new ArrayList<>();
        this.wordLength = wordLength;
        this.validWords = Utils.reduceSearchSpace(wordLength, dictionary, input);
    }

    @Deprecated
    public List<List<String>> solveWordSquare() {
        wordSquareBacktrack(0, new ArrayList<>(), validWords);
        return results;
    }

    public List<List<String>> solveWordSquareWithTrie() {

        Trie<String, String> trie = new PatriciaTrie<>();
        validWords.forEach(word -> trie.put(word, word));

        wordSquareBacktrackWithTrie(0, new ArrayList<>(), validWords, trie);
        return results;
    }

    @Deprecated
    private void wordSquareBacktrack(int currentRow, List<String> wordSquareList, List<String> wordsLeft) {

        // if the length of wordSquareList is the same as the length of the word then we must be done and can exit
        if (currentRow == wordLength) {
            results.add(new ArrayList<>(wordSquareList));
        } else {
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

    private void wordSquareBacktrackWithTrie(int currentRow, List<String> wordSquareList, List<String> wordsLeft, Trie<String, String> dict) {

        // if the length of wordSquareList is the same as the length of the word then we must be done and can exit
        if (currentRow == wordLength) {
            results.add(new ArrayList<>(wordSquareList));
        } else {
            StringBuilder prefix = new StringBuilder();
            for (String s : wordSquareList) {
                prefix.append(s.charAt(currentRow));
            }

            Map<String, String> eligibleWords = dict.prefixMap(String.valueOf(prefix));
            Set<String> words = eligibleWords.keySet();

            for (String word : words) {
                wordSquareList.add(word);
                List<String> reducedWords = new ArrayList<>(words);
                reducedWords.remove(word);
                wordSquareBacktrackWithTrie(currentRow + 1, wordSquareList, reducedWords, dict);
                wordSquareList.removeLast();
            }
        }
    }

    public List<List<String>> getResults() {
        return results;
    }
}
