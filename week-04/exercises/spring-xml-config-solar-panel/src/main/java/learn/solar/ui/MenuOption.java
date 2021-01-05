package learn.solar.ui;

public enum MenuOption {
    DISPLAY_BY_SECTION("Find Panels by Section"),
    ADD_PANEL("Add a Panel"),
    UPDATE_PANEL("Update a Panel"),
    DELETE_PANEL("Delete a Panel"),
    EXIT("Exit");

    private String option;

    MenuOption(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }
}
