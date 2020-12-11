import java.util.Scanner;

public class Exercise12 {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.print("Enter a phrase: ");
        String phrase = console.nextLine();

        // 1. Write a loop to determine if the letter `x` occurs in a user-entered phrase.
        // 2. Print a message for both finding and not finding the `x`.
        for (int i =0; i < phrase.length(); i++){
            if (phrase.charAt(i) == 'x'){
                System.out.println("Here lies an X");
                break;
                // Cant figure out how to only display the "not finding x" message when there isn;t an x
            }
        }
        System.out.println("There has not been, nor will there ever be, an X here.");
    }
}
