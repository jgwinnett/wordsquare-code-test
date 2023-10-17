package com.glykon;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class WordFinderTest {

    private WordFinder wordFinder;
    @BeforeEach
    void setUp() throws IOException {
        wordFinder = new WordFinder();
    }

    @ParameterizedTest
    @MethodSource("inputs")
    void shouldFindExpectedWordsFromRandomString(int length, String input, List<String> expectedWords) {

        Set<List<String>> foundWords = wordFinder.findValidWordsOfLength(length, input);

        List<List<String>> actual = foundWords.stream().filter(wordList -> CollectionUtils.isEqualCollection(wordList, expectedWords)).toList();
        assertTrue(actual.size() == 1);
        assertTrue(actual.getFirst().containsAll(expectedWords));
    }

    private static Stream<Arguments> inputs() {
        return Stream.of(
                Arguments.of(4, "eeeeddoonnnsssrv", List.of("rose", "oven", "send", "ends")),
            Arguments.of(4, "aaccdeeeemmnnnoo", List.of("moan", "once", "acme", "need"))
//                Arguments.of(5, "aabbeeeeeeeehmosrrrruttvv", List.of("heart", "ember", "above", "revue", "trees"))
//                Arguments.of(5, "aaaeeeefhhmoonssrrrrttttw", List.of("feast", "earth", "armor", "stone", "threw"))
//            Arguments.of(7, "aaaaaaaaabbeeeeeeedddddggmmlloooonnssssrrrruvvyyy", List.of("bravado", "renamed", "analogy", "valuers", "amoebas", "degrade", "odyssey"))
      );
    }
}