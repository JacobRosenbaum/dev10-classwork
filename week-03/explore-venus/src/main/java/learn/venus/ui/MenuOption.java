package learn.venus.ui;

public enum MenuOption {

    EXIT("Exit"),
    DISPLAY_ORBITERS("Display Orbiters"),
    CREATE_ORBITER("Create an Orbiter"),
    UPDATE_ORBITER("Update an Orbiter"),
    DELETE_ORBITER("Delete an Orbiter");

    private final String option;

    MenuOption(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }

}
