package learn.house.ui;

import learn.house.models.Reservation;
import org.springframework.stereotype.Component;

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
        if (reservations == null || reservations.isEmpty()) {
            io.println("No reservations found.");
            return;
        }

        String header = String.format("%s: %s, %s",
                reservations.get(0).getHost().getLastName(),
                reservations.get(0).getHost().getCity(),
                reservations.get(0).getHost().getState());
        displayHeader(header);

        for (Reservation reservation : reservations) {
            io.printf("ID: %s, %s - %s, Guest: %s, %s, Email: %s%n",
                    reservation.getReservationId(),
                    reservation.getStartDate(),
                    reservation.getEndDate(),
                    reservation.getGuest().getLastName(),
                    reservation.getGuest().getFirstName(),
                    reservation.getGuest().getGuestEmail());
        }
    }
}
