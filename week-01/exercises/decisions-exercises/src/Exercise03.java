import java.util.Scanner;


public class Exercise03 {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        // 1. Change the code to accept input from the user.
        // Use a Scanner and parse the input with Integer.parseInt.
        System.out.print("Enter a number: ");
        String input = console.nextLine();
        int value = Integer.parseInt(input);
        if (value >= 10 && value < 26) {
            System.out.println("Value is in the acceptable range.");
        }
    }
}
