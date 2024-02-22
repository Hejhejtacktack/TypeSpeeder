package se.ju23.typespeeder;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageBundle {
    private static final String BASE_NAME = "messages";
    private final ResourceBundle bundle;

    public MessageBundle(Locale locale) {
        this.bundle = ResourceBundle.getBundle(BASE_NAME, locale);
    }

    public String getMessage(String key, Object... args) {
        return String.format(bundle.getString(key), args);
    }
}
