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
                boolean roomErrorCheckOut = false;
                boolean roomCheckInError = false;
                String hotelGuest = "";
                boolean viewGuests = true;
                boolean returnToMain = false;
                boolean returnToMainCheckOut = true;

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

//                                for (int i = capsuleParsed - 1; i < capsulesArray.length; i++) {
//                                    if (capsulesArray[i] == null) {
//                                        capsulesArray[capsuleParsed - 1] = guestName;
//                                        roomCheckInError = false;
//                                        break;
//                                    } else {
//                                        System.out.println("\nError!");
//                                        System.out.printf("Capsule #%s is occupied.\n", capsuleParsed);
//                                        roomCheckInError = true;
//                                        break;
//                                    }
//                                }
                                }

//                            if (!roomCheckInError) {
//                                System.out.println("\nGreat Success!");
//                                System.out.printf("%s is booked in capsule #%s\n", guestName, capsuleParsed);
//                                checkedIn = true;
//                            }
                        } while ((!checkedIn) || (roomCheckInError));
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
                                roomErrorCheckOut = true;
                            } else {
                                for (int i = capsuleParsed - 1; i < capsulesArray.length; i++) {
                                    if (capsulesArray[i] != null) {
                                        hotelGuest = capsulesArray[capsuleParsed - 1];
                                        roomErrorCheckOut = false;
                                        if (!roomErrorCheckOut) {
                                            System.out.println("\nGreat Success!");
                                            System.out.printf("%s has been checked out of capsule #%s\n", hotelGuest, capsuleParsed);
                                            capsulesArray[capsuleParsed - 1] = null;
                                            checkedOut = true;
                                            break;
                                        }
//                                        break;
                                    } else {
                                        System.out.println("\nError!");
                                        System.out.printf("Capsule #%s is unoccupied.\n", capsuleParsed);
                                        roomErrorCheckOut = true;
                                        break;
                                    }
                                }
                            }
                            System.out.println("\nTo check another room, press 1.\nOtherwise, press 2 to go back to the Main Menu");
                            String leaveOrStay = console.nextLine();
                            if (leaveOrStay.equalsIgnoreCase("1")) {
                                returnToMainCheckOut = true;

                            } else if (leaveOrStay.equalsIgnoreCase("2")) {
                                returnToMainCheckOut = false;
                                break;
                            }

                        } while ((!checkedOut) || (roomErrorCheckOut) || (returnToMainCheckOut));
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
                                if ((i + 1 >= (capsuleParsed - 10) && (i + 1 <= capsuleParsed) || ((i + 1 <= (capsuleParsed + 10) && (i + 1 > capsuleParsed))))
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
                            System.out.println("\nTo view another set of rooms, press 1.\nOtherwise, press 2 to go back to the Main Menu");
                            String viewMore = console.nextLine();
                            if (viewMore.equalsIgnoreCase("1")) {
                                viewGuests = true;
                            } else if (viewMore.equalsIgnoreCase("2")) {
                                viewGuests = false;
                                break;
                            }
                        }
                        while (viewGuests = true);
                        break;
                    case "4":
                        do {
                            System.out.println("Exit");
                            System.out.println("====");
                            System.out.println("Are you sure you want to exit?\nAll data will be lost");
                            System.out.print("Exit [y/n]: ");
                            String exitOrStay = console.nextLine();
                            if (exitOrStay.equalsIgnoreCase("y")) {
                                System.out.println("\nGoodbye!");
                                exit = true;
                            } else if (exitOrStay.equalsIgnoreCase("n")) {
                                returnToMain = true;
                            }
                        } while (returnToMain = false);
                        break;
                    default:
                        System.out.println("Please type again - I didn't understand that.");
                }
            } while (!exit);
        }
    }
}
