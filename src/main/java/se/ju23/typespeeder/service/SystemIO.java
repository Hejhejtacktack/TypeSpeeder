package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class SystemIO implements IOService {

    private final Scanner scanner;

    private Menu menu;

    @Autowired
    public SystemIO(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void print(Object object) {
        System.out.println(object);
    }

    @Override
    public void print(String string) {
        System.out.print(string);
    }

    @Override
    public void println() {
        System.out.println();
    }

    @Override
    public void println(Object object) {
        System.out.println(object);
    }

    @Override
    public void println(String string) {
        System.out.println(string);
    }

    @Override
    public void printList(List<?> list) {
        for (Object object : list) {
            System.out.println(object);
        }
    }

    @Override
    public String readString() {
        return this.scanner.nextLine();
    }
}
