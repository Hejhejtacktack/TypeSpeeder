package se.ju23.typespeeder.service;

import org.springframework.stereotype.Service;

public interface IOService {
    void print(Object object);
    void print(String string);
    void println(String string);
    String readString();
}
