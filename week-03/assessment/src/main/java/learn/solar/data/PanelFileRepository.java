package learn.solar.data;

import learn.solar.models.Panel;
import learn.solar.models.PanelMaterial;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PanelFileRepository implements PanelRepository {
    private static final String DELIMITER = ",";
    private static final String DELIMITER_REPLACEMENT = "~~~";
    private static final String HEADER = "Panel_ID,Section,Row,Column,Material,Year_Installed,Is_Tracking";
    private String filePath;

    public PanelFileRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Panel> findAll() throws DataAccessException {
        ArrayList<Panel> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                Panel panel = deserialize(line);
                if (panel != null) {
                    result.add(panel);
                }
            }
        } catch (FileNotFoundException ex) {
            // If no file is found - that means we have no panels yet
        } catch (IOException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }
        return result;
    }

    @Override
    public List<Panel> findByMaterial(PanelMaterial material) throws DataAccessException {
        ArrayList<Panel> result = new ArrayList<>();

        for (Panel panel : findAll()) {
            if (panel.getMaterial().getMaterialName() == material.getMaterialName()) {
                result.add(panel);
            }
        }
        return result;
    }


    @Override
    public List<Panel> findBySection(String section) throws DataAccessException {
        ArrayList<Panel> result = new ArrayList<>();

        for (Panel panel : findAll()) {
            if (panel.getSection() == section) {
                result.add(panel);
            }
        }
        return result;
    }

    @Override
    public List<Panel> findTrackable(boolean trackable) throws DataAccessException {
        List<Panel> all = findAll();
        ArrayList<Panel> result = new ArrayList<>();
        for (Panel panel : all) {
            if (panel.isTracking() == trackable) {
                result.add(panel);
            }
        }
        return result;
    }


    @Override
    public Panel findById(int panelId) throws DataAccessException {
        for (Panel panel : findAll()) {
            if (panel.getPanelId() == panelId) {
                return panel;
            }
        }
        return null;
    }

    @Override
    public Panel add(Panel panel) throws DataAccessException {
        List<Panel> all = findAll();
        panel.setPanelId(getNextId(all));
        all.add(panel);
        writeToFile(all);
        return panel;
    }

    @Override
    public boolean update(Panel panel) throws DataAccessException {
        List<Panel> all = findAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getPanelId() == panel.getPanelId()) {
                all.set(i, panel);
                writeToFile(all);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(int panelId) throws DataAccessException {
        List<Panel> all = findAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getPanelId() == panelId) {
                all.remove(i);
                writeToFile(all);
                return true;
            }
        }
        return false;

    }

    private int getNextId(List<Panel> panels) {
        int maxPanelId = 0;
        for (Panel panel : panels) {
            if (maxPanelId < panel.getPanelId()) {
                maxPanelId = panel.getPanelId();
            }
        }
        return maxPanelId + 1;
    }

    private void writeToFile(List<Panel> panels) throws DataAccessException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(HEADER);
            for (Panel panel : panels) {
                writer.println(serialize(panel));
            }
        } catch (IOException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }
    }

    private String serialize(Panel panel) {
        return String.format("%s,%s,%s,%s,%s,%s,%s",
                panel.getPanelId(),
                cleanField(panel.getSection()),
                panel.getRow(),
                panel.getColumn(),
                panel.getMaterial(),
                panel.getYearInstalled(),
                panel.isTracking());
    }

    private Panel deserialize(String line) {
        String[] fields = line.split(DELIMITER, -1);

        if (fields.length == 7) {
            Panel panel = new Panel();
            panel.setPanelId(Integer.parseInt(fields[0]));
            panel.setSection(restoreField(fields[1]));
            panel.setRow(Integer.parseInt(fields[2]));
            panel.setColumn(Integer.parseInt(fields[3]));
            panel.setMaterial(PanelMaterial.valueOf(fields[4]));
            panel.setYearInstalled(Integer.parseInt(fields[5]));
            panel.setTracking("true".equals(fields[6]));
            return panel;
        }
        return null;

    }

    private String cleanField(String value) {
        return value.replace(DELIMITER, DELIMITER_REPLACEMENT);
    }

    private String restoreField(String value) {
        return value.replace(DELIMITER_REPLACEMENT, DELIMITER);
    }

}
