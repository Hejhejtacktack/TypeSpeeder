package se.ju23.typespeeder.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SymbolGenerator implements Generator<Character> {
    String difficulty;
    List<Character> symbols;

    public SymbolGenerator(String difficulty) {
        this.difficulty = difficulty;
        this.symbols = new ArrayList<>();
        populateList();
    }

    @Override
    public String generate() {
        int counter = 0;
        StringBuilder stringBuilder = new StringBuilder();

        switch (this.difficulty) {
            case "1" -> counter = 3;
            case "2" -> counter = 6;
            case "3" -> counter = 12;
        }

        for (int i = 0; i < counter; i++) {
            stringBuilder.append(getRandomElement(this.symbols));
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
        for (Character c : "!@#$%^&*()-_=+[]{}|;:'\"<>,.?/`~".toCharArray()) {
            this.symbols.add(c);
        }
    }
}
