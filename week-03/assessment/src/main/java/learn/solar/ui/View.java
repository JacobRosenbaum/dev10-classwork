package learn.solar.ui;

import learn.solar.models.Panel;

import java.util.List;
import java.util.Scanner;

public class View {
    private final Scanner console = new Scanner(System.in);

    public MenuOption displayMenuAndSelect() {
        MenuOption[] values = MenuOption.values();
        printHeader("Main Menu");
        for (int i = 0; i < values.length; i++) {
            System.out.printf("%s. %s\n", i + 1, values[i].getOption());
        }
        int index = readInt("\nSelect an option [1-5]: ", 1, 5);
        return values[index-1];
    }

    public void printAllSectionTitles(List<String> section) {
        int count = 1;
       for (int i = 0; i < section.size(); i++){
           System.out.printf("%s. %s%n", count, section.get(i));
           count ++;
       }
    }

    public void printHeader(String header) {
        System.out.println("\n" + header);
        System.out.println("=".repeat(header.length()));
    }

    private String readString(String message) {
        System.out.print(message);
        return console.nextLine();
    }

    private String readRequiredString(String message) {
        String result;
        do {
            result = readString(message);
            if (result.trim().length() == 0) {
                System.out.println("Value is required.");
            }
        } while (result.trim().length() == 0);
        return result;
    }

    private int readInt(String message) {
        String input = null;
        int result = 0;
        boolean isValid = false;
        do {
            try {
                input = readRequiredString(message);
                result = Integer.parseInt(input);
                isValid = true;
            } catch (NumberFormatException ex) {
                System.out.printf("%s is not a valid number.%n", input);
            }
        } while (!isValid);

        return result;
    }

    private int readInt(String message, int min, int max) {
        int result;
        do {
            result = readInt(message);
            if (result < min || result > max) {
                System.out.printf("Value must be between %s and %s.%n", min, max);
            }
        } while (result < min || result > max);
        return result;
    }
}
