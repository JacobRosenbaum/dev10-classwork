package learn.solar.domain;

import learn.solar.data.DataAccessException;
import learn.solar.data.PanelFileRepository;
import learn.solar.data.PanelRepository;
import learn.solar.models.Panel;
import learn.solar.models.PanelMaterial;

import java.util.List;

public class PanelService {

    private final PanelRepository repository;

    public PanelService(PanelRepository repository) {
        this.repository = repository;
    }

    public List<Panel> findAll() throws DataAccessException {
        return repository.findAll();
    }

    public List<Panel> findByMaterial(PanelMaterial material) throws DataAccessException {
        return repository.findByMaterial(material);
    }

    public List<Panel> findBySection(String section) throws DataAccessException {
        return repository.findBySection(section);
    }

    public List<Panel> findTrackable(boolean trackable) throws DataAccessException {
        return repository.findTrackable(trackable);
    }

    public Panel findById(int panelId) throws DataAccessException {
        return repository.findById(panelId);
    }

    public PanelResult add(Panel panel) throws DataAccessException {
        PanelResult result = validate(panel);

        if (result.isSuccess()) {
            if (panel.getPanelId() > 0) {
                result.addErrorMessage("Result ID should not be set");
                return result;
            }
            panel = repository.add(panel);
            result.setPanel(panel);
        }
        return result;
    }

    public PanelResult update(Panel panel) throws DataAccessException {
        PanelResult result = validate(panel);

        if (panel.getPanelId() <= 0) {
            result.addErrorMessage("Panel ID is required");
        }

        if (result.isSuccess()) {
            if (repository.update(panel)) {
                result.setPanel(panel);
            } else {
                String message = String.format("Memory ID: %s was not found", panel.getPanelId());
                result.addErrorMessage(message);
            }
        }

        return result;
    }

    public PanelResult deleteById(int panelId) throws DataAccessException {
        PanelResult result = new PanelResult();

        if (!repository.deleteById(panelId)) {
            String message = String.format("Memory ID: %s was not found", panelId);
            result.addErrorMessage(message);
        }
        return result;
    }

    public PanelResult deleteByPanel(int panelId) throws DataAccessException {
        PanelResult result = new PanelResult();

        if (!repository.deleteById(panelId)) {
            String message = String.format("Memory ID: %s was not found", panelId);
            result.addErrorMessage(message);
        }
        return result;
    }

    private PanelResult validate(Panel panel) throws DataAccessException {
        List<Panel> all = repository.findAll();
        PanelResult result = new PanelResult();

        if (panel == null) {
            result.addErrorMessage("Panel cannot be null");
            return result;
        }

        if (panel.getSection() == null || panel.getSection().trim().length() == 0) {
            result.addErrorMessage("Section cannot be blank");
        }

        if (panel.getRow() > 250 || panel.getRow() < 1) {
            result.addErrorMessage("Row must be between [1-250]");
        }

        if (panel.getColumn() > 250 || panel.getColumn() < 1) {
            result.addErrorMessage("Column must be between [1-250]");
        }

        if (panel.getYearInstalled() > 2020) {
            result.addErrorMessage("Year installed must be in the past");
        }

        if (panel.getMaterial() == null) {
            result.addErrorMessage("Material is required");
        }

        for (int i = 0; i < all.size(); i++) {
            if (panel.getRow() == all.get(i).getRow() &&
                    panel.getColumn() == all.get(i).getColumn() &&
                    panel.getSection().equals(all.get(i).getSection())) {
                result.addErrorMessage("Cannot enter duplicate Panel with same Section, Row, and Column");
            }

        }
        return result;
    }

}
