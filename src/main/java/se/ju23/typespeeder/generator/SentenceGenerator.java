package se.ju23.typespeeder.generator;

import se.ju23.typespeeder.exception.ChallengeException;

import java.util.List;
import java.util.Random;

public class SentenceGenerator implements Generator<String> {

    String difficulty;
    List<String> subjects = List.of("A cat", "A dog", "Your boyfriend", "A sheep", "The baker", "A lion", "An elephant");
    List<String> verbs = List.of("ate", "hugged", "slapped", "scared", "chased", "married", "climbed");
    List<String> objects = List.of("a car", "its owner", "a mailbox", "a can", "a policeman", "a bicycle", "the mailman");
    List<String> adverbials = List.of("in the office", "at home", "at the park", "in a fountain", "in anger", "at the supermarket", "during christmas");

    public SentenceGenerator(String difficulty) throws ChallengeException {
        if (difficulty.matches("[1-3]")) {
            this.difficulty = difficulty;
        } else {
            throw new ChallengeException("Difficulty must be Easy, Medium or Hard");
        }
    }

    @Override
    public String getDifficulty() {
        return this.difficulty;
    }

    @Override
    public String generate() {
        StringBuilder sentence = new StringBuilder();

        String subject = this.getRandomElement(this.subjects);
        String verb = this.getRandomElement(this.verbs);
        String object = this.getRandomElement(this.objects);
        String adverbial = this.getRandomElement(this.adverbials);

        sentence.append(subject).append(" ");
        sentence.append(verb).append(" ");
        sentence.append(object).append(" ");
        sentence.append(adverbial);

        switch (this.difficulty) {
            case "1" -> {
                return sentence.toString().trim();
            }
            case "2" -> {
                return capitalizeRandomCharacter(sentence).trim();
            }
            case "3" -> {
                return capitalizeRandomCharacter(sentence.reverse()).trim();
            }
            default -> {
                return "";
            }
        }
    }

    @Override
    public String getRandomElement(List<String> list) {
        Random random = new Random();
        int index = random.nextInt(list.size());
        return list.get(index);
    }

    private String capitalizeRandomCharacter(StringBuilder sentence) {
        Random random = new Random();

        for (int i = 0; i < sentence.length(); i++) {
            char character = sentence.charAt(i);
            if (Character.isLetter(character) && random.nextBoolean()) {
                sentence.setCharAt(i, Character.toUpperCase(character));
            }
        }
        return sentence.toString();
    }
}
