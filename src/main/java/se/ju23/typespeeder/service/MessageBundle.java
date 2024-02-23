package se.ju23.typespeeder.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageBundle {
    private static final String BASE_NAME = "messages";
    private Locale currentLocale;
    private ResourceBundle bundle;

    public MessageBundle(Locale locale) {
        this.currentLocale = locale;
        this.bundle = ResourceBundle.getBundle(BASE_NAME, locale, getClass().getClassLoader());
    }

    public String getMessage(String key, Object... args) {
        return String.format(bundle.getString(key), args);
    }

    public void updateLocale(Locale newLocale) {
        this.currentLocale = newLocale;
        this.bundle = ResourceBundle.getBundle(BASE_NAME, newLocale, getClass().getClassLoader());
    }

    public Locale getCurrentLocale() {
        return this.currentLocale;
    }

    public List<String> getAllSubjects() {
        List<String> subjects = new ArrayList<>();
        for (int i = 1; ; i++) {
            String subjectKey = "subject." + i;
            if (bundle.containsKey(subjectKey)) {
                subjects.add(getMessage(subjectKey));
            } else {
                break;
            }
        }
        return subjects;
    }
}
