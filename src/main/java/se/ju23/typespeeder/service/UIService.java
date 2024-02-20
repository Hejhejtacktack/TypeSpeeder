package se.ju23.typespeeder.service;

import java.util.List;

public interface UIService {
    String promptForInput(String promptMessage);

    String promptForInput(List<?> list);
}
