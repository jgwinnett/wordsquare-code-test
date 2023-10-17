package com.glykon.wordSquare;

import com.glykon.utils.Utils;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class WordSquareSolverTest {

    private WordSquareSolver wordSquare;
    private List<String> dictionary;

    @BeforeEach
    public void setUp() throws IOException {
        dictionary = Utils.loadDictionary();
    }

    @ParameterizedTest
    @MethodSource("provideChallengeIO")
    @Disabled
    public void shouldSolveSquare(int wordLength, String input, List<String> expected)  {
        wordSquare = new WordSquareSolver(wordLength, input, dictionary);

        List<List<String>> results= wordSquare.solveWordSquare();
        List<List<String>> actual = results.stream().filter(wordList -> CollectionUtils.isEqualCollection(wordList, expected)).toList();

        Assertions.assertEquals(actual.size(), 1);
    }

    @ParameterizedTest
    @MethodSource("provideChallengeIO")
    @DisplayName("Word Square Solver should return result set that contains examples from instructions")
    public void shouldSolveSquareWithTrie(int wordLength, String input, List<String> expected)  {
        wordSquare = new WordSquareSolver(wordLength, input, dictionary);

        List<List<String>> results= wordSquare.solveWordSquareWithTrie();
        List<List<String>> actual = results.stream().filter(wordList -> CollectionUtils.isEqualCollection(wordList, expected)).toList();

        Assertions.assertEquals(actual.size(), 1);
    }

    private static Stream<Arguments> provideChallengeIO() {
        return Stream.of(
                Arguments.of(4, "eeeeddoonnnsssrv", List.of("rose", "oven", "send", "ends")),
                Arguments.of(4, "aaccdeeeemmnnnoo", List.of("moan", "once", "acme", "need")),
                Arguments.of(5, "aaaeeeefhhmoonssrrrrttttw", List.of("feast", "earth", "armor", "stone", "threw")),
                Arguments.of(5, "aabbeeeeeeeehmosrrrruttvv", List.of("heart", "ember", "above", "revue", "trees")),
                Arguments.of(7, "aaaaaaaaabbeeeeeeedddddggmmlloooonnssssrrrruvvyyy", List.of("bravado", "renamed", "analogy", "valuers", "amoebas", "degrade", "odyssey"))
                );
    }
}
