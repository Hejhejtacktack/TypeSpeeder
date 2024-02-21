package se.ju23.typespeeder.generator;

import se.ju23.typespeeder.exception.ChallengeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CharacterGenerator implements Generator<Character> {

    String difficulty;
    List<Character> characters;

    public CharacterGenerator(String difficulty) throws ChallengeException {
        if (difficulty.matches("[1-3]")) {
            this.difficulty = difficulty;
        } else {
            throw new ChallengeException("Difficulty must be Easy, Medium or Hard");
        }
        this.characters = new ArrayList<>();
        populateList();
    }

    @Override
    public String generate() {
        int counter = 0;
        StringBuilder stringBuilder = new StringBuilder();

        switch (this.difficulty) {
            case "1" -> counter = 5;
            case "2" -> counter = 10;
            case "3" -> counter = 15;
        }

        for (int i = 0; i < counter; i++) {
            stringBuilder.append(getRandomElement(this.characters));
        }

        return stringBuilder.toString();
    }

    @Override
    public Character getRandomElement(List<Character> list) {
        Random random = new Random();
        int index = random.nextInt(list.size());
        return list.get(index);
    }

    private void populateList() {
        for (char character = 'a'; character <= 'z'; character++) {
            characters.add(character);
        }

        for (char character = 'A'; character <= 'Z'; character++) {
            characters.add(character);
        }
    }
}
