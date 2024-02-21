package se.ju23.typespeeder.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class Menu implements MenuService {

    // TODO

    private String TEXT_COLOR;

    public Menu() {
    }

    public Menu(String TEXT_COLOR) {
        this.TEXT_COLOR = TEXT_COLOR;
    }

    @Override
    public void displayMenu(List<String> menuOptions) {
        for (String string : menuOptions) {
            System.out.println(string);
        }
    }

    @Override
    public List<String> getMenuOptions(String menu) {
        return makeList(menu);
    }

    @Override
    public String startMenu() {
        return """
                1. Login
                2. Create account
                3. Change language
                4. News
                0. Quit""";
    }

    @Override
    public String mainMenu() {
        return """
                1. Play
                2. View leaderboard
                3. Change account information
                4. Patch notes
                0. Logout""";
    }

    private List<String> makeList(String menu) {
        String[] splitMenu = menu.split("\\R");
        return Arrays.asList(splitMenu);
    }
}
