import java.util.Scanner;

public class Exercise11 {
    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        System.out.print("What's the starting number?: ");
        String start = console.nextLine();
        int startingNumber = Integer.parseInt(start);

        System.out.print("What's the ending number?: ");
        String end = console.nextLine();
        int endingNumber = Integer.parseInt(end);

        System.out.print("What's the increment number?: ");
        String increment = console.nextLine();
        int incrementNumber = Integer.parseInt(increment);
        int sum = 0;
        for (int i = startingNumber; i < endingNumber + 1; i+=incrementNumber){
            sum+=i;
        }
        System.out.println(sum);
    }
}
