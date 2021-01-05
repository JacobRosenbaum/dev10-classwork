package learn.solar.data;

import learn.solar.models.Panel;
import learn.solar.models.PanelMaterial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PanelFileRepositoryTest {

    private static final String SEED_PATH = "./data/panels-seed.csv";
    private static final String TEST_PATH = "./data/panels-test.csv";

    private PanelFileRepository repository = new PanelFileRepository(TEST_PATH);

    @BeforeEach
    void setUp() throws IOException {
        Files.copy(
                Paths.get(SEED_PATH),
                Paths.get(TEST_PATH),
                StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void findALlShouldReturnCorrectNumberOfPanels() throws DataAccessException {
        List<Panel> all = repository.findAll();

        assertNotNull(all);
        assertEquals(3, all.size());
    }

    @Test
    void findAllShouldReturnProperPanel() throws DataAccessException {
        List<Panel> all = repository.findAll();

        assertEquals(2, all.get(1).getPanelId());
        assertEquals("Camp Rosenbaum", all.get(1).getSection());
        assertEquals(4, all.get(1).getRow());
        assertEquals(6, all.get(1).getColumn());
        assertEquals(PanelMaterial.COPPER_INDIUM_GALLIUM_SELENIDE, all.get(1).getMaterial());
        assertEquals(2011, all.get(1).getYearInstalled());
        assertEquals(false, all.get(1).isTracking());

    }

    @Test
    void shouldFindBySection() throws DataAccessException {
        List<Panel> campJacob = repository.findBySection("Camp Jacob");

        assertNotNull(campJacob);
        assertEquals(1, campJacob.size());
    }

    @Test
    void shouldNotFindMissingSection() throws DataAccessException {
        List<Panel> campDavid = repository.findBySection("Camp David");

        assertEquals(0, campDavid.size());
    }

    @Test
    void shouldFindById() throws DataAccessException {
        Panel panel = repository.findById(2);

        assertEquals("Camp Rosenbaum", panel.getSection());
        assertEquals(4, panel.getRow());
        assertEquals(6, panel.getColumn());
        assertEquals(PanelMaterial.COPPER_INDIUM_GALLIUM_SELENIDE, panel.getMaterial());
        assertEquals(2011, panel.getYearInstalled());
        assertEquals(false, panel.isTracking());

    }

    @Test
    void shouldNotFindMissingId() throws DataAccessException {
        Panel panel = repository.findById(12345);

        assertNull(panel);
    }

    @Test
    void shouldAddPanelAndReturnCorrectNextId() throws DataAccessException {
        Panel panel = new Panel();

        panel.setSection("Camp Jacob");
        panel.setRow(1);
        panel.setColumn(7);
        panel.setMaterial(PanelMaterial.MONOCRYSTALLINE_SILICON);
        panel.setYearInstalled(2017);
        panel.setTracking(false);

        Panel actual = repository.add(panel);

        assertNotNull(actual);
        assertEquals(4, actual.getPanelId());
    }

    @Test
    void shouldUpdateExistingPanel() throws DataAccessException {
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
    void shouldNotUpdateMissingPanel() throws DataAccessException{
        Panel panel = new Panel();

        panel.setPanelId(32929);
        panel.setSection("Camp Jacob");
        panel.setRow(1);
        panel.setColumn(7);
        panel.setMaterial(PanelMaterial.MONOCRYSTALLINE_SILICON);
        panel.setYearInstalled(2017);
        panel.setTracking(false);

        boolean success = repository.update(panel);
        assertFalse(success);
    }

    @Test
    void userInputDelimiterShouldNotRuinUpdate() throws DataAccessException{
        Panel panel = new Panel();

        panel.setPanelId(3);
        panel.setSection("Camp Delimiter, test");
        panel.setRow(1);
        panel.setColumn(7);
        panel.setMaterial(PanelMaterial.MONOCRYSTALLINE_SILICON);
        panel.setYearInstalled(2017);
        panel.setTracking(false);
        repository.update(panel);
        Panel actual = repository.findById(3);

        assertEquals("Camp Delimiter test", actual.getSection());
        assertEquals(1, actual.getRow());

    }

    @Test
    void userInputDelimiterShouldNotRuinAdd() throws DataAccessException{
        Panel panel = new Panel();

        panel.setSection("Camp Delimiter, test");
        panel.setRow(1);
        panel.setColumn(7);
        panel.setMaterial(PanelMaterial.MONOCRYSTALLINE_SILICON);
        panel.setYearInstalled(2017);
        panel.setTracking(false);

        Panel actual = repository.add(panel);
        assertEquals(1, actual.getRow());

    }

    @Test
    void shouldDeleteExistingPanelById() throws DataAccessException {
        boolean actual = repository.deleteById(3);
        assertTrue(actual);

        Panel panel = repository.findById(3);
        assertNull(panel);
    }

    @Test
    void shouldNotDeleteMissingPanelById() throws DataAccessException {
        boolean actual = repository.deleteById(4121);
        assertFalse(actual);
    }

    @Test
    void shouldDeleteExistingPanelByPanel() throws DataAccessException {
        boolean actual = repository.deleteByPanel("Camp Aaron", 8, 2);
        assertTrue(actual);

        Panel panel = repository.findById(3);
        assertNull(panel);
    }

    @Test
    void shouldNotDeleteMissingPanelByPanel() throws DataAccessException {
        boolean actual = repository.deleteByPanel("Camp Aaron", 91, 21);

        assertFalse(actual);
    }

}