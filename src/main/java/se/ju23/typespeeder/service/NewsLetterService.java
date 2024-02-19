package se.ju23.typespeeder.service;

import se.ju23.typespeeder.model.NewsLetter;

import java.util.Optional;

public interface NewsLetterService {
    Optional<NewsLetter> getLatestNewsLetter();
    void create();
}
