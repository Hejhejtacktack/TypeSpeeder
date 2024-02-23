package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.model.NewsLetter;
import se.ju23.typespeeder.repository.NewsLetterRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class NewsLetterEngine implements NewsLetterService {

    NewsLetterRepository newsLetterRepo;

    @Autowired
    public NewsLetterEngine(NewsLetterRepository newsLetterRepo) {
        this.newsLetterRepo = newsLetterRepo;
    }

    @Override
    public Optional<NewsLetter> getLatestNewsLetter() {
        return this.newsLetterRepo.findFirstByOrderByPublishDateTimeDesc();
    }

    @Override
    public void create() {
        NewsLetter newsLetter = new NewsLetter(LocalDateTime.now(), "This application is under construction.\n" +
                "Soon the game will be implemented. For now you can create an account and view the leaderboard." +
                "This is some words to fill out the very needed space time travl safe. This feels very meaningful. " +
                " asdmfam sfmasd,mf a,dfm ma dfmansdfmad fnamnds fmans dfmnasdmnf amsnd fmans dfmna sdmfnamnsdf " +
                "Content field length should be at least 100 characters.Content field length should be at least 100 characters." +
                "Content field length should be at least 100 characters." +
                "Content field length should be at least 100 characters." +
                "Content field length should be at least 100 characters." +
                "Content field length should be at least 100 characters." +
                "v" +
                "v" +
                "v" +
                "Content field length should be at least 100 characters.Content field length should be at least 100 characters." +
                "Content field length should be at least 100 characters.");
        this.newsLetterRepo.save(newsLetter);
    }
}
