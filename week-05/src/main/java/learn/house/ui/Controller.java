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
import java.sql.ResultSet;
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
                    break;
                case UPDATE_RESERVATION:
                    updateReservation();
                    break;
                case DELETE_RESERVATION:
                    break;
            }

        } while (option != MainMenuOption.EXIT);

    }

    private void viewReservationsByHost() throws DataAccessException {
        view.displayHeader(MainMenuOption.VIEW_RESERVATIONS_BY_HOST.getOption());
        Host host = getHost();
        List<Reservation> reservations = getReservationList(host.getHostEmail());
        if (reservations != null) {
            hostService.findByEmail(host.getHostEmail());
            view.displayReservationsByHost(reservations);
        }
        view.enterToContinue();
    }

    private void addReservation() throws DataAccessException {
        view.displayHeader(MainMenuOption.ADD_RESERVATION.getOption());
        Guest guest = getGuest();
        Host host = getHost();

        List<Reservation> reservations = getReservationList(host.getHostEmail());
        if (reservations != null) {
            view.displayReservationsByHost(reservations);
        }

        boolean correct = true;
        while (correct) {
            LocalDate startDate = view.getStartDate();
            LocalDate endDate = view.getEndDate();
            BigDecimal total = getTotal(startDate, endDate, host);
            boolean success = view.displaySummary(startDate, endDate, total);

            if (success) {
                Reservation reservation = makeReservation(0, startDate, endDate, host, guest, total);
                Result<Reservation> result = reservationService.add(reservation);
                if (!result.isSuccess()) {
                    view.displayStatus(false, result.getErrorMessages());

                } else {
                    String successMessage = String.format("Reservation ID: %s has been booked. Cheers!", result.getPayload().getReservationId());
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

    private void updateReservation() throws DataAccessException {
        view.displayHeader(MainMenuOption.UPDATE_RESERVATION.getOption());
        Guest guest = getGuest();
        Host host = getHost();
        Reservation reservation = getReservation(guest, host);

        if (reservation != null) {
            view.displayReservationHeader(reservation);
            view.displayReservation(reservation);
            view.displayEditHeader(reservation);
            boolean correct = true;

            while (correct) {
                view.update(reservation);
                BigDecimal total = getTotal(reservation.getStartDate(), reservation.getEndDate(), host);
                boolean success = view.displaySummary(reservation.getStartDate(), reservation.getEndDate(), total);

                if (success) {
                    reservation = makeReservation(reservation.getReservationId(), reservation.getStartDate(), reservation.getEndDate(),
                            host, guest, total);
                    Result<Reservation> result = reservationService.update(reservation);
                    if (!result.isSuccess()) {
                        view.displayStatus(false, result.getErrorMessages());

                    } else {
                        String successMessage = String.format("Reservation ID: %s has been updated. Cheers!", result.getPayload().getReservationId());
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
    }

    private Reservation makeReservation(Integer id, LocalDate startDate, LocalDate endDate, Host host,
                                        Guest guest, BigDecimal total) {

        Reservation reservation = new Reservation();
        if (id != 0) {
            reservation.setReservationId(id);
        }
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setHost(host);
        reservation.setGuest(guest);
        reservation.setTotal(total);

        return reservation;
    }

    private Host getHost() throws DataAccessException {
        boolean validateHost = true;
        String hostEmail;
        Host host = new Host();
        while (validateHost) {
            hostEmail = view.getHostEmail();
            host = hostService.findByEmail(hostEmail);
            if (host == null) {
                view.displayHostDoesNotExist();
            } else {
                validateHost = false;
            }
        }

        return host;
    }

    private Guest getGuest() throws DataAccessException {
        boolean validateGuest = true;
        String guestEmail;
        Guest guest = new Guest();
        while (validateGuest) {
            guestEmail = view.getGuestEmail();
            guest = guestService.findByEmail(guestEmail);
            if (guest == null) {
                view.displayGuestDoesNotExist();
            } else {
                validateGuest = false;
            }
        }

        return guest;
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

    private Reservation getReservation(Guest guest, Host host) {
        try {
            return reservationService.findReservationByGuestAndHostEmail(guest.getGuestEmail(),
                    host.getHostEmail());
        } catch (DataAccessException ex) {
            view.NoReservationsFound(host.getHostEmail());
            return null;
        }
    }
}
