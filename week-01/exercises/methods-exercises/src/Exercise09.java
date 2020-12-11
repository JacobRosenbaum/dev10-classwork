import java.util.Scanner;


public class Exercise09 {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        // 2. Call your method in various ways to test it here.
        System.out.print("PLease choose your rows: ");
        int rowInput = console.nextInt();

        System.out.print("PLease choose your column: ");
        int columnInput = console.nextInt();

        printBox(rowInput,columnInput);

    }

    // 1. Create a method.
    // Name: printBox
    // Inputs: int, int
    // Output: void
    // Description: prints a visual box to the console. The first parameter is the number of rows to print.
    // The second parameter is the number of columns.
    // See repetition Exercise15.
    public static void printBox(int rows, int columns){

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                System.out.print("#");
            }
            System.out.println("#");
        }
    }


    // Expected Output (5 rows, 5 columns)
    // #####
    // #####
    // #####
    // #####
    // #####

    // (3 rows, 4 columns)
    // ####
    // ####
    // ####
}
