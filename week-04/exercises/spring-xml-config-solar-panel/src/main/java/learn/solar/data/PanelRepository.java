package learn.solar.data;

import learn.solar.models.Panel;
import learn.solar.models.PanelMaterial;

import java.util.List;

public interface PanelRepository {

    List<Panel> findAll() throws DataAccessException;

    List<Panel> findBySection(String section) throws DataAccessException;

    Panel findById(int panelId) throws DataAccessException;

    Panel add(Panel panel) throws DataAccessException;

    boolean update(Panel panel) throws DataAccessException;

    boolean deleteById(int panelId) throws DataAccessException;

    boolean deleteByPanel(String section, int row, int column) throws DataAccessException;


}
