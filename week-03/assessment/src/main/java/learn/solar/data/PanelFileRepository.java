package learn.solar.data;

import learn.solar.models.Panel;
import learn.solar.models.PanelMaterial;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PanelFileRepository implements PanelRepository {
    private static final String DELIMITER = ",";
    private static final String DELIMITER_REPLACEMENT = "~~~";
    private static final String HEADER = "panel_id,section,row,column,material,year_installed,is_tracking";
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
    public List<Panel> findByType(PanelMaterial type) {
        return null;
    }

    @Override
    public Panel findById(int panelId) {
        return null;
    }

    @Override
    public Panel add(Panel panel) {
        return null;
    }

    @Override
    public boolean update(Panel panel) {
        return false;
    }

    @Override
    public boolean deleteById(int panelId) {
        return false;
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
