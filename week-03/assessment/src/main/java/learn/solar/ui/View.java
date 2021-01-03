package learn.solar.ui;

import learn.solar.domain.PanelResult;
import learn.solar.models.Panel;
import learn.solar.models.PanelMaterial;

import java.util.List;
import java.util.Scanner;

public class View {
    private final Scanner console = new Scanner(System.in);

    public MenuOption displayMenuAndSelect() {
        MenuOption[] values = MenuOption.values();
        printHeader("Main Menu");
        for (int i = 0; i < values.length; i++) {
            System.out.printf("%s. %s\n", i + 1, values[i].getOption());
        }
        int index = readInt("\nSelect an option [1-5]: ", 1, 5);
        return values[index - 1];
    }

    public String printAllSectionTitles(List<String> section) {
        for (int i = 0; i < section.size(); i++) {
            System.out.printf("%s. %s%n", i + 1, section.get(i));
        }
        int index = readInt("\nSelect an Section [1-" + section.size() + "]: ", 1, section.size());
        return section.get(index - 1);
    }

    public void printAllPanelsBySection(List<Panel> panels) {
        System.out.println(printSectionGraphHeader(panels));
        for (int i = 0; i < panels.size(); i++) {
            System.out.println(panelToGraph(panels.get(i)));
        }
    }

    public void printHeader(String header) {
        System.out.println("\n" + header);
        System.out.println("=".repeat(header.length()));
    }

    public Panel buildPanel() {
        Panel panel = new Panel();
        panel.setSection(readRequiredString("Section: "));
        panel.setRow(readRequiredRowOrCol("Row: "));
        panel.setColumn(readRequiredRowOrCol("Column: "));
        panel.setMaterial(readMaterial());
        panel.setYearInstalled(readRequiredInstallationYear("Installation Year: "));
        panel.setTracking(readRequiredTrackable("Tracked [y/n]: "));
        return panel;
    }

    public Panel selectPanel(List<Panel> panels) {
        boolean isValid = false;
        Panel selectedPanel = null;
        do {
            int panelId = readInt("\nTo update a Panel, enter a Panel ID: ");
            for (Panel panel : panels) {

                if (panel.getPanelId() == panelId) {
                    selectedPanel = panel;
                    System.out.printf("%nEditing Panel ID: %s, in %s%n", panel.getPanelId(), panel.getSection());
                    System.out.println("Press [Enter] to keep original value\n");
                    isValid = true;
                    break;
                }
            }
            if (selectedPanel == null) {
                printMessage("Panel ID " + panelId + " was not found");
            }
        } while (!isValid);
        return selectedPanel;
    }

    public Panel updatePanel(Panel panel) {
        String section = readString("Section (" + panel.getSection() + "): ");
        if (section.trim().length() > 0) {
            panel.setSection(section);
        }

        int row = readRowOrCol("Row (" + panel.getRow() + "): ");
        if (row != 0) {
            panel.setRow(row);
        }

        int col = readRowOrCol("Column (" + panel.getColumn() + "): ");
        if (col != 0) {
            panel.setColumn(col);
        }

        System.out.println("Material (" + panel.getMaterial().getMaterialName() + "): \n");
        panel.setMaterial(readMaterial());

        int year = readInstallationYear("Installation Year (" + panel.getYearInstalled() + "): ");
        if (year != 0) {
            panel.setYearInstalled(year);
        }

        String isTrackable = readTrackable("Tracked (" + panel.isTracking() + ") [y/n]: ");
        if (isTrackable.equalsIgnoreCase("true")) {
            panel.setTracking(true);
        } else if (isTrackable.equalsIgnoreCase("false")) {
            panel.setTracking(false);
        }

        return panel;
    }

    public void printResult(PanelResult result, String successMessage) {
        if (result.isSuccess()) {
            if (result.getPanel() != null) {
                System.out.printf(successMessage, result.getPanel().getPanelId(), result.getPanel().getSection());
            }
        } else {
            printHeader("Errors!");
            for (String errorMessage : result.getMessages()) {
                System.out.printf("- %s%n", errorMessage);
            }
        }
    }

    public void printMessage(String message) {
        System.out.println();
        System.out.println(message);
    }


    private String panelToGraph(Panel panel) {
        String tracking;
        if (panel.isTracking()) {
            tracking = "yes";
        } else {
            tracking = "no";
        }

        String id = null;
        if (String.valueOf(panel.getPanelId()).length() == 1) {
            id = String.format("%s   ", panel.getPanelId());
        } else if ((String.valueOf(panel.getPanelId()).length() == 2)) {
            id = String.format("%s  ", panel.getPanelId());
        }

        String row = null;
        if (String.valueOf(panel.getRow()).length() == 1) {
            row = String.format("%s    ", panel.getRow());
        } else if ((String.valueOf(panel.getRow()).length() == 2)) {
            row = String.format("%s   ", panel.getRow());
        } else if ((String.valueOf(panel.getRow()).length() == 3)) {
            row = String.format("%s  ", panel.getRow());
        }

        String column = null;
        if (String.valueOf(panel.getColumn()).length() == 1) {
            column = String.format("%s    ", panel.getColumn());
        } else if ((String.valueOf(panel.getColumn()).length() == 2)) {
            column = String.format("%s   ", panel.getColumn());
        } else if ((String.valueOf(panel.getColumn()).length() == 3)) {
            column = String.format("%s  ", panel.getColumn());
        }

        return String.format("%s%s%s   %s   %s    %s",
                id,
                row,
                column,
                panel.getMaterial().getShortHandName(),
                panel.getYearInstalled(),
                tracking
        );
    }

    private String printSectionGraphHeader(List<Panel> panel) {
        return String.format("%nPanels in %s%n%s  %s  %s  %s  %s  %s",
                panel.get(0).getSection(),
                "ID",
                "Row",
                "Column",
                "Material",
                "Year",
                "Tracking");
    }

    private String readString(String message) {
        System.out.print(message);
        return console.nextLine();
    }

    private String readRequiredString(String message) {
        String result;
        do {
            result = readString(message);
            if (result.trim().length() == 0) {
                System.out.println("Value is required.");
            }
        } while (result.trim().length() == 0);
        return result;
    }

    private int readInt(String message) {
        String input = null;
        int result = 0;
        boolean isValid = false;
        do {
            try {
                input = readRequiredString(message);
                result = Integer.parseInt(input);
                isValid = true;
            } catch (NumberFormatException ex) {
                System.out.printf("%s is not a valid number.%n", input);
            }
        } while (!isValid);

        return result;
    }

    private int readInt(String message, int min, int max) {
        int result;
        do {
            result = readInt(message);
            if (result < min || result > max) {
                System.out.printf("Value must be between %s and %s.%n", min, max);
            }
        } while (result < min || result > max);
        return result;
    }

    private int readRowOrCol(String message) {
        String input = null;
        int result = 0;
        boolean isValid = false;
        do {
            try {
                input = readString(message);
                if (input.equals("")) {
                    return result;
                }
                result = Integer.parseInt(input);
                if (result < 1 || result > 250) {
                    System.out.println("Value must be between 1 and 250.");
                } else {
                    return result;
                }

            } catch (NumberFormatException ex) {
                System.out.printf("%s is not a valid number.%n", input);
            }
        } while (!isValid);
        return result;
    }

    private int readRequiredRowOrCol(String message) {
        int result;
        do {
            result = readInt(message);
            if (result < 1 || result > 250) {
                System.out.println("Value must be between 1 and 250.");
            }
        } while (result < 1 || result > 250);
        return result;
    }

    private int readInstallationYear(String message) {
        String input = null;
        int year = 0;
        boolean isValid = false;
        do {
            try {
                input = readString(message);
                if (input.equals("")) {
                    return year;
                }
                year = Integer.parseInt(input);
                if (year > 2020) {
                    System.out.println("Installation Year must be in the past.");
                } else if (year < 1800) {
                    System.out.println("Installation Year must be after 1800.");
                } else {
                    return year;
                }

            } catch (NumberFormatException ex) {
                System.out.printf("%s is not a valid number.%n", input);
            }
        } while (!isValid);
        return year;
    }

    private int readRequiredInstallationYear(String message) {
        int year;
        do {
            year = readInt(message);
            if (year > 2020) {
                System.out.println("Installation Year must be in the past.");
            } else if (year < 1800) {
                System.out.println("Installation Year must be after 1800.");
            }
        } while (year < 1800 || year > 2020);
        return year;
    }

    public PanelMaterial readMaterial() {
        int index = 1;
        for (PanelMaterial material : PanelMaterial.values()) {
            System.out.printf("%s. %s%n", index++, material.getMaterialName());
        }
        index--;
        String prompt = String.format("Select Panel Material [1-%s]: ", index);
        return PanelMaterial.values()[readInt(prompt, 1, index) - 1];
    }

    private String readTrackable(String prompt) {
        boolean isValid = true;
        do {
            String result = readString(prompt);
            if (result.equalsIgnoreCase("y")) {
                return "true";
            } else if (result.equalsIgnoreCase("n")) {
                return "false";
            } else if (result.equalsIgnoreCase("")) {
                return result;
            } else {
                System.out.println("Please choose either 'y' or 'n'");
            }
        } while (isValid);

        return null;
    }

    private boolean readRequiredTrackable(String prompt) {
        boolean isValid = true;
        do {
            String result = readRequiredString(prompt);
            if (result.equalsIgnoreCase("y")) {
                return true;
            } else if (result.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Please choose either 'y' or 'n'");
            }
        } while (isValid);

        return false;
    }

}
