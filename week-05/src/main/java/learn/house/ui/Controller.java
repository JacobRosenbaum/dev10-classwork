package learn.house.ui;

import learn.house.data.DataAccessException;
import learn.house.domain.GuestService;
import learn.house.domain.HostService;
import learn.house.domain.ReservationService;
import learn.house.domain.Result;
import learn.house.models.Guest;
import learn.house.models.Host;
import learn.house.models.Reservation;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class Controller {
    private final ReservationService reservationService;
    private final HostService hostService;
    private final GuestService guestService;
    private final View view;

    public Controller(ReservationService reservationService, HostService hostService,
                      GuestService guestService, View view) {
        this.reservationService = reservationService;
        this.hostService = hostService;
        this.guestService = guestService;
        this.view = view;
    }

    public void run() {
        view.displayHeader("Welcome To Don't Wreck My House");

        try {
            runMenuLoop();
        } catch (DataAccessException ex) {
            view.displayHeader("Fatal error " + ex);
        }
        view.displayHeader("Good day to you sir.");
    }

    private void runMenuLoop() throws DataAccessException {
        MainMenuOption option;

        do {
            option = view.displayMenuAndSelect();

            switch (option) {
                case EXIT:
                    break;
                case VIEW_RESERVATIONS_BY_HOST:
                    viewReservationsByHost();
                    break;
                case ADD_RESERVATION:
                    addReservation();
                case UPDATE_RESERVATION:
                    break;
                case DELETE_RESERVATION:
                    break;
            }

        } while (option != MainMenuOption.EXIT);

    }

    private void viewReservationsByHost() throws DataAccessException {
        view.displayHeader(MainMenuOption.VIEW_RESERVATIONS_BY_HOST.getOption());
        String hostEmail = view.getHostEmail();
        List<Reservation> reservations = getReservationList(hostEmail);
        if (reservations != null) {
            Host host = hostService.findByEmail(hostEmail);
            view.displayReservationsByHost(reservations, host);
        }
        view.enterToContinue();
    }

    private void addReservation() throws DataAccessException {
        view.displayHeader(MainMenuOption.ADD_RESERVATION.getOption());

        Guest guest = getGuest();
        if (guest == null) {
            return;
        }
        Host host = getHost();
        if (host == null) {
            return;
        }

        List<Reservation> reservations = reservationService.findReservationListByHostEmail(host.getHostEmail());
        view.displayReservationsByHost(reservations, host);
        boolean correct = true;

        while (correct) {
            LocalDate startDate = view.getStartDate();
            LocalDate endDate = view.getEndDate();
            BigDecimal total = getTotal(startDate, endDate, host);
            boolean success = view.displaySummary(startDate, endDate, total);

            if (success) {
                Reservation reservation = makeReservation(startDate, endDate, host, guest, total);
                Result<Reservation> result = reservationService.add(reservation);
                if (!result.isSuccess()) {
                    view.displayStatus(false, result.getErrorMessages());

                } else {
                    String successMessage = String.format("Reservation ID: %s created. Cheers!", result.getPayload().getReservationId());
                    view.displayStatus(true, successMessage);
                }
                correct = false;
            } else {
                boolean tryAgain = view.displayTryAgain();
                if (tryAgain) {
                    correct = true;
                } else {
                    view.returnToMainMenu();
                    correct = false;
                }
            }
        }

    }

    public Reservation makeReservation(LocalDate startDate, LocalDate endDate, Host host,
                                       Guest guest, BigDecimal total) {

        Reservation reservation = new Reservation();
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setHost(host);
        reservation.setGuest(guest);
        reservation.setTotal(total);

        return reservation;
    }

    private Host getHost() throws DataAccessException {
        String hostEmail = view.getHostEmail();
        return hostService.findByEmail(hostEmail);
    }

    private Guest getGuest() throws DataAccessException {
        String guestEmail = view.getGuestEmail();
        return guestService.findByEmail(guestEmail);
    }

    private BigDecimal getTotal(LocalDate startDate, LocalDate endDate, Host host) {
        return reservationService.calculateTotal(startDate, endDate, host);
    }

    private List<Reservation> getReservationList(String hostEmail) {
        try {
            return reservationService.findReservationListByHostEmail(hostEmail);
        } catch (DataAccessException ex) {
            view.NoReservationsFound(hostEmail);
            return null;
        }
    }

}
