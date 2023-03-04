package com.mparaske.SpellingWordGame;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import javax.speech.Central;
import javax.speech.EngineException;

public class WordSpeaker {
    public static void speak(String word) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        try {
            Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
        } catch (EngineException e) {
            throw new RuntimeException(e);
        }
        // Convert the word to speech
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        voice.allocate();
        System.out.println("Please write the word you hear: ");
        voice.speak(word);
        voice.deallocate();
    }
}
