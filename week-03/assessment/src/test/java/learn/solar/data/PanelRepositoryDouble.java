package learn.solar.data;

import learn.solar.models.Panel;
import learn.solar.models.PanelMaterial;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PanelRepositoryDouble implements PanelRepository {
    private ArrayList<Panel> panels = new ArrayList<>();

    public PanelRepositoryDouble() {

        panels.add(new Panel(1, "Camp Jacob", 2, 4, PanelMaterial.AMORPHOUS_SILICON, 2018, true));
        panels.add(new Panel(2, "Camp Rosenbaum", 4, 6, PanelMaterial.COPPER_INDIUM_GALLIUM_SELENIDE, 2011, false));
        panels.add(new Panel(3, "Camp Aaron", 8, 2, PanelMaterial.MONOCRYSTALLINE_SILICON, 2005, true));

    }

    @Override
    public List<Panel> findAll() throws DataAccessException {
        return new ArrayList<>(panels);
    }

    @Override
    public List<Panel> findByMaterial(PanelMaterial material) throws DataAccessException {
        return null;
    }

    @Override
    public List<Panel> findBySection(String section) throws DataAccessException {
        ArrayList<Panel> result = new ArrayList<>();

        for (Panel panel : panels) {
            if (panel.getSection().equals(section)) {
                result.add(panel);
            }
        }
        return result;
    }

    @Override
    public List<Panel> findTrackable(boolean trackable) throws DataAccessException {
        return null;
    }

    @Override
    public Panel findById(int panelId) throws DataAccessException {
        for (Panel panel : panels) {
            if (panel.getPanelId() == panelId) {
                return panel;
            }
        }
        return null;
    }

    @Override
    public Panel add(Panel panel) throws DataAccessException {
        return panel;
    }

    @Override
    public boolean update(Panel panel) throws DataAccessException {
        return panel.getPanelId() == 3;
    }

    @Override
    public boolean deleteById(int panelId) throws DataAccessException {
        return panelId == 2;
    }

    @Override
    public boolean deleteByPanel(String section, int row, int column) throws DataAccessException {
        if (section.equals("Camp Aaron") && row == 8 && column == 2) {
            return true;
        }
        return false;
    }
}