import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Exercise12 {

    // 1. Create a method.
    // Name: readRequiredString
    // Inputs: String
    // Output: String
    // Description: prompts a user to enter a required string and returns their validated input.
    // The parameter is the message displayed to the user.
    //
    // See the readRequiredString implementation in the methods lesson.
    // You can definitely improve it. Make sure you don't allow blank input. Checking the length() is not enough.
public static String readRequiredString(String prompt) {
    Scanner console = new Scanner(System.in);


    if (prompt.length() > 0) {

        System.out.print(prompt);

        return console.nextLine();
    }
    return ("Please try again");
}

    // 2. Create a method.
    // Name: printNounPhrase
    // Inputs: none
    // Output: none
    // Description: prints an adjective + noun phrase to the console based on user input.
    // Internally, prompts a user for an adjective and a noun with readRequiredString.
    // Concatenates adjective and noun and prints it to the console.
    public static void printNounPhrase(){
        String adjective = null;
        String noun = null;

        adjective = readRequiredString("Print an adjective: ");
        noun = readRequiredString("Print a noun: ");
        String concat = adjective + noun;
        System.out.printf("Your concatnated word is %s.%n", concat);



    }

    public static void main(String[] args) {
        // 3. Uncomment the code below and confirm it works.
        printNounPhrase();
        printNounPhrase();
        printNounPhrase();
    }
}
