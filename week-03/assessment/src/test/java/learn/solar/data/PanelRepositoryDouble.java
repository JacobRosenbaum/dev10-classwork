package learn.solar.data;

import learn.solar.models.Panel;
import learn.solar.models.PanelMaterial;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PanelRepositoryDouble implements PanelRepository{

    @Override
    public List<Panel> findAll() throws DataAccessException {
        return null;
    }

    @Override
    public List<Panel> findByMaterial(PanelMaterial material) throws DataAccessException {
        return null;
    }

    @Override
    public List<Panel> findBySection(String section) throws DataAccessException {
        return null;
    }

    @Override
    public List<Panel> findTrackable(boolean trackable) throws DataAccessException {
        return null;
    }

    @Override
    public Panel findById(int panelId) throws DataAccessException {
        return null;
    }

    @Override
    public Panel add(Panel panel) throws DataAccessException {
        return null;
    }

    @Override
    public boolean update(Panel panel) throws DataAccessException {
        return false;
    }

    @Override
    public boolean deleteById(int panelId) throws DataAccessException {
        return false;
    }

    @Override
    public boolean deleteByPanel(String section, int row, int column) throws DataAccessException {
        return false;
    }
}