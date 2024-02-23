package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocaleServiceImpl {

    private Locale currentLocale = Locale.ENGLISH;
    private final MessageBundle messageBundle;

    @Autowired
    public LocaleServiceImpl(MessageBundle messageBundle) {
        this.messageBundle = messageBundle;
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
        this.messageBundle.updateLocale(currentLocale);
    }
}
