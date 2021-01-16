package learn.house.ui;

import learn.house.models.Guest;
import learn.house.models.Host;
import learn.house.models.Reservation;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class View {

    private final ConsoleIO io;

    public View(ConsoleIO io) {
        this.io = io;
    }

    public MainMenuOption displayMenuAndSelect() {
        MainMenuOption[] values = MainMenuOption.values();
        displayHeader("Main Menu");
        for (int i = 0; i < values.length; i++) {
            System.out.printf("%s. %s\n", i, values[i].getOption());
        }
        int index = io.readInt("\nSelect an option [0-4]: ", 0, 4);
        return values[index];
    }

    public void displayHeader(String message) {
        System.out.println("");
        System.out.println(message);
        System.out.println("=".repeat(message.length()));
    }

    public void enterToContinue() {
        io.readString("Press [Enter] to continue.");
    }

    public String getHostEmail() {
        return io.readEmail("Host Email: ");
    }

    public String getGuestEmail() {
        return io.readEmail("Guest Email: ");
    }

    public void displayReservationsByHost(List<Reservation> reservations) {
        String header = String.format("%s: %s, %s",
                reservations.get(0).getHost().getLastName(),
                reservations.get(0).getHost().getCity(),
                reservations.get(0).getHost().getState());
        displayHeader(header);

        for (Reservation reservation : reservations) {
            io.printf("ID: %s, %s - %s, Guest: %s, %s, Email: %s, Total: $%s%n",
                    reservation.getReservationId(),
                    reservation.getStartDate(),
                    reservation.getEndDate(),
                    reservation.getGuest().getLastName(),
                    reservation.getGuest().getFirstName(),
                    reservation.getGuest().getGuestEmail(),
                    reservation.getTotal());
        }
    }

    public LocalDate getStartDate() {
        return io.readLocalDate("Start (MM/dd/yyyy): ");
    }

    public LocalDate getEndDate() {
        return io.readLocalDate("End (MM/dd/yyyy): ");
    }

    public boolean displaySummary(LocalDate startDate, LocalDate endDate, BigDecimal total) {
        displayHeader("Summary");
        System.out.println("Start: " + startDate);
        System.out.println("Start: " + endDate);
        System.out.println("Total: " + total);
        return io.readBoolean("Is this ok? [y/n]: ");
    }

    public void displayStatus(boolean success, String message) {
        displayStatus(success, List.of(message));
    }

    public void displayStatus(boolean success, List<String> messages) {
        displayHeader(success ? "Success" : "Error");
        for (String message : messages) {
            io.println(message);
        }
    }

    public boolean displayTryAgain() {
        return io.readBoolean("\nOK,would you like to try again? [y/n]: ");
    }

    public void returnToMainMenu() {
        io.println("Returning to Main Menu...");
    }

    public void NoReservationsFound(String hostEmail) {
        io.println(hostEmail + " does not have any reservations booked.");
    }

    public void displayGuestDoesNotExist() {
        io.println("Guest does not exist in database. Please try again.");
    }

    public void displayHostDoesNotExist() {
        io.println("Host does not exist in database. Please try again.");
    }
}
