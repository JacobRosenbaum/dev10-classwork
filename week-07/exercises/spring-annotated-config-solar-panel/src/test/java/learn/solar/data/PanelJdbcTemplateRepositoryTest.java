package learn.solar.data;

import learn.solar.models.Panel;
import learn.solar.models.PanelMaterial;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;


import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PanelJdbcTemplateRepositoryTest {

    @Autowired
    PanelJdbcTemplateRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    boolean hasSetUp = false;

    @BeforeEach
    void setUp(){
        if (!hasSetUp){
            hasSetUp = true;
            jdbcTemplate.update("call set_known_good_state();");
        }
    }

    @Test
    void findAllReturnsProperFieldCount() {
        List<Panel> all = repository.findAll();

        assertNotNull(all);
        assertTrue(all.size() >= 3);
    }

    @Test
    void findAllReturnsProperData() {
        List<Panel> all = repository.findAll();

        assertEquals("Camp Jacob", all.get(0).getSection());
    }

    @Test
    void shouldFindBySection() {
        List<Panel> panels = repository.findBySection("Camp Rosenbaum");

        assertEquals(1, panels.size());
    }

    @Test
    void shouldFindById() {
        Panel id = repository.findById(2);

        assertEquals("Camp Rosenbaum", id.getSection());
    }

    @Test
    void shouldAdd() {
        Panel panel = new Panel();

        panel.setSection("Camp Jacob");
        panel.setRow(1);
        panel.setColumn(7);
        panel.setMaterial(PanelMaterial.MONOCRYSTALLINE_SILICON);
        panel.setYearInstalled(2017);
        panel.setTracking(true);

        Panel actual = repository.add(panel);

        assertNotNull(actual);
        assertTrue(actual.getPanelId() >= 4);
    }

    @Test
    void shouldUpdate() {
        Panel panel = new Panel();

        panel.setPanelId(3);
        panel.setSection("Camp Jacob");
        panel.setRow(1);
        panel.setColumn(7);
        panel.setMaterial(PanelMaterial.MONOCRYSTALLINE_SILICON);
        panel.setYearInstalled(2017);
        panel.setTracking(false);

        boolean success = repository.update(panel);
        assertTrue(success);

        Panel actual = repository.findById(3);
        assertNotNull(actual);
        assertEquals("Camp Jacob", actual.getSection());
        assertEquals(7, actual.getColumn());
        assertEquals(2017, actual.getYearInstalled());
    }

    @Test
    void shouldDeleteExistingPanelById() {
        boolean actual = repository.deleteById(3);
        assertTrue(actual);

        Panel panel = repository.findById(3);
        assertNull(panel);
    }
}