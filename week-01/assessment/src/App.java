import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        // Start up

        System.out.println("Welcome to Capsule Hotel \n========================");
        System.out.print("Enter the number of capsules available: ");
        String capsulesAvailable = console.nextLine();
        int parsedCapsulesAvailable = Integer.parseInt(capsulesAvailable);
        String[] capsulesArray = new String[parsedCapsulesAvailable];
        int exit = 2;

        if (parsedCapsulesAvailable > 0) {
            System.out.printf("\nThere are %s unoccupied capsules ready to be booked.\n", parsedCapsulesAvailable);

            // MENU

            do {
                System.out.println("\nGUEST MENU");
                System.out.println("==========");
                System.out.println("1. Check in");
                System.out.println("2. Check out");
                System.out.println("3. View Guests");
                System.out.println("4. Exit");
                System.out.print("Choose an option [1-4]: ");
                String menuOption = console.nextLine();

                switch (menuOption) {
                    case "1":
                        checkIn(console, capsulesArray);
                        break;
                    case "2":
                        checkOut(console, capsulesArray);
                        break;
                    case "3":
                        viewGuests(console, capsulesArray);
                        break;
                    case "4":
                        exit = exitProgram(console, exit);
                        break;
                    default:
                        System.out.println("Please type again - I didn't understand that.");
                }
            } while (exit == 2);
        } else {
            System.out.println("We have no capsules available");
        }
    }

    public static void checkIn(Scanner console, String[] capsulesArray) {
        boolean checkedIn = false;
        boolean roomCheckInError = false;
        System.out.println("\nGuest Check In");
        System.out.println("============");
        System.out.print("Guest Name: ");
        String guestName = console.nextLine();
        do {
            System.out.printf("Capsule #[1-%s]: ", capsulesArray.length);
            String capsuleNumberCheckIn = console.nextLine();
            int capsuleParsedCheckIn = Integer.parseInt(capsuleNumberCheckIn);
            if ((capsuleParsedCheckIn > (capsulesArray.length)) || (capsuleParsedCheckIn < 1)) {
                System.out.println("\nError!\nWe don't have that capsule\n");
                roomCheckInError = true;

            } else {
                if (capsulesArray[capsuleParsedCheckIn - 1] == null) {
                    capsulesArray[capsuleParsedCheckIn - 1] = guestName;
                    System.out.println("\nGreat Success!");
                    System.out.printf("%s is booked in capsule #%s\n", guestName, capsuleParsedCheckIn);
                    checkedIn = true;
                    break;
                } else {
                    System.out.println("\nError!");
                    System.out.printf("Capsule #%s is occupied.\n\n", capsuleParsedCheckIn);
                    roomCheckInError = true;
                }
            }
        } while ((!checkedIn) || (roomCheckInError));
    }

    public static void checkOut(Scanner console, String[] capsulesArray) {
        boolean checkedOut = false;
        boolean roomCheckOutError = false;
        String hotelGuest = "";
        int returnToMainFromCheckout = 1;

        System.out.println("\nGuest Check Out");
        System.out.println("============");
        do {
            System.out.printf("Capsule #[1-%s]: ", capsulesArray.length);
            String capsuleNumberCheckOut = console.nextLine();
            int capsuleParsedCheckOut = Integer.parseInt(capsuleNumberCheckOut);
            if ((capsuleParsedCheckOut > capsulesArray.length) || (capsuleParsedCheckOut < 1)) {
                System.out.println("\nError!\nWe don't have that capsule");
                roomCheckOutError = true;
            } else {
                if (capsulesArray[capsuleParsedCheckOut - 1] != null) {
                    hotelGuest = capsulesArray[capsuleParsedCheckOut - 1];
                    System.out.println("\nGreat Success!");
                    System.out.printf("%s has been checked out of capsule #%s\n", hotelGuest, capsuleParsedCheckOut);
                    capsulesArray[capsuleParsedCheckOut - 1] = null;
                    checkedOut = true;

                } else {
                    System.out.println("\nError!");
                    System.out.printf("Capsule #%s is unoccupied.\n", capsuleParsedCheckOut);
                    roomCheckOutError = true;
                }
            }
            returnToMainFromCheckout = returnToMenu(console, "\n1. Check another room\n2. Return to main menu\nChoose an option [1-2]: ");
        } while ((!checkedOut) && (roomCheckOutError) && (returnToMainFromCheckout == 1));
    }

    public static void viewGuests(Scanner console, String[] capsulesArray) {
        int returnToMainFromViewGuests = 1;

        do {
            System.out.println("\nView Guests");
            System.out.println("============");
            System.out.printf("Capsule #[1-%s]: ", capsulesArray.length);
            String capsuleNumberViewGuest = console.nextLine();
            int capsuleParsedViewGuest = Integer.parseInt(capsuleNumberViewGuest);
            int guestCount = 0;
            for (int i = 0; i < capsulesArray.length; i++) {
                String roomOccupied = capsulesArray[i] == null ? "[unoccupied]" : capsulesArray[i];
                if ((capsuleParsedViewGuest > (capsulesArray.length)) || (capsuleParsedViewGuest < 1)) {
                    System.out.println("\nError!\nWe don't have that capsule");
                    break;
                } else if ((i + 1 >= (capsuleParsedViewGuest - 10) && (i + 1 <= capsuleParsedViewGuest) || ((i + 1 <= (capsuleParsedViewGuest + 10) && (i + 1 > capsuleParsedViewGuest))))
                ) {
                    if ((i + 1 >= (capsuleParsedViewGuest - 5) && (i + 1 <= capsuleParsedViewGuest))) {
                        guestCount++;
                        System.out.printf("Capsule #%s: %s%n",
                                i + 1, roomOccupied);
                    } else if (((i + 1 <= (capsuleParsedViewGuest + 5) && (i + 1 > capsuleParsedViewGuest)))) {
                        guestCount++;
                        System.out.printf("Capsule #%s: %s%n",
                                i + 1, roomOccupied);
                    } else if ((((i + 1) + 11) > capsulesArray.length) && (guestCount < 1)) {
                        guestCount++;
                        System.out.printf("Capsule #%s: %s%n",
                                i + 1, roomOccupied);
                    } else if ((guestCount < 11) && (guestCount != 0)) {
                        guestCount++;
                        System.out.printf("Capsule #%s: %s%n",
                                i + 1, roomOccupied);
                    }
                }
            }
            returnToMainFromViewGuests = returnToMenu(console, "\n1. View another set of rooms\n2. Return to main menu\nChoose an option [1-2]: ");
        } while (returnToMainFromViewGuests == 1);
    }

    public static int returnToMenu(Scanner console, String prompt) {
        System.out.print(prompt);
        prompt = console.nextLine();
        if (prompt.equalsIgnoreCase("1")) {
            return 1;

        } else if (prompt.equalsIgnoreCase("2")) {
            return 2;
        }
        return 2;

    }

    public static int exitProgram(Scanner console, int exit) {

        System.out.println("Exit");
        System.out.println("====");
        System.out.println("Are you sure you want to exit?\nAll data will be lost");
        System.out.print("Exit [y/n]: ");
        String exitOrStay = console.nextLine();
        if (exitOrStay.equalsIgnoreCase("y")) {
            System.out.println("\nGoodbye!");
            return exit = 1;
        } else if (exitOrStay.equalsIgnoreCase("n")) {
            return exit = 2;
        }
        return exit = 2;
    }
}
