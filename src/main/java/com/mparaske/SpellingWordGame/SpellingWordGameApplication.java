package com.mparaske.SpellingWordGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SpellingWordGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpellingWordGameApplication.class, args);

        Game game;
        try {
            game = new Game("C:\\Users\\mario\\Programming\\SpellingWordGame\\SpellingWordGame\\src\\main\\resources\\words.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        game.play();
    }

}
