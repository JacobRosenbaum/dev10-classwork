public class Exercise03 {

    public static void main(String[] args) {
        // 1. Write a loop to count backward from 23 to 17.

        // Expected Output
        // 23
        // 22
        // 21
        // 20
        // 19
        // 18
        // 17
        int high = 24;
        int low = 17;
        for (int i = 0 ; i < high; i++){
            high--;
            System.out.println(high);
            if (high == low){
                break;
            }
        }
    }
}
