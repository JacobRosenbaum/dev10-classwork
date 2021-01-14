package learn.house.ui;

public enum MainMenuOption {
    EXIT("Exit"),
    VIEW_RESERVATIONS_BY_HOST("View Reservations By Host"),
    ADD_RESERVATION("Add a Reservation"),
    UPDATE_RESERVATION("Update a Reservation"),
    DELETE_RESERVATION("Delete a Reservation");

    private String option;

    MainMenuOption(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }
}
