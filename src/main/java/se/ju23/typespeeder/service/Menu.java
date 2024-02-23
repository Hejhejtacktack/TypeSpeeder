package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class Menu implements MenuService {

    MessageBundle messageBundle;

    @Autowired
    public Menu(MessageBundle messageBundle) {
        this.messageBundle = messageBundle;
    }

    public Menu() {
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
        return this.messageBundle.getMessage("menu.startMenu");
    }

    @Override
    public String mainMenu() {
        return this.messageBundle.getMessage("menu.mainMenu");
    }

    private List<String> makeList(String menu) {
        String[] splitMenu = menu.split("\\R");
        return Arrays.asList(splitMenu);
    }
}
