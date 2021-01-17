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
import java.util.Locale;

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
                case VIEW_RESERVATIONS_BY_GUEST:
                    viewReservationsByGuest();
                    break;
                case VIEW_RESERVATIONS_BY_STATE:
                    viewReservationsByState();
                    break;
                case ADD_RESERVATION:
                    addReservation();
                    break;
                case UPDATE_RESERVATION:
                    updateReservation();
                    break;
                case DELETE_RESERVATION:
                    deleteReservation();
                    break;
            }

        } while (option != MainMenuOption.EXIT);

    }

    private void viewReservationsByHost() throws DataAccessException {
        view.displayHeader(MainMenuOption.VIEW_RESERVATIONS_BY_HOST.getOption());
        Host host = getHost();
        List<Reservation> reservations = getHostReservationList(host);
        if (reservations != null) {
            hostService.findByEmail(host.getHostEmail());
            view.displayReservationsByHost(reservations);
        }
        view.returnToMainMenu();
    }

    private void viewReservationsByGuest() throws DataAccessException {
        view.displayHeader(MainMenuOption.VIEW_RESERVATIONS_BY_GUEST.getOption());
        Guest guest = getGuest();
        List<Reservation> reservations = getGuestReservationList(guest);
        if (reservations != null) {
            guestService.findByEmail(guest.getGuestEmail());
            view.displayReservationsByGuest(reservations);
        }
        view.returnToMainMenu();


    }

    private void viewReservationsByState() throws DataAccessException {

        view.displayHeader(MainMenuOption.VIEW_RESERVATIONS_BY_STATE.getOption());
        String state = view.getState();
        List<Reservation> reservations = getStateReservation(state);
        if (reservations != null) {
            view.displayReservationsByState(reservations);
        }
        view.returnToMainMenu();

    }


    private void addReservation() throws DataAccessException {
        view.displayHeader(MainMenuOption.ADD_RESERVATION.getOption());
        Guest guest = getGuest();
        Host host = getHost();

        List<Reservation> reservations = getHostReservationList(host);
        if (reservations != null) {
            view.displayReservationsByHost(reservations);
        }

        boolean correct = true;
        view.displayHeader("Book Reservation");
        while (correct) {
            LocalDate startDate = view.getStartDate();
            LocalDate endDate = view.getEndDate();
            BigDecimal total = getTotal(startDate, endDate, host);
            boolean success = view.displaySummary(startDate, endDate, total);

            if (success) {
                Reservation reservation = makeReservation(0, startDate, endDate, host, guest, total);
                Result<Reservation> result = reservationService.add(reservation);
                if (!result.isSuccess()) {
                    view.displayStatus(false, result.getErrorMessages() + "\n");

                } else {
                    String successMessage = String.format("Reservation ID: %s has been booked. Cheers!\n", result.getPayload().getReservationId());
                    view.displayStatus(true, successMessage);
                }
                view.returnToMainMenu();
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
        Reservation reservation = getHostReservation(guest, host);

        if (reservation != null) {
            view.displayReservationHeader(reservation);
            view.displayReservation(reservation);
            view.displayEditOrDeleteHeader(reservation, "Editing Reservation %s");
            boolean correct = true;

            while (correct) {
                reservation = view.update(reservation);
                BigDecimal total = getTotal(reservation.getStartDate(), reservation.getEndDate(), host);
                boolean success = view.displaySummary(reservation.getStartDate(), reservation.getEndDate(), total);

                if (success) {
                    reservation = makeReservation(reservation.getReservationId(), reservation.getStartDate(), reservation.getEndDate(),
                            host, guest, total);
                    Result<Reservation> result = reservationService.update(reservation);
                    if (!result.isSuccess()) {
                        view.displayStatus(false, result.getErrorMessages() + "\n");

                    } else {
                        String successMessage = String.format("Reservation ID: %s has been updated. Cheers!\n", result.getPayload().getReservationId());
                        view.displayStatus(true, successMessage);

                    }
                    view.returnToMainMenu();
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
        } else {
            view.returnToMainMenu();
        }

    }

    private void deleteReservation() throws DataAccessException {
        view.displayHeader(MainMenuOption.DELETE_RESERVATION.getOption());
        Guest guest = getGuest();
        Host host = getHost();
        Reservation reservation = getHostReservation(guest, host);
        LocalDate now = LocalDate.now();
        if (reservation != null) {
            if (reservation.getEndDate().isBefore(now) || reservation.getEndDate().equals(now)) {
                view.reservationInPast(guest, reservation);
            } else {
                view.displayReservationHeader(reservation);
                view.displayReservation(reservation);
                view.displayEditOrDeleteHeader(reservation, "Delete Reservation %s");
                boolean delete = view.deleteReservation(reservation);
                if (delete) {
                    Result<Reservation> result = reservationService.delete(reservation);
                    if (!result.isSuccess()) {
                        view.displayStatus(false, result.getErrorMessages() + "\n");

                    } else {
                        String successMessage = String.format("Reservation ID: %s has been deleted. Cheers!\n", result.getPayload().getReservationId());
                        view.displayStatus(true, successMessage);
                    }
                }
            }
        }
        view.returnToMainMenu();
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
            host = hostService.findByEmail(hostEmail.trim());
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
            guest = guestService.findByEmail(guestEmail.trim());
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

    private List<Reservation> getHostReservationList(Host host) throws DataAccessException {

        List<Reservation> result = reservationService.findReservationListByHostEmail(host.getHostEmail());

        if (result.size() == 0) {
            view.hostReservationListNotFound(host);
            return null;
        }

        return result;

    }

    private List<Reservation> getGuestReservationList(Guest guest) throws DataAccessException {

        List<Reservation> result = reservationService.findReservationListByGuestEmail(guest.getGuestEmail());

        if (result.size() == 0) {
            view.guestReservationListNotFound(guest);
            return null;
        }

        return result;

    }

    private Reservation getHostReservation(Guest guest, Host host) throws DataAccessException {
        Reservation reservation = reservationService.findReservationByGuestAndHostEmail(guest.getGuestEmail(),
                host.getHostEmail());

        if (reservation == null) {
            view.noReservationFound(guest, host);
            return null;
        }

        return reservation;
    }

    private List<Reservation> getStateReservation(String state) throws DataAccessException {
        List<Reservation> reservations = reservationService.findReservationListsByState(state);

        if (reservations.size() == 0) {
            view.stateReservationListNotFound(state);
            return null;
        }

        return reservations;
    }

}
