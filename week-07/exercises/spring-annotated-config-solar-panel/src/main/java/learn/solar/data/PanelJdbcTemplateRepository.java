package learn.solar.data;

import learn.solar.models.Panel;
import learn.solar.models.PanelMaterial;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Primary
@Repository
public class PanelJdbcTemplateRepository implements PanelRepository {

    private final JdbcTemplate template;

    private final RowMapper<Panel> mapper = ((resultSet, rowNum) -> {
        Panel panel = new Panel();
        panel.setPanelId(resultSet.getInt("panel_id"));
        panel.setSection(resultSet.getString("section"));
        panel.setRow(resultSet.getInt("panel_row"));
        panel.setColumn(resultSet.getInt("panel_column"));
        panel.setYearInstalled(resultSet.getInt("year_installed"));
        if (resultSet.getInt("sun_tracking") == 1) {
            panel.setTracking(true);
        } else if (resultSet.getInt("sun_tracking") == 0) {
            panel.setTracking(false);
        }
        panel.setMaterial(PanelMaterial.findByValue(resultSet.getInt("panel_material_id")));
        return panel;
    });

    public PanelJdbcTemplateRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Panel> findAll() {
        final String sql = "select panel_id, section, panel_row, panel_column," +
                " year_installed, sun_tracking, panel_material_id from panel";
        return template.query(sql, mapper);
    }

    @Override
    public List<Panel> findBySection(String section) {
        final String sql = "select * from panel where section = ?;";

        try {
            // 2. Parameters always follow SQL and mappers.
            // Any number of parameters is allowed.

            List<Map<String, Object>> rows = template.queryForList(sql, section);
            List<Panel> panels = new ArrayList<>();
            for (Map<String, Object> row : rows) {
                Panel panel = new Panel();
                panel.setPanelId((Integer) row.get("panel_id"));
                panel.setSection((String) row.get("section"));
                panel.setRow((Integer) row.get("panel_row"));
                panel.setColumn((Integer) row.get("panel_column"));
                panel.setYearInstalled((Integer) row.get("year_installed"));
                if ((Integer) row.get("sun_tracking") == 1) {
                    panel.setTracking(true);
                } else if ((Integer) row.get("sun_tracking") == 0) {
                    panel.setTracking(false);
                }
                panel.setMaterial(PanelMaterial.findByValue((Integer) row.get("panel_material_id")));
                panels.add(panel);
            }
            return panels;

        } catch (EmptyResultDataAccessException ex) {
            // 3. queryForObject can throw an unchecked exception
            // If the ResultSet is empty, it just means the pet with the id wasn't found.
            // So returning null is valid.
            return null;
        }
    }

    public Panel findBySectionTest(String section) {
        final String sql = "select * from panel where section = ?;";
        try {
            // 2. Parameters always follow SQL and mappers.
            // Any number of parameters is allowed.
            return template.queryForObject(sql, mapper, section);
        } catch (EmptyResultDataAccessException ex) {
            // 3. queryForObject can throw an unchecked exception
            // If the ResultSet is empty, it just means the pet with the id wasn't found.
            // So returning null is valid.
            return null;
        }
    }


    @Override
    public Panel findById(int panelId) {
        final String sql = "select * from panel where panel_id = ?;";
        try {
            // 2. Parameters always follow SQL and mappers.
            // Any number of parameters is allowed.
            return template.queryForObject(sql, mapper, panelId);
        } catch (EmptyResultDataAccessException ex) {
            // 3. queryForObject can throw an unchecked exception
            // If the ResultSet is empty, it just means the pet with the id wasn't found.
            // So returning null is valid.
            return null;
        }
    }


    @Override
    public Panel add(Panel panel) {
        final String sql = "insert into panel(section, panel_row," +
                " panel_column, year_installed, sun_tracking, panel_material_id)" +
                " values (?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, panel.getSection());
            ps.setInt(2, panel.getRow());
            ps.setInt(3, panel.getColumn());
            ps.setInt(4, panel.getYearInstalled());
            ps.setBoolean(5, panel.isTracking());
            ps.setInt(6, panel.getMaterial().getValue());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        panel.setPanelId(keyHolder.getKey().intValue());
        return panel;
    }

    @Override
    public boolean update(Panel panel) {
        final String sql = "update panel set "
                + "section = ?, "
                + "panel_row = ?, "
                + "panel_column = ?, "
                + "year_installed = ?, "
                + "sun_tracking = ?, "
                + "panel_material_id = ? "
                + "where panel_id = ?;";

        int rowsUpdated = template.update(sql,
                panel.getSection(), panel.getRow(), panel.getColumn(),
                panel.getYearInstalled(), panel.isTracking(),
                panel.getMaterial().getValue(), panel.getPanelId());

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteById(int panelId) {
        final String sql = "delete from panel where panel_id = ?;";
        return template.update(sql, panelId) > 0;
    }

//    @Override
//    public boolean deleteByPanel(String section, int row, int column) throws DataAccessException {
//        return false;
//    }
}
