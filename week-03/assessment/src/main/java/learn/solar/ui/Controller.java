package learn.solar.ui;

import learn.solar.data.DataAccessException;
import learn.solar.domain.PanelService;
import learn.solar.models.Panel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Controller {

    private final PanelService service;
    private final View view;

    public Controller(PanelService service, View view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        try {
            runMenu();
        } catch (DataAccessException ex) {
            view.printHeader("Fatal error " + ex);
        }
        view.printHeader("Goodbye");
    }

    private void runMenu() throws DataAccessException {
        MenuOption option;

        do {
            option = view.displayMenuAndSelect();

            switch (option) {
                case DISPLAY_BY_SECTION:
                    displayPanels();
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
                    view.printHeader("Good day to you sir...");
                    break;
            }

        } while (option != MenuOption.EXIT);

    }

    private void displayPanels() throws DataAccessException {
        view.printHeader(MenuOption.DISPLAY_BY_SECTION.getOption());
        displayAllSectionTitles();
    }

    private void addPanel() {
    }

    private void updatePanel() {
    }

    private void deletePanel() {
    }

    private void displayAllPanels() throws DataAccessException {
        List<Panel> all = service.findAll();
//        view.printAllPanels(panels);
    }

    private void displayAllSectionTitles() throws DataAccessException {
        List<Panel> all = service.findAll();
//        ArrayList<Panel> panels = new ArrayList();
        ArrayList<String> section = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            String sectionTitle = all.get(i).getSection();
            if(!section.contains(sectionTitle))
                section.add(sectionTitle);
//            section.add(all.get(i).getSection());
//            for (int j = 0; j < section.size(); j++) {
//                if (all.get(j).getSection().equals(section.get(j)))
//                    section.remove(all.get(j).getSection());
//            }
        }
        view.printAllSectionTitles(section);
    }
}
