package se.ju23.typespeeder.service;

import org.springframework.stereotype.Service;

import java.util.List;

public interface IOService {
    void print(Object object);
    void print(String string);
    void println();
    void println(Object object);
    void println(String string);
    void printList(List<?> list);
    String readString();

}
