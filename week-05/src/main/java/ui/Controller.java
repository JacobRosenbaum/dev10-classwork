package ui;

import data.DataAccessException;
import domain.ReservationService;
import domain.Result;
import org.springframework.stereotype.Component;

@Component
public class Controller {
    private final ReservationService service;
    private final View view;

    public Controller(ReservationService service, View view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        view.displayHeader("Welcome To 'Don't Wreck My House.'");

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
                case VIEW_RESERVATION_BY_HOST:
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
}
