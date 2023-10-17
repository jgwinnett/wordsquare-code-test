package com.glykon;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.trie.PatriciaTrie;

import java.io.IOException;
import java.util.*;

import static com.glykon.Utils.loadDictionary;

public class WordFinder {

    List<String> validEnglishWords;
    Set<List<String>> validWordCombinations = new HashSet<>();
    HashMap<String, Map<Character, Integer>> wordCountChars = new HashMap<>();

    Map<Character, Integer> inputStringCharCount;

    public WordFinder() throws IOException {
        this.validEnglishWords = loadDictionary();
    }

    public Set<List<String>> findValidWordsOfLength(int length, String input) {
        reduceDictionarySearchSpaceByLength(length);
        reduceDictionarySearchSpaceByCharacters(input);

        inputStringCharCount = countCharsInInput(input);

        // Populate map so that each word has a Map of character counts in it
        for( String word: validEnglishWords) {
            wordCountChars.put(word, countCharsInInput(word));
        }

        // Filter words that by themselves break the character max
        Iterator<Map.Entry<String, Map<Character, Integer>>> wordIterator = wordCountChars.entrySet().iterator();

        while (wordIterator.hasNext()) {
            Map.Entry<String, Map<Character, Integer>> entry = wordIterator.next();
            Map<Character, Integer> val = entry.getValue();

            for (char c: val.keySet()) {
                if  ( val.get(c) > inputStringCharCount.get(c))  {
                    wordIterator.remove();
                    break;
                }
            }
        }

        validTotalCountBacktrack(length, wordCountChars.keySet(),  new ArrayList<>() );

        return validWordCombinations;

    }
    private void validTotalCountBacktrack(int targetLength, Set<String> keys, List<String> currentSet) {

        // if we have a list which hits the target length we are done
        if (currentSet.size() == targetLength ) {
            List<String> toAdd = new ArrayList<>(currentSet);
            Collections.sort(toAdd);
            validWordCombinations.add(toAdd);
        }
        else {
            for (String key: keys) {
                //get the char count
                Map<Character, Integer> val = wordCountChars.get(key);

                // add the char counts to the total
                String joined = String.join("", currentSet);
                Map<Character, Integer> totals = countCharsInInput(joined);

                boolean valid = true;
                for (char c: val.keySet()) {
                    int charCount = val.get(c);
                    totals.put(c, totals.getOrDefault(c, 0) + charCount);

                    // if this total puts us over the num of that char in the input string, undo and break so we can move onto next word
                    if (totals.get(c) > inputStringCharCount.get(c)) {
                        valid = false;
                        break;
                    }
                }

                if (valid) {
                    currentSet.add(key);
                    Set<String> unused = new HashSet<>(keys);
                    unused.remove(key);
                    validTotalCountBacktrack(targetLength, unused, currentSet);
                    currentSet.removeLast();
                }
            }
        }
    }

    private Map<Character, Integer> countCharsInInput(String input) {
        Map<Character, Integer> charCount = new HashMap<>();

        for (char c: input.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        return charCount;
    }

    private void reduceDictionarySearchSpaceByLength(int wordLength) {
        validEnglishWords = validEnglishWords.stream().filter( word -> word.length() == wordLength).toList();
    }

    private void reduceDictionarySearchSpaceByCharacters(String input) {

        List<Character> presentChars = input.chars().distinct().mapToObj(ch -> (char) ch).toList();
        StringBuilder regexBuilder = new StringBuilder();
        regexBuilder.append("[");
        for (Character character: presentChars) {
            regexBuilder.append(character);
        }
        regexBuilder.append("]+");
        String regex = regexBuilder.toString();
        validEnglishWords = validEnglishWords.stream().filter(word -> word.matches(regex)).toList();
    }

}
