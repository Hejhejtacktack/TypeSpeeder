package se.ju23.typespeeder.service;

import org.springframework.stereotype.Service;

import java.util.List;

public interface MenuService {
    void displayMenu(List<String> menuOptions);
    List<String> getMenuOptions(String menu);
    String startMenu();
    String mainMenu();
}
