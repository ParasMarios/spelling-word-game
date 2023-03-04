package com.mparaske.SpellingWordGame;

public class Word {

    private String word;
    private boolean isCorrect;

    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public void speak() {
        WordSpeaker.speak(word);
    }
}
