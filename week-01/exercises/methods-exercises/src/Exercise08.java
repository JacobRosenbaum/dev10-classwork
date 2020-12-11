public class Exercise08 {

    // 1. Create a method.
    // Name: getRandomFruit
    // Inputs: none
    // Output: String
    // Description: returns a random fruit name as a string.
    // See Exercise01.
    // Choose from at least 5 fruit.

    public static void main(String[] args) {
        // 2. Call your method in various ways to test it here.
        String randomFruit = getRandomFruit();
        System.out.println(randomFruit);
    }

    public static String getRandomFruit(){

        switch ((int) (Math.random() * 5)) {
            case 0:
                return "Banana";
            case 1:
                return "Orange";
            case 2:
                return "Peach";
            case 3:
                return "Mango";
            case 4:
                return "Pineapple";
        }

        return ""; // Should never happen.
    }
}
