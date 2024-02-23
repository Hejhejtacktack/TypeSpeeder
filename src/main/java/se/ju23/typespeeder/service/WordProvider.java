package se.ju23.typespeeder.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface WordProvider {
    Map<String, List<String>> getAllWords();
}
