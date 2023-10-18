# Word Square Code Test 

## Problem statement 

Given an input, consisting of a target length and a sequence of characters of length^2, produce a valid [Word Square](https://en.wikipedia.org/wiki/Word_square).

It was unclear from the problem whether it wanted _a_ word square or all of them. I opted for the latter. 

## Pre-requisites 

You will need the following 

* Java 21 
* Maven 

## Installation 

Run `mvn install` - this will generate two JARs in the `/target` directory - `word-square-test-1.0.jar` and `word-square-test-1.0-jar-with-dependencies.jar` 

## Running the program from the command line 

### Via Maven

Ensure the code has been compiled, either as a lifecycle stage of (`mvn install`) or explicitly via `mvn compile`

Run `mvn exec:java` 

### Via Java

Ensure you have installed the package and are in the project directory (i.e. you can see the `target` folder)
🫠
Run `java -jar target/word-square-test-1.0-jar-with-dependencies.jar` 

You must run the 'jar-with-dependencies' else the application will keel over when it tries to construct an Apache Commons Trie.

### Proof of functionality, miscellaneous notes

The following screenshot demonstrates the program being run against a 7 letter wordsquare input (my apologies for the terminal opacity)

![An image demonstrating the output of the program, with two valid word squares displayed](./media/functional.png?raw=true "Optional Title")

The [docs](docs) folder contains some text notes detailing some initial planning thoughts and a rough log of my thoughts as I went through implementation. 