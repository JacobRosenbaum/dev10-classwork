import java.util.Scanner;

public class Exercise15 {
    /* FIZZ BUZZ

    Historically, the Fizz Buzz (https://en.wikipedia.org/wiki/Fizz_buzz) problem was used in programming interviews.
    Not sure if it still is. Just in case, we'll get it out of the way in Milestone 1.
*/
    public static int readInt (String prompt){
        Scanner console = new Scanner(System.in);

        System.out.print(prompt);
        String input = console.nextLine();
        int number = Integer.parseInt(input);
        if (number > 0){

            for (int i = 1 ; i < number + 1; i++ ){
                if ((i % 3 ==0) && (i % 5 == 0)){
                    System.out.println("Fizz Buzz");
                }
                else if (i % 3 == 0){
                    System.out.println("Fizz");

                }
                else if (i % 5 == 0){
                    System.out.println("Buzz");
                }
                else {
                    System.out.println(i);
                }
            }
            return number;
        }
        return 0;
    }

    public static void main(String[] args){
        readInt("Please enter a positive integer: ");
        // Can't figure out how to complete logic in main method because I don't know how to access the number from the readInt


    }
    /*
    Write a program to:
    - Prompt a user for a positive integer and store the result. (Could reuse a readInt method.)
    - Loop from the number 1 to the user's integer.
    - If the number is divisible by 3, print Fizz.
    - If the number is divisible by 5, print Buzz.
    - If the number is divisible by both 3 and 5, print Fizz Buzz.
    - If the number is not divisible by either 3 or 5, print the number.

    Example Output:
    1
    2
    Fizz
    4
    Buzz
    Fizz
    7
    8
    Fizz
    Buzz
    11
    Fizz
    13
    14
    Fizz Buzz
    16
    17
    Fizz
     */
}
