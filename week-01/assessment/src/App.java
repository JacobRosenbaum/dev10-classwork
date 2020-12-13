import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        // Start up

        System.out.println("Welcome to Capsule Hotel \n========================");
        System.out.print("Enter the number of capsules available: ");
        String capsulesAvailable = console.nextLine();
        int parsedCapsulesAvailable = Integer.parseInt(capsulesAvailable);
        System.out.printf("\nThere are %s unoccupied capsules ready to be booked.\n", parsedCapsulesAvailable);

        /*
        CREATE AN STRING ARRAY
        I'll create an array to hold the number of unoccupied capsules.
        */
        String[] capsulesArray = new String[parsedCapsulesAvailable];
        boolean exit = false;
        /*

        MENU

        If there are capsules available to be booked, we need to enter the menu. Inside of an if statement,
        making sure the user input a capsule number greater than 0,
        I will create a do while loop. The "do" portion will be to print out the 4 options of the menu "while"
        boolean 'exit' = false. Inside of the do while loop will be a switch statement.
        */
        if (parsedCapsulesAvailable > 0) {
            do {
                System.out.println("\nGUEST MENU");
                System.out.println("==========");
                System.out.println("1. Check in");
                System.out.println("2. Check out");
                System.out.println("3. View Guests");
                System.out.println("4. Exit");
                System.out.print("Choose an option [1-4]: ");
                String menuOption = console.nextLine();
                boolean checkedIn = false;
                boolean checkedOut = false;
                boolean roomError = false;
                String hotelGuest = "";

                switch (menuOption) {
                    case "1":
                        do {
                            System.out.println("\nGuest Check In");
                            System.out.println("============");
                            System.out.print("Guest Name: ");
                            String guestName = console.nextLine();
                            System.out.printf("Capsule #[1-%s]: ", parsedCapsulesAvailable);
                            String capsuleNumber = console.nextLine();
                            int capsuleParsed = Integer.parseInt(capsuleNumber);
                            if ((capsuleParsed > (parsedCapsulesAvailable)) || (capsuleParsed < 1)) {
                                System.out.println("\nWe don't have that capsule");
                                roomError = true;

                            } else {
                                for (int i = capsuleParsed - 1; i < capsulesArray.length; i++) {
                                    if (capsulesArray[i] == null) {
                                        capsulesArray[capsuleParsed - 1] = guestName;
                                        roomError = false;
                                        break;
                                    } else {
                                        System.out.println("\nError!");
                                        System.out.printf("Capsule #%s is occupied.\n", capsuleParsed);
                                        roomError = true;
                                        break;
                                    }
                                }
                            }
                            if (!roomError) {
                                System.out.println("\nGreat Success!");
                                System.out.printf("%s is booked in capsule #%s\n", guestName, capsuleParsed);
                                checkedIn = true;
                            }
                        } while ((!checkedIn) || (roomError));
                        break;
                    case "2":
                        do {
                            System.out.println("\nGuest Check Out");
                            System.out.println("============");
                            System.out.printf("Capsule #[1-%s]: ", parsedCapsulesAvailable);
                            String capsuleNumber = console.nextLine();
                            int capsuleParsed = Integer.parseInt(capsuleNumber);
                            if ((capsuleParsed > (parsedCapsulesAvailable)) || (capsuleParsed < 1)) {
                                System.out.println("\nWe don't have that capsule");
                                roomError = true;
                            } else {
                                for (int i = capsuleParsed - 1; i < capsulesArray.length; i++) {
                                    if (capsulesArray[i] != null) {
                                        hotelGuest = capsulesArray[capsuleParsed - 1];
                                        roomError = false;
                                        if (!roomError) {
                                            System.out.println("\nGreat Success!");
                                            System.out.printf("%s has been checked out of capsule #%s\n", hotelGuest, capsuleParsed);
                                            capsulesArray[capsuleParsed-1] = null;
                                            checkedOut = true;
                                            break;
                                        }
                                        break;
                                    } else {
                                        System.out.println("\nError!");
                                        System.out.printf("Capsule #%s is unoccupied.\n", capsuleParsed);
                                        roomError = true;
                                        break;
                                    }
                                }
                            }

                        } while ((!checkedOut) || (roomError));
                        break;
                    case "3":
                    case "4":
                   /* default:
                        System.out.println("Please type again");
*/
                }
            } while (!exit);
        }

        /*
        CASE 1: If the user types number 1, I will print lines asking the user to input a name and capsule number
        for a guest. Once I have the input, I will run a for loop to check my array of capsules to see if that room
        is occupied. I'll make an if statement, if the for loop returns 'null', I will print a message saying
        the guest has been checked in. Then, I will add the guests name to the capsule array using the user input
        as my index number. I'll make a boolean called 'checkedIn' which will now equal true.
        Else, I will print a message saying the room is booked. The if statement will
        be in a while loop, it will re-print the capsule number message until the capsule number returns null.
        That while loop will be inside another while loop that runs while 'checkedIn' = false. When checkedIn
        equals true, I will break the loop, re-printing the menu. THIS WILL BE A METHOD

        CASE 2: If the user types number 2, I will print lines asking the user to input a name and capsule number
        for a guest. Once I have the input, I will run a for loop to check my array of capsules to see if that room
        is occupied. I'll make an if statement, if the for loop returns a string, I will print a message saying
        the guest has been checked out using the array index as my capsule number and the string as the guest.
        Then, I will add the guests name to the capsule array using the user input
        as my index number. I'll make a boolean called 'checkedOut' which will now equal true.
        Else, I will print a message saying the room is unoccupied. The if statement will
        be in a while loop, it will re-print the capsule number message until the capsule number returns a string.
        That while loop will be inside another while loop that runs while 'checkedOut' = false.When checkedOut
        equals true, I will break the loop, re-printing the menu. THIS WILL BE A METHOD

        CASE 3: If the user types number 3; I'll have to show my array of guests with their index number equaling
        their capsule number. I'll print the message asking for a capsule number.
        I'll declare two Strings arrays, fivePrevious and fiveNext. I'll run one for loop of my array of capsules
        with int i equaling the capsule number the user input.
        I'll initialize a String array closeCapsules to hold the 5 values above and below the capsule number.
        I'll initialize a int capsuleCounter to see if I have 11 capsules.
        I'll initialize a boolean capsuleEqualsEleven.
        I'll nest an if statement in my for loop. IF the index of a value in my capsules array is
        (larger than the index value AND less than or equal the value plus 5) I will add the value to the fiveNext array
        and add 1 to the capsuleCounter
        ELSE IF the index of the capsule value is (smaller than the index value AND less than or equal the value minus 5)
        I'll add it to the previousFive array and add 1 to the capsuleCounter.
        Another if statement, IF capsuleCounter = 11; print the previousFive array and the nextFive array.
        ELSE IF (capsuleCounter != 11), I'll
        NEED TO WORK THIS EDGE CASE OUT... / WILL HANDLE IT IN THE CODE

        CASE 4: If the user types number 4, I will print out a confirmation to make sure they want
        to exit, and once they confirm, boolean 'exit' will = true and the loop will end. IF they do not confirm,
        the menu will re-print.
        */
    }
}
