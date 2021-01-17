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
        int index = io.readInt("\nSelect an option [0-5]: ", 0, 6);
        return values[index];
    }

    public void displayHeader(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println("=".repeat(message.length()));
    }

    public String getHostEmail() {
        return io.readEmail("Host Email: ");
    }

    public String getGuestEmail() {
        return io.readEmail("Guest Email: ");
    }

    public String getState() {
        return io.readState("State (Abbr.): ");

    }

    public void displayReservationsByHost(List<Reservation> reservations) {
        String header = String.format("%s: %s, %s",
                reservations.get(0).getHost().getLastName(),
                reservations.get(0).getHost().getCity(),
                reservations.get(0).getHost().getState());
        displayHeader(header);

        for (Reservation reservation : reservations) {
            displayReservation(reservation);
        }
    }

    public void displayReservationsByGuest(List<Reservation> reservations) {
        String header = String.format("%s %s (%s):",
                reservations.get(0).getGuest().getFirstName(),
                reservations.get(0).getGuest().getLastName(),
                reservations.get(0).getGuest().getGuestEmail());
        displayHeader(header);

        int count = 1;
        for (Reservation reservation : reservations) {
            io.printf("%s. %s - %s, Host: %s (%s) in %s, %s, Total: $%s%n",
                    count,
                    reservation.getStartDate(),
                    reservation.getEndDate(),
                    reservation.getHost().getLastName(),
                    reservation.getHost().getHostEmail(),
                    reservation.getHost().getCity(),
                    reservation.getHost().getState(),
                    reservation.getTotal());
            count++;
        }
    }

    public void displayReservationsByState(List<Reservation> reservations) {
        String header = String.format("Reservations in %s:",
                reservations.get(0).getHost().getState());
        displayHeader(header);

        int count = 1;
        for (Reservation reservation : reservations) {
            io.printf("%s. %s - %s, HOST: %s (%s) in %s, %s," +
                            " GUEST: %s, %s, Email: %s, TOTAL: $%s%n",
                    count,
                    reservation.getStartDate(),
                    reservation.getEndDate(),
                    reservation.getHost().getLastName(),
                    reservation.getHost().getHostEmail(),
                    reservation.getHost().getCity(),
                    reservation.getHost().getState(),
                    reservation.getGuest().getLastName(),
                    reservation.getGuest().getFirstName(),
                    reservation.getGuest().getGuestEmail(),
                    reservation.getTotal());
            count++;
        }
    }

    public Reservation update(Reservation reservation) {
        LocalDate startDate = io.readLocalDate("Start (" + reservation.getStartDate() + "): ", false);
        if (startDate != null) {
            reservation.setStartDate(startDate);
        }

        LocalDate endDate = io.readLocalDate("End (" + reservation.getEndDate() + "): ", false);
        if (endDate != null) {
            reservation.setEndDate(endDate);
        }

        return reservation;
    }

    public LocalDate getStartDate() {
        return io.readLocalDate("Start (MM/dd/yyyy): ", true);
    }

    public LocalDate getEndDate() {
        return io.readLocalDate("End (MM/dd/yyyy): ", true);
    }

    public boolean displaySummary(LocalDate startDate, LocalDate endDate, BigDecimal total) {
        displayHeader("Summary");
        System.out.println("Start: " + startDate);
        System.out.println("Start: " + endDate);
        System.out.println("Total: $" + total);
        return io.readBoolean("Is this ok? [y/n]: ");
    }

    public void displayStatus(boolean success, String message) {
        displayStatus(success, List.of(message));
    }

    public void displayStatus(boolean success, List<String> messages) {
        displayHeader(success ? "Success" : "Error");
        for (String message : messages) {
            io.print(cleanStatus(message));
        }
    }

    private String cleanStatus(String value) {
        return value.replace("[", "")
                .replace("]", "");

    }

    public boolean displayTryAgain() {
        return io.readBoolean("\nOK,would you like to try again? [y/n]: ");
    }

    public void returnToMainMenu() {
        io.readString("\nPress [Enter] to continue.");
        io.print("Returning to Main Menu..." + "\n");
    }

    public void hostReservationListNotFound(Host host) {
        io.printf("%n%s (%s) in %s, %s does not have any reservations booked at this time.%n",
                host.getLastName(), host.getHostEmail(), host.getCity(), host.getState());
    }

    public void guestReservationListNotFound(Guest guest) {
        io.printf("%n%s %s (%s) has not booked any reservations yet.%n",
                guest.getFirstName(), guest.getLastName(), guest.getGuestEmail());
    }

    public void stateReservationListNotFound(String state) {
        io.printf("%nThere are no reservations booked in %s at this time.%n",
                state);
    }

    public void noReservationFound(Guest guest, Host host) {
        io.printf("%n%s, %s (%s) doest not have a reservation with %s (%s) in %s, %s" +
                        " booked at this time.%n%n",
                guest.getLastName(), guest.getFirstName(), guest.getGuestEmail(),
                host.getLastName(), host.getHostEmail(), host.getCity(), host.getState());
    }

    public void reservationInPast(Guest guest, Reservation reservation) {
        io.printf("%n%s, %s (%s) booked a reservation from %s - %s," +
                        " you cannot delete a reservation in the past.%n",
                guest.getLastName(), guest.getFirstName(), guest.getGuestEmail(),
                reservation.getStartDate(), reservation.getEndDate());
    }

    public void displayGuestDoesNotExist() {
        io.println("Guest does not exist in database. Please try again.");
    }

    public void displayHostDoesNotExist() {
        io.println("Host does not exist in database. Please try again.");
    }

    public void displayReservation(Reservation reservation) {
        io.printf("ID: %s, %s - %s, Guest: %s, %s, Email: %s, Total: $%s%n",
                reservation.getReservationId(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getGuest().getLastName(),
                reservation.getGuest().getFirstName(),
                reservation.getGuest().getGuestEmail(),
                reservation.getTotal());
    }

    public void displayReservationHeader(Reservation reservation) {
        String header = String.format("%s: %s, %s",
                reservation.getHost().getLastName(),
                reservation.getHost().getCity(),
                reservation.getHost().getState());
        displayHeader(header);
    }

    public void displayEditOrDeleteHeader(Reservation reservation, String message) {
        String header = String.format(message,
                reservation.getReservationId());
        displayHeader(header);
    }

    public boolean deleteReservation(Reservation reservation) {
        return io.readBoolean("Would you like to delete Reservation ID: " +
                reservation.getReservationId() +
                " [y/n]: ");
    }
}
