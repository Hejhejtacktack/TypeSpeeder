package se.ju23.typespeeder.generator;

import java.util.List;

public interface Generator<T> {
    String getDifficulty();
    String generate();
    T getRandomElement(List<T> list);
}
