package learn.house.ui;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

@Component
public class ConsoleIO {
    private static final String INVALID_NUMBER
            = "[INVALID] Enter a valid number.";
    private static final String NUMBER_OUT_OF_RANGE
            = "[INVALID] Enter a number between %s and %s.";
    private static final String REQUIRED
            = "[INVALID] Value is required.";
    private static final String NEEDS_AT_SYMBOL
            = "[INVALID] Email must contain an '@' symbol";
    private static final String TWO_CHARACTERS
            = "[INVALID] State must be abbreviated to 2 letters";
    private static final String INVALID_DATE
            = "[INVALID] Enter a date in MM/dd/yyyy format.";
    private final static String states = "|AL|AK|AS|AZ|AR|CA|CO|CT|DE|" +
            "DC|FM|FL|GA|GU|HI|ID|IL|IN|IA|KS|KY|LA|ME|MH|MD|MA|MI|MN|" +
            "MS|MO|MT|NE|NV|NH|NJ|NM|NY|NC|ND|MP|OH|OK|OR|PW|PA|PR|RI|" +
            "SC|SD|TN|TX|UT|VT|VI|VA|WA|WV|WI|WY|";

    private final Scanner scanner = new Scanner(System.in);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public void print(String message) {
        System.out.print(message);
    }

    public void println(String message) {
        System.out.println("\n" + message + "\n");
    }

    public void printf(String format, Object... values) {
        System.out.printf(format, values);
    }

    public String readString(String prompt) {
        print(prompt);
        return scanner.nextLine();
    }

    public String readRequiredString(String prompt) {
        while (true) {
            String result = readString(prompt);
            if (!result.isBlank()) {
                return result;
            }
            println(REQUIRED);
        }
    }

    public String readEmail(String prompt) {
        while (true) {
            String result = readString(prompt);
            if (result.isBlank()) {
                println(REQUIRED);
            } else if (!result.contains("@")) {
                println(NEEDS_AT_SYMBOL);
            } else {
                return result;
            }
        }
    }

    public String readState(String prompt) {
        while (true) {
            String result = readString(prompt);
            if (result.isBlank()) {
                println(REQUIRED);
            } else if (result.length() != 2) {
                println(TWO_CHARACTERS);
            } else if (!states.contains(result.toUpperCase(Locale.ROOT))) {
                println("[INVALID] " + result.toUpperCase(Locale.ROOT) + " is not a state.");
            } else {
                return result.toUpperCase(Locale.ROOT);
            }
        }
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readRequiredString(prompt));
            } catch (NumberFormatException ex) {
                println(INVALID_NUMBER);
            }
        }
    }

    public int readInt(String prompt, int min, int max) {
        while (true) {
            int result = readInt(prompt);
            if (result >= min && result <= max) {
                return result;
            }
            println(String.format(NUMBER_OUT_OF_RANGE, min, max));
        }
    }

    public boolean readBoolean(String prompt) {
        while (true) {
            String input = readRequiredString(prompt).toLowerCase();
            if (input.equals("y")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            }
            println("[INVALID] Please enter 'y' or 'n'.");
        }
    }

    public LocalDate readLocalDate(String prompt, boolean required) {
        String input;
        while (true) {
            if (required) {
                input = readRequiredString(prompt);
            } else {
                input = readString(prompt);
                if (input.isBlank()) {
                    return null;
                }
            }
            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException ex) {
                println(INVALID_DATE);
            }
        }
    }
}
