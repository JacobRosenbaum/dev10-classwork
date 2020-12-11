import java.util.Scanner;

public class Exercise10 {
    // 1. Add a `main` method.
    // 2. Create method that accepts a String and returns that string with all of its whitespace remove.
    // 2. Call your method in various ways in the main method.
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter a phrase and I'll remove the whitespace: ");
        removeWhitespace(console.nextLine());
    }

    public static String removeWhitespace(String word){
        String noWhiteSpace = "";
        String result = "";
        for (int i = 0; i < word.length(); i++) {
            result += word.charAt(i);
            noWhiteSpace = result.replaceAll("\\s+","");
        }
        System.out.println("Your phrase without whitespace is: " + noWhiteSpace);
        return (noWhiteSpace);
    }

}
