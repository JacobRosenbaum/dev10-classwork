public class Exercise02 {

    public static void main(String[] args) {
        // 1. Write a loop to print positive even numbers less than 13.
        // Use whatever approach you see fit. (Could count by 2 or use a decision statement inside the loop.)

        // Expected Output
        // 2
        // 4
        // 6
        // 8
        // 10
        // 12
        int number = 1;

        for (int i = 0; i < number; i++){
            number++;
            if ((number < 13) && (number > 0) && (number % 2 == 0)){
                System.out.println(number);
            }
        }
    }
}
