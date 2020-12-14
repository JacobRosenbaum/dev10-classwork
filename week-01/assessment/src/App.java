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

        String[] capsulesArray = new String[parsedCapsulesAvailable];
        boolean exit = false;

        // MENU

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
                boolean checkedOut = false;
                boolean checkedIn = false;
                boolean roomCheckOutError = false;
                boolean roomCheckInError = false;
                String hotelGuest = "";
                int returnToMainFromCheckout = 1;
                int returnToMainFromViewGuests = 1;

                switch (menuOption) {
                    case "1":

                        System.out.println("\nGuest Check In");
                        System.out.println("============");
                        System.out.print("Guest Name: ");
                        String guestName = console.nextLine();
                        do {
                            System.out.printf("Capsule #[1-%s]: ", parsedCapsulesAvailable);
                            String capsuleNumber = console.nextLine();
                            int capsuleParsed = Integer.parseInt(capsuleNumber);
                            if ((capsuleParsed > (parsedCapsulesAvailable)) || (capsuleParsed < 1)) {
                                System.out.println("\nError!\nWe don't have that capsule\n");
                                roomCheckInError = true;

                            } else {
                                if (capsulesArray[capsuleParsed - 1] == null) {
                                    capsulesArray[capsuleParsed - 1] = guestName;
                                    System.out.println("\nGreat Success!");
                                    System.out.printf("%s is booked in capsule #%s\n", guestName, capsuleParsed);
                                    checkedIn = true;
                                    break;
                                } else {
                                    System.out.println("\nError!");
                                    System.out.printf("Capsule #%s is occupied.\n\n", capsuleParsed);
                                    roomCheckInError = true;
                                }
                            }
                        } while ((!checkedIn) || (roomCheckInError));
                        break;
                    case "2":
                        System.out.println("\nGuest Check Out");
                        System.out.println("============");
                        do {
                            System.out.printf("Capsule #[1-%s]: ", parsedCapsulesAvailable);
                            String capsuleNumber = console.nextLine();
                            int capsuleParsed = Integer.parseInt(capsuleNumber);
                            if ((capsuleParsed > (parsedCapsulesAvailable)) || (capsuleParsed < 1)) {
                                System.out.println("\nError!\nWe don't have that capsule");
                                roomCheckOutError = true;
                            } else {
                                if (capsulesArray[capsuleParsed - 1] != null) {
                                    hotelGuest = capsulesArray[capsuleParsed - 1];
                                    System.out.println("\nGreat Success!");
                                    System.out.printf("%s has been checked out of capsule #%s\n", hotelGuest, capsuleParsed);
                                    capsulesArray[capsuleParsed - 1] = null;
                                    checkedOut = true;

                                } else {
                                    System.out.println("\nError!");
                                    System.out.printf("Capsule #%s is unoccupied.\n", capsuleParsed);
                                    roomCheckOutError = true;
                                }
                            }
                            returnToMainFromCheckout = returnToMenu(console, "\n1. Check another room\n2. Return to main menu\nChoose an option [1-2]: ");
                        } while ((!checkedOut) && (roomCheckOutError) && (returnToMainFromCheckout == 1));
                        break;
                    case "3":
                        do {
                            System.out.println("\nView Guests");
                            System.out.println("============");
                            System.out.printf("Capsule #[1-%s]: ", parsedCapsulesAvailable);
                            String capsuleNumber = console.nextLine();
                            int capsuleParsed = Integer.parseInt(capsuleNumber);
                            int guestCount = 0;
                            for (int i = 0; i < capsulesArray.length; i++) {
                                String roomOccupied = capsulesArray[i] == null ? "[unoccupied]" : capsulesArray[i];
                                if ((capsuleParsed > (parsedCapsulesAvailable)) || (capsuleParsed < 1)) {
                                    System.out.println("\nError!\nWe don't have that capsule");
                                    break;
                                } else if ((i + 1 >= (capsuleParsed - 10) && (i + 1 <= capsuleParsed) || ((i + 1 <= (capsuleParsed + 10) && (i + 1 > capsuleParsed))))
                                ) {
                                    if ((i + 1 >= (capsuleParsed - 5) && (i + 1 <= capsuleParsed))) {
                                        guestCount++;
                                        System.out.printf("Capsule #%s: %s%n",
                                                i + 1, roomOccupied);
                                    } else if (((i + 1 <= (capsuleParsed + 5) && (i + 1 > capsuleParsed)))) {
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
                        break;
                    case "4":
                        System.out.println("Exit");
                        System.out.println("====");
                        System.out.println("Are you sure you want to exit?\nAll data will be lost");
                        System.out.print("Exit [y/n]: ");
                        String exitOrStay = console.nextLine();
                        if (exitOrStay.equalsIgnoreCase("y")) {
                            System.out.println("\nGoodbye!");
                            exit = true;
                        } else if (exitOrStay.equalsIgnoreCase("n")) {
                            exit = false;
                        }
                        break;
                    default:
                        System.out.println("Please type again - I didn't understand that.");
                }
            } while (!exit);
        }
    }

    public static int returnToMenu(Scanner console, String prompt) {
        System.out.print(prompt);
        prompt = console.nextLine();
        if (prompt.equalsIgnoreCase("1")) {
            return 1;

        } else if (prompt.equalsIgnoreCase("2")) {
            return 2;
        }
        return (2);

    }
}
