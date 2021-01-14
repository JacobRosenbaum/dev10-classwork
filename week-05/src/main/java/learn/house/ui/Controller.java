package learn.house.ui;

import learn.house.data.DataAccessException;
import learn.house.domain.ReservationService;
import learn.house.models.Reservation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Controller {
    private final ReservationService service;
    private final View view;

    public Controller(ReservationService service, View view) {
        this.service = service;
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
                    break;
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
        List<Reservation> reservations = service.findReservationListByHostEmail(hostEmail);
        view.displayReservationsByHost(reservations);
        view.enterToContinue();
    }
}
