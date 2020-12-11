import java.util.Scanner;
public class Exercise14 {

    /* SHORT SURVEY

    Write a program that asks a user four questions and prints the results:
    - What is your first name?
    - What is your last name?
    - How many towns/cities have you lived in?
    - How many musical instruments can you play?

    Store each answer in a variable with an appropriate type.
    Print the results after the user has answered all four questions.

    Use methods to break the program into reusable blocks of code.
     */
    public static String returnName(String prompt){
        Scanner console = new Scanner(System.in);
        System.out.print(prompt);
        String name = console.nextLine();
        System.out.println(name);
        return name;
    }
    public static int returnInt(String prompt){
        Scanner console = new Scanner(System.in);
        System.out.print(prompt);
        String number = console.nextLine();
        int parsedNumber  = Integer.parseInt(number);
        System.out.println(parsedNumber);
        return parsedNumber;
    }
    public static void main(String[] args){
        returnName("What's your first name? ");
        returnName("What's your last name? ");
        returnInt("How many towns/cities have you lived in? ");
        returnInt("How many musical instruments can you play? ");
        // Can't figure out how to wait until all questions are asked to display answers
    }

}
