package se.ju23.typespeeder;

import java.util.ArrayList;
import java.util.List;

public class Menu implements MenuService {

    private String TEXT_COLOR;

    public Menu() {
    }

    public Menu(String TEXT_COLOR) {
        this.TEXT_COLOR = TEXT_COLOR;
    }

    @Override
    public void displayMenu() {
        for (String string : getMenuOptions()) {
            System.out.println(string);
        }
    }

    @Override
    public List<String> getMenuOptions() {
        List<String> list = new ArrayList<>();
        list.add("1. Login");
        list.add("2. Change user information (account name, username and password");
        list.add("3. Change language (swedish or english)");
        list.add("4. View leaderboard");
        list.add("5. Patch notes");
        return list;
    }

    private String mainMenu() {
        return """
                Main Menu
                
                1. Login
                2. Create account
                0. Quit
                """;
    }
}
