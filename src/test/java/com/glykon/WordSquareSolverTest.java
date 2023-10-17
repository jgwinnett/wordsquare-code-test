package com.glykon;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WordSquareSolverTest {

    private WordSquareSolver wordSquare;

    @ParameterizedTest
    @MethodSource("provideChallengeIO")
    @Disabled
    public void shouldSolveSquare(int wordLength, String input, List<String> expected) throws IOException {
        wordSquare = new WordSquareSolver(input, wordLength);

        List<List<String>> results= wordSquare.solveWordSquare();
        List<List<String>> actual = results.stream().filter(wordList -> CollectionUtils.isEqualCollection(wordList, expected)).toList();

        Assertions.assertEquals(actual.size(), 1);
    }

    @ParameterizedTest
    @MethodSource("provideChallengeIO")
    public void shouldSolveSquareWithTrie(int wordLength, String input, List<String> expected) throws IOException {
        wordSquare = new WordSquareSolver(input, wordLength);

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
