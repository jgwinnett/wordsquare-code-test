package com.glykon;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static com.glykon.Utils.loadDictionary;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {

        List<String> validEnglishWords = loadDictionary();

        String input = "eeeeddoonnnsssrv"; // Your input string with repeated characters
        int n = 4; // Desired length of the output strings
        List<String> result = generateStrings(input, n);

        List<String> realWords = result.stream().filter(validEnglishWords::contains).toList();

        for(String word: realWords) {
            System.out.println(word);
        }
    }



    public static List<String> generateStrings(String input, int n) {
        List<String> result = new ArrayList<>();
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : input.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        generateStringsHelper(charCount, n, "", result);
        return result;
    }

    private static void generateStringsHelper(Map<Character, Integer> charCount, int n, String current, List<String> result) {
        if (n == 0) {
            result.add(current);
            return;
        }

        for (char c : charCount.keySet()) {
            int count = charCount.get(c);
            if (count > 0) {
                charCount.put(c, count - 1);
                generateStringsHelper(charCount, n - 1, current + c, result);
                charCount.put(c, count);
            }
        }
    }
}