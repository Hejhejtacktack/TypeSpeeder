package se.ju23.typespeeder.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.exception.ChallengeException;
import se.ju23.typespeeder.service.LocaleServiceImpl;
import se.ju23.typespeeder.service.MessageBundle;
import se.ju23.typespeeder.service.WordProvider;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class SentenceGenerator implements Generator<String> {

    String difficulty;
    MessageBundle messageBundle;
    LocaleServiceImpl localeService;
    List<String> subjects;
    List<String> verbs;
    List<String> objects;
    List<String> adverbials;

    public SentenceGenerator(String difficulty) throws ChallengeException {
        if (difficulty.matches("[1-3]")) {
            this.difficulty = difficulty;
            this.messageBundle = new MessageBundle(Locale.getDefault());
            this.localeService = new LocaleServiceImpl(messageBundle);
        } else {
            throw new ChallengeException("Difficulty must be Easy, Medium or Hard");
        }
    }

    public SentenceGenerator() {
    }

    @Override
    public String getDifficulty() {
        return this.difficulty;
    }

    @Override
    public String generate() {
        populateList();
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

    @Override
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
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

    private void populateList() {
        subjects = Arrays.asList(this.messageBundle.getMessage("generator.subjects").split(", "));
        verbs = Arrays.asList(this.messageBundle.getMessage("generator.verbs").split(", "));
        objects = Arrays.asList(this.messageBundle.getMessage("generator.objects").split(", "));
        adverbials = Arrays.asList(this.messageBundle.getMessage("generator.adverbials").split(", "));
    }
}
