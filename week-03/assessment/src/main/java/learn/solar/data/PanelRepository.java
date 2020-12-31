package learn.solar.data;

import learn.solar.models.Panel;
import learn.solar.models.PanelMaterial;

import java.util.List;

public interface PanelRepository {

    List<Panel> findAll() throws DataAccessException;

    List<Panel> findByType(PanelMaterial type);

    Panel findById(int panelId);

    Panel add(Panel panel);

    boolean update(Panel panel);

    boolean deleteById(int panelId);


}
