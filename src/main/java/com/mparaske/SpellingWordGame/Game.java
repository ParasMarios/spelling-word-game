package com.mparaske.SpellingWordGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Game {

    private final List<Word> words;
    private final Set<Integer> usedIndices;
    private final Random random;

    public Game(String fileName) throws IOException {
        words = new ArrayList<>();
        usedIndices = new HashSet<>();
        random = new Random();

        BufferedReader reader = new BufferedReader(new java.io.FileReader(fileName));
        String line;

        while ((line = reader.readLine()) != null) {
            words.add(new Word(line));
        }

        reader.close();
    }

    public int getTotalWords() {
        return words.size();
    }

    public void play(int numberOfWords) {
        if (numberOfWords > words.size()) {
            throw new IllegalArgumentException("Number of words requested exceeds the total number of words.");
        }

        Scanner scanner = new Scanner(System.in);
        int correctWords = 0;
        int points = 0;

        for (int i = 0; i < numberOfWords; i++) {
            int randomIndex;
            do {
                randomIndex = random.nextInt(words.size());
            } while (usedIndices.contains(randomIndex));

            usedIndices.add(randomIndex);

            Word randomWord = words.get(randomIndex);

            // Allow the user to hear the word again with a limit of 3 retries
            int retries = 3;
            boolean correctInput = false;
            do {
                randomWord.speak();
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase(randomWord.getWord())) {
                    randomWord.setCorrect(true);
                    correctWords++;
                    points += 10;
                    System.out.println("Correct!");
                    System.out.println("Points: " + points);
                    correctInput = true; // Exit the loop
                } else {
                    retries--;
                    if (retries > 0) {
                        System.out.println("Would you like to hear the word again? (y/n)");
                        String hearAgainInput = scanner.nextLine();
                        if (hearAgainInput.equalsIgnoreCase("y")) {
                            System.out.println("Remaining retries: " + retries);
                        } else {
                            correctInput = true; // Exit the loop
                        }
                    } else {
                        System.out.println("Out of retries. The correct word was: " + randomWord.getWord());
                        correctInput = true; // Exit the loop
                    }
                }
            } while (!correctInput);
        }

        usedIndices.clear(); // Clear used indices for the next game
        System.out.println("Game Over!");
        System.out.println("You got " + correctWords + " out of " + numberOfWords + " correct.");
    }
}