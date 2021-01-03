package learn.solar.ui;

import learn.solar.data.DataAccessException;
import learn.solar.domain.PanelResult;
import learn.solar.domain.PanelService;
import learn.solar.models.Panel;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private final PanelService service;
    private final View view;

    public Controller(PanelService service, View view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        view.printHeader("Welcome To The Solar Farm.");

        try {
            runMenu();
        } catch (DataAccessException ex) {
            view.printHeader("Fatal error " + ex);
        }
        view.printHeader("Good day to you sir.");
    }

    private void runMenu() throws DataAccessException {
        MenuOption option;

        do {
            option = view.displayMenuAndSelect();

            switch (option) {
                case DISPLAY_BY_SECTION:
                    displayBySection();
                    break;
                case ADD_PANEL:
                    addPanel();
                    break;
                case UPDATE_PANEL:
                    updatePanel();
                    break;
                case DELETE_PANEL:
                    deletePanel();
                    break;
                case EXIT:
                    break;
            }

        } while (option != MenuOption.EXIT);

    }

    private void displayBySection() throws DataAccessException {
        view.printHeader(MenuOption.DISPLAY_BY_SECTION.getOption());
        displayAllSectionTitles();
    }

    private void addPanel() throws DataAccessException {
        view.printHeader(MenuOption.ADD_PANEL.getOption());
        Panel panel = view.buildPanel();
        PanelResult result = service.add(panel);
        view.printResult(result, "\nPanel ID: %s was successfully added to Section: %s. Cheers%n");
    }

    private void updatePanel() throws DataAccessException {
        view.printHeader(MenuOption.UPDATE_PANEL.getOption());
        view.printMessage("Which Section would like to Update?");

        String sectionName = displayAllSectionTitles();
        Panel selectedPanel = view.selectPanel(service.findBySection(sectionName));

        Panel panel = view.updatePanel(selectedPanel);
        if (panel != null) {
            PanelResult result = service.update(panel);
            view.printResult(result, "\nPanel ID: %s was successfully updated in Section: %s. Cheers%n");
        }
    }

    private void deletePanel() {
    }

    private String displayAllSectionTitles() throws DataAccessException {
        List<Panel> all = service.findAll();
        ArrayList<String> section = new ArrayList<>();
        String sectionName;
        List<Panel> panel = new ArrayList<>();

        for (int i = 0; i < all.size(); i++) {
            String sectionTitle = all.get(i).getSection();
            if (!section.contains(sectionTitle))
                section.add(sectionTitle);
        }
        sectionName = view.printAllSectionTitles(section);

        for (int j = 0; j < section.size(); j++) {
            if (sectionName.equals(section.get(j))) {
                panel = service.findBySection(sectionName);
            }
        }
        view.printAllPanelsBySection(panel);
        return sectionName;
    }
}
