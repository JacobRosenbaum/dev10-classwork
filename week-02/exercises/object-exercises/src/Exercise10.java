import java.util.Scanner;

public class Exercise10 {

    public static void main(String[] args) {
        // BALLOON GAME
        Scanner console = new Scanner(System.in);

        // 1. Instantiate three balloons of different colors.
        Balloon green = new Balloon("green");
        Balloon yellow = new Balloon("yellow");
        Balloon blue = new Balloon("blue");

        do {

            System.out.print("Inflate? [y/n]: ");
            if (console.nextLine().equalsIgnoreCase("y")) {
                // 2. If the user confirms an inflate, inflate each balloon.
                green.inflate();

                yellow.inflate();

                blue.inflate();

            }
            // STILL NEEDS WORK
            if (green.isExploded() || (yellow.isExploded()) || (blue.isExploded())){
                return;
            }

            // 3. When one or more balloons explode, stop the loop.
        } while (false);

        // 4. Print the color of the winners (balloons that exploded).

    }
}
