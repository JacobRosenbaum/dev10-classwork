package src;
import java.util.Scanner;

public class Exercise09 {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.print("What's the minimum value? ");
        String minValue = console.nextLine();
        int minimum = Integer.parseInt(minValue);

        System.out.print("What's the maximum value? ");
        String maxValue = console.nextLine();
        int maximum = Integer.parseInt(maxValue);

        System.out.print("What's the actual value? ");
        String actValue = console.nextLine();
        int actual = Integer.parseInt(actValue);

        if ((actual >= minimum) && (actual <= maximum)){

            System.out.println("That's in range - well done!");
        }

        else{
            System.out.println("Not in range, bud!");
        }

    }
}
