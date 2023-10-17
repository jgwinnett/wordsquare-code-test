# Random notes and observations as I go along: 

The abomination 'brute force every permutation of n characters from input string' is CRAZY inefficient (not surprising). 

## Word Square 

Initial implementation uses whole words (in a randomised order) to validate algorithm.  
Test structure assumes only one result (uses findFirst) - iffy but acceptable for test inputs 

## Word Finder 

Experimented with a solution implementation that tried to find all possible sets of valid words (_n_ number of words of _n_ length) that used all the characters in the input sequence exactly once.
Impossible sets were checked and eliminated by summing the character counts of the containing words ; if the total of each char exceeded the total in the input then the combination must be invalid and ignored. 

This does work for the 4-letter words (taking around 10 seconds...) but the time complexity becomes ridiculous once we go larger than that. 

Did attempt a solution where we simplified the number of combinations to be checked against each other by reducing the comparison set to common jumbles of characters (e.g. 'ember' and 'berme' are both valid words with equivelant chars inside them). 
This did reduce the number of comparisons being made quite dramatically but I wasn't receiving correct results.
    -TODO Need to check if this is because I wasn't accounting for 'unique' combinations. 

----

Realised I have been extremely stupid - if I know the search space is too big to be reasonable computationally then I HAVE to try and construct the word square as I go. 

Basic process should be: 

1. Find all valid words, load a Trie with them 
2. Choose a word to start with
3. Use a trie to find all words that begin with the prefix 
4. If any exist, add that word
   4a. If not, remove the word above #
6. repeat

.......

I HAD ALREADY BLEEDING SOLVED IT I JUST NEVER TRIED PUTTING IN THE LIST OF ELIGIBLE WORDS OMG. 

Examples solve time - original: 

548
34
3sec 664
2sec 897 
5min something

Examples solve time - with Tries

564ms
28ms
252ms
143ms
5s 273

-----

Implemented:
    * Solver creates wordsquares
    * Basic service with very basic input validation
    * Exception for bad user input 
    * Runs from CLI (extremely noddy - just scanner )
    
TO-DO:
    * Add tests for WordSquareService and InputValidator (no I didn't TDD these... I was aggy and wanted something functional)
    * Update README 
        - Installation instructions
        - How to run 
        - Screenshot of CLI in action 
    * Cleanup legacy solve (optional);