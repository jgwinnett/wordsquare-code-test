package com.glykon.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class Utils {
    public static List<String> loadDictionary() throws IOException {
        Path dictionaryPath = Path.of(Objects.requireNonNull(Utils.class.getClassLoader().getResource("dictionary.txt")).getPath());
        return Files.readAllLines(dictionaryPath);
    }

    public static List<String> reduceSearchSpace(int wordLength, List<String> dictionary, String input) {

        dictionary = reduceDictionarySearchSpaceByLength(wordLength, dictionary);
        dictionary = reduceDictionarySearchSpaceByCharacters(input, dictionary);
        return dictionary;
    }

    private static List<String> reduceDictionarySearchSpaceByLength(int wordLength, List<String> dictionary) {

        dictionary = dictionary.stream().filter( word -> word.length() == wordLength).toList();
        return dictionary;
    }

    private static List<String> reduceDictionarySearchSpaceByCharacters(String input, List<String> dictionary) {

        List<Character> presentChars = input.chars().distinct().mapToObj(ch -> (char) ch).toList();
        StringBuilder regexBuilder = new StringBuilder();
        regexBuilder.append("[");
        for (Character character: presentChars) {
            regexBuilder.append(character);
        }
        regexBuilder.append("]+");
        String regex = regexBuilder.toString();
        dictionary = dictionary.stream().filter(word -> word.matches(regex)).toList();
        return dictionary;
    }

}