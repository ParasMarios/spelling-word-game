package com.mparaske.SpellingWordGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class SpellingWordGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpellingWordGameApplication.class, args);
        Scanner scanner = new Scanner(System.in);

        Game game;
        try {
            game = new Game("src/main/resources/words.txt");
        } catch (IOException e) {
            System.out.println("Error reading file.");
            throw new RuntimeException(e);
        }

        System.out.println("Please enter your nickname: ");
        String nickname = scanner.nextLine();
        System.out.println("Welcome " + nickname + "!");
        System.out.println("Let's start the game!");
        String playAgain = "y";
        while (playAgain.equalsIgnoreCase("y")) {
            int totalWordsInFile = game.getTotalWords();
            System.out.println("How many words would you like to practice? (Maximum: " + totalWordsInFile + ")");
            int numberOfWords = scanner.nextInt();

            if (numberOfWords > totalWordsInFile) {
                System.out.println("You can't practice more words than available in the file.");
                continue;  // Go back to the beginning of the loop
            }

            game.play(numberOfWords);

            System.out.println("Would you like to play again? (y/n)");
            playAgain = scanner.next();
        }
        scanner.close();
        System.exit(0);
    }
}

