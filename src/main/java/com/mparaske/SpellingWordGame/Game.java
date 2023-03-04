package com.mparaske.SpellingWordGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private final List<Word> words;
    private final Random random;

    public Game(String fileName) throws IOException {
        words = new ArrayList<>();
        random = new Random();

        BufferedReader reader = new BufferedReader(new java.io.FileReader(fileName));
        String line;

        while ((line = reader.readLine()) != null) {
            words.add(new Word(line));
        }

        reader.close();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        for (Word word : words) {
            Word randomWord = words.get(random.nextInt(words.size()));
            randomWord.speak();

            String input = scanner.nextLine();

            if (input.equalsIgnoreCase(randomWord.getWord())) {
                randomWord.setCorrect(true);
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect.");
            }
        }
        scanner.close();
    }
}
