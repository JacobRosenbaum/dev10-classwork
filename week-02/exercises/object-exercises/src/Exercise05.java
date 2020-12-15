import java.util.Scanner;

public class Exercise05 {

    public static void main(String[] args) {
        // STILL NEEDS WORK
        Scanner console = new Scanner(System.in);

        Musician[] musicians = new Musician[5];

        for (int i = 0; i < musicians.length; i++) {
            Musician musician = new Musician();
            System.out.print("Musician name: ");
            musician.setName(console.nextLine());
            System.out.print("Musician rating: ");
            int rating = Integer.parseInt(console.nextLine());
            musician.setRating(rating);
            musicians[i] = new Musician(musician.getName(), musician.getRating());

        }

        for (Musician m : musicians){
            System.out.printf("%s: %s%n", m.getName(), m.getRating());

        }

        // 1. Use a loop to populate the `musicians` array with your top 5 favorite musicians.
        // (Replace Frank Ocean.)
        // Create musicians from user input. (See Exercise04.)

        // 2. Use a second loop to print details about each musician.
    }
}
