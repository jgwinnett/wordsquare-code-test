package com.glykon;

import com.glykon.utils.Utils;
import com.glykon.wordSquare.WordSquareService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        List<String> dictionary = Utils.loadDictionary();
        WordSquareService wordSquareService = new WordSquareService(dictionary);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the word square problem solver \n");
        System.out.println("Please enter your input - it should take the format of: ");
        System.out.println("$integerSizeOfSquare $characterSequence \n" );
        System.out.println("Please ensure that your characterSequence is of length $integerSizeOfSquare squared");
        System.out.println("e.g. if you want a square of size 4, your character sequence must be 16 characters long \n");

        String input = scanner.nextLine();

        List<List<String>> wordSquares = wordSquareService.getWordSquaresForInput(input);

        if (wordSquares.size() == 0) {
            System.out.println("No valid word squares were found, exiting");
            scanner.close();
        }

        System.out.printf("\n There are %s valid word squares \n", wordSquares.size() );
        System.out.println("Which square would you like to view?");
        System.out.println("(No funny business please, i've not implemented proper input handling \n");

        String desiredSquare = scanner.nextLine();

        System.out.println("\n");

        wordSquareService.printWordSquare(wordSquares.get(Integer.parseInt(desiredSquare)));

        while (true) {
            System.out.println("\n");
            System.out.println("Would you like to view another square?");
            System.out.println("If so, please enter another integer");
            System.out.println("Any other inputs will cause the program to terminate \n");
            desiredSquare = scanner.nextLine();
            int square;
            try {
                square = Integer.parseInt(desiredSquare);
            } catch (NumberFormatException e) {
                break;
            }
            System.out.println("\n");
            wordSquareService.printWordSquare(wordSquares.get(Integer.parseInt(desiredSquare)));
        }

        scanner.close();
    }
}