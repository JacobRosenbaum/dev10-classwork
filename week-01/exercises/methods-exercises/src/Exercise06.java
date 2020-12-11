public class Exercise06 {

    // 1. Create a method.
    // Name: isBetween
    // Inputs: int, int, int
    // Output: boolean
    // Description: return true if the first parameter is between the second and third parameter.
    // Otherwise, returns false.

    public static void main(String[] args) {
        // 2. Call your method in various ways to test it here.
        System.out.println(isBetween(10, 2, 30));
    }

    public static boolean isBetween(int number1, int number2, int number3) {

        if ((number1 > number2) && (number1 < number3)) {
            return true;
        }

       return false;
    }
}
