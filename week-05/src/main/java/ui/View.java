package ui;

import org.springframework.stereotype.Component;

@Component
public class View {

    private final ConsoleIO io;
    public View(ConsoleIO io) {
        this.io = io;
    }

    public MainMenuOption displayMenuAndSelect() {
        MainMenuOption[] values = MainMenuOption.values();
        displayHeader("Main Menu");
        for (int i = 0; i < values.length; i++) {
            System.out.printf("%s. %s\n", i, values[i].getOption());
        }
        int index = io.readInt("\nSelect an option [0-4]: ", 0, 4);
        return values[index];
    }

    public void displayHeader(String message) {
        io.println("");
        io.println(message);
        io.println("=".repeat(message.length()));
    }
}
