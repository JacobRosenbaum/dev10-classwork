import java.util.Scanner;

public class Exercise15 {

    public static void main(String[] args) {
        // SWITCH OPPOSITES
        // Given a word, print its opposite using a switch statement.
        Scanner console = new Scanner(System.in);

        System.out.print("Enter a word: ");
        String word = console.nextLine();
        String opposite = null;

        switch (word){
            case "hot":
                System.out.println("cold");
                break;
            case "small":
                System.out.println("big");
                break;
            case "strong":
                System.out.println("weak");
                break;
            case "ice":
                System.out.println("fire");
                break;
            default:
                System.out.println("I don't know that word");
                break;
        }


    }
}
