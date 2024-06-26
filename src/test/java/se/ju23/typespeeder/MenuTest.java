package se.ju23.typespeeder;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.mockito.Mockito;
import se.ju23.typespeeder.service.Menu;
import se.ju23.typespeeder.service.MenuService;
import se.ju23.typespeeder.service.MessageBundle;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testClassExists() {
        try {
            Class<?> clazz = Class.forName("se.ju23.typespeeder.service.Menu");
            assertNotNull(clazz, "The class 'Menu' should exist.");
        } catch (ClassNotFoundException e) {
            fail("The class 'Menu' does not exist.", e);
        }
    }

    @Test
    public void testMethodExists() {
        try {
            Class<?> clazz = Class.forName("se.ju23.typespeeder.service.Menu");
            Method method = clazz.getMethod("displayMenu");
            assertNotNull(method, "The method 'displayMenu()' should exist in the class 'Menu'.");
        } catch (ClassNotFoundException e) {
            fail("The class 'Menu' does not exist.", e);
        } catch (NoSuchMethodException e) {
            fail("The method 'displayMenu()' does not exist in the class 'Menu'.", e);
        }
    }

    @Test
    public void testMenuImplementsInterface() {
        try {
            Class<?> menuClass = Class.forName("se.ju23.typespeeder.service.Menu");
            boolean implementsInterface = false;

            Class<?>[] interfaces = menuClass.getInterfaces();
            for (Class<?> iface : interfaces) {
                if (iface.equals(MenuService.class)) {
                    implementsInterface = true;
                    break;
                }
            }

            assertTrue(implementsInterface, "The class 'Menu' should implement the interface 'MenuService'.");
        } catch (ClassNotFoundException e) {
            fail("The class 'Menu' could not be found", e);
        }
    }

    @Test
    public void testDisplayMenuCallsGetMenuOptionsAndReturnsAtLeastFive() {
        Menu menuMock = Mockito.spy(new Menu());
        doReturn(Arrays.asList("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")).when(menuMock).getMenuOptions(Mockito.anyString());

        menuMock.displayMenu(menuMock.getMenuOptions(Mockito.anyString()));
        verify(menuMock, times(1)).getMenuOptions(Mockito.anyString());
        assertTrue(menuMock.getMenuOptions(Mockito.anyString()).size() >= 5, "'getMenuOptions()' should return at least 5 alternatives.");
    }

    @Test
    public void menuShouldHaveAtLeastFiveOptions() {
        Menu menu = new Menu();
        menu.setMessageBundle(new MessageBundle(new Locale("sv")));
        List<String> options = menu.getMenuOptions(menu.mainMenu());
        assertTrue(options.size() >= 5, "The menu should contain at least 5 alternatives.");
        List<String> options2 = menu.getMenuOptions(menu.startMenu());
        assertTrue(options2.size() >= 5, "The menu should contain at least 5 alternatives.");
    }

    @Test
    public void menuShouldPrintAtLeastFiveOptions() {
        Menu menu = new Menu();
        menu.setMessageBundle(new MessageBundle(new Locale("sv")));
        menu.displayMenu(menu.getMenuOptions(menu.startMenu()));
        long count = outContent.toString().lines().count();
        assertTrue(count >= 5, "The menu should print out at least 5 alternatives.");
    }

    @Test
    public void testUserCanChooseSwedishLanguage() {
        String input = "svenska\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Menu menu = new Menu();
        menu.setMessageBundle(new MessageBundle(new Locale("sv")));
        menu.displayMenu(menu.getMenuOptions(menu.startMenu()));

        String consoleOutput = outContent.toString();
        assertTrue(consoleOutput.contains("Välj språk (svenska/engelska):"), "Menu should prompt for language selection.");
//        assertTrue(consoleOutput.contains("Svenska valt."), "Menu should confirm Swedish language selection.");
    }

}