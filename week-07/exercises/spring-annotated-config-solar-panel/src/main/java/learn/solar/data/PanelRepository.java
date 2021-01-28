package learn.solar.data;

import learn.solar.models.Panel;
import learn.solar.models.PanelMaterial;

import java.util.List;

public interface PanelRepository {

    List<Panel> findAll();

    List<Panel> findBySection(String section);

    Panel findById(int panelId);

    Panel add(Panel panel);

    boolean update(Panel panel);

    boolean deleteById(int panelId);

//    boolean deleteByPanel(String section, int row, int column) throws DataAccessException;


}
