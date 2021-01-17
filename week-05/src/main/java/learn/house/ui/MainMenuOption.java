package learn.house.ui;

public enum MainMenuOption {
    EXIT("Exit"),
    VIEW_RESERVATIONS_BY_HOST("View Reservations By Host"),
    VIEW_RESERVATIONS_BY_GUEST("View Reservations By Guest"),
    VIEW_RESERVATIONS_BY_STATE("View Reservations By State"),
    ADD_RESERVATION("Make a Reservation"),
    UPDATE_RESERVATION("Edit a Reservation"),
    DELETE_RESERVATION("Cancel a Reservation");

    private String option;

    MainMenuOption(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }
}
