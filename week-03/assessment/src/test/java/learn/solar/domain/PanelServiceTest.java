package learn.solar.domain;

import learn.solar.data.DataAccessException;
import learn.solar.data.PanelRepositoryDouble;
import learn.solar.models.Panel;
import learn.solar.models.PanelMaterial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PanelServiceTest {

    PanelService service;

    @BeforeEach
    void setup() {
        service = new PanelService(new PanelRepositoryDouble());
    }

    @Test
    void findAllShouldReturnCorrectNumberOfPanels() throws DataAccessException {
        List<Panel> all = service.findAll();

        assertNotNull(all);
        assertEquals(3, all.size());
    }

    @Test
    void findAllShouldReturnProperPanel() throws DataAccessException {
        List<Panel> all = service.findAll();

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
        List<Panel> campJacob = service.findBySection("Camp Jacob");

        assertNotNull(campJacob);
        assertEquals(1, campJacob.size());
    }

    @Test
    void shouldNotFindMissingSection() throws DataAccessException {
        List<Panel> campDavid = service.findBySection("Camp David");

        assertEquals(0, campDavid.size());
    }

    @Test
    void shouldFindById() throws DataAccessException {
        Panel panel = service.findById(2);

        assertEquals("Camp Rosenbaum", panel.getSection());
        assertEquals(4, panel.getRow());
        assertEquals(6, panel.getColumn());
        assertEquals(PanelMaterial.COPPER_INDIUM_GALLIUM_SELENIDE, panel.getMaterial());
        assertEquals(2011, panel.getYearInstalled());
        assertEquals(false, panel.isTracking());

    }

    @Test
    void shouldAddValidPanel() throws DataAccessException {
        Panel panel = new Panel();
        panel.setPanelId(4);
        panel.setSection("Camp Jacob");
        panel.setRow(6);
        panel.setColumn(6);
        panel.setMaterial(PanelMaterial.MONOCRYSTALLINE_SILICON);
        panel.setYearInstalled(2013);
        panel.setTracking(true);

        PanelResult expected = new PanelResult();
        expected.setPanel(panel);
        PanelResult actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddDuplicateId() throws DataAccessException {
        Panel panel = new Panel(1, "Test", 1, 2,
                PanelMaterial.MONOCRYSTALLINE_SILICON, 2013, false);

        PanelResult expected = makeResult("Cannot enter duplicate Panel ID: " + panel.getPanelId());
        PanelResult actual = service.add(panel);
        assertEquals(expected, actual);

    }

    @Test
    void shouldNotAddNullPanel() throws DataAccessException {
        PanelResult result = service.add(null);

        assertEquals(1, result.getMessages().size());
        assertTrue("Panel cannot be null".equals(result.getMessages().get(0)));
    }

    @Test
    void shouldNotFindMissingId() throws DataAccessException {
        Panel panel = service.findById(12345);

        assertNull(panel);
    }

    @Test
    void shouldNotAddEmptySection() throws DataAccessException {
        Panel panel = new Panel(13, " ", 1, 2,
                PanelMaterial.MONOCRYSTALLINE_SILICON, 2013, false);

        PanelResult expected = makeResult("Section cannot be blank");
        PanelResult actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullSection() throws DataAccessException {
        Panel panel = new Panel(12, null, 1, 2,
                PanelMaterial.MONOCRYSTALLINE_SILICON, 2013, false);

        PanelResult expected = makeResult("Section cannot be blank");
        PanelResult actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNegativeId() throws DataAccessException {
        Panel panel = new Panel(-12, "Camp jake", 1, 2,
                PanelMaterial.MONOCRYSTALLINE_SILICON, 2013, false);

        PanelResult expected = makeResult("Panel ID needs to be a positive number");
        PanelResult actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullMaterial() throws DataAccessException {
        Panel panel = new Panel(11, "Test", 1, 2,
                null, 2013, false);

        PanelResult expected = makeResult("Material is required");
        PanelResult actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddLowRow() throws DataAccessException {
        Panel panel = new Panel(10, "Tes", 0, 2,
                PanelMaterial.MONOCRYSTALLINE_SILICON, 2013, false);

        PanelResult expected = makeResult("Row must be between [1-250]");
        PanelResult actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddHighRow() throws DataAccessException {
        Panel panel = new Panel(9, "Tes", 251, 2,
                PanelMaterial.MONOCRYSTALLINE_SILICON, 2013, false);

        PanelResult expected = makeResult("Row must be between [1-250]");
        PanelResult actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddLowColumn() throws DataAccessException {
        Panel panel = new Panel(8, "Tes", 10, 0,
                PanelMaterial.MONOCRYSTALLINE_SILICON, 2013, false);

        PanelResult expected = makeResult("Column must be between [1-250]");
        PanelResult actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddHighColumn() throws DataAccessException {
        Panel panel = new Panel(7, "Tes", 10, 251,
                PanelMaterial.MONOCRYSTALLINE_SILICON, 2013, false);

        PanelResult expected = makeResult("Column must be between [1-250]");
        PanelResult actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddHighYearInstalled() throws DataAccessException {
        Panel panel = new Panel(6, "Tes", 10, 243,
                PanelMaterial.MONOCRYSTALLINE_SILICON, 2025, false);

        PanelResult expected = makeResult("Year installed must be in the past");
        PanelResult actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddDuplicatePanel() throws DataAccessException {
        Panel panel = new Panel(5, "Camp Jacob", 2, 4,
                PanelMaterial.MONOCRYSTALLINE_SILICON, 2015, false);

        PanelResult expected = makeResult("Cannot enter duplicate Panel with same Section, Row, and Column");
        PanelResult actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdateValidPanel() throws DataAccessException{
        Panel panel = new Panel();
        panel.setPanelId(3);
        panel.setSection("Camp Jacob");
        panel.setRow(6);
        panel.setColumn(6);
        panel.setMaterial(PanelMaterial.MONOCRYSTALLINE_SILICON);
        panel.setYearInstalled(2013);
        panel.setTracking(true);

        PanelResult expected = new PanelResult();
        expected.setPanel(panel);
        PanelResult actual = service.update(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateInvalidId() throws DataAccessException{
        Panel panel = new Panel();
        panel.setPanelId(33333);
        panel.setSection("Camp Jacob");
        panel.setRow(6);
        panel.setColumn(6);
        panel.setMaterial(PanelMaterial.MONOCRYSTALLINE_SILICON);
        panel.setYearInstalled(2013);
        panel.setTracking(true);

        PanelResult expected = makeResult("Panel ID: 33333 was not found");

        PanelResult actual = service.update(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullPanel() throws DataAccessException{
        PanelResult result = service.update(null);

        assertEquals(1, result.getMessages().size());
        assertTrue("Panel cannot be null".equals(result.getMessages().get(0)));
    }

    @Test
    void shouldDeleteById() throws DataAccessException {
        PanelResult expected = new PanelResult();
        PanelResult actual = service.deleteById(2);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotDeleteInvalidId() throws DataAccessException {
        PanelResult expected = makeResult("Panel ID: 33333 was not found");
        PanelResult actual = service.deleteById(33333);


        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteByPanel() throws DataAccessException {
        PanelResult expected = new PanelResult();
        PanelResult actual = service.deleteByPanel("Camp Aaron", 8, 2);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotDeleteInvalidPanel() throws DataAccessException {
        PanelResult expected = makeResult("There is no Panel Camp Aaron-9-2");
        PanelResult actual = service.deleteByPanel("Camp Aaron", 9, 2);

        assertEquals(expected, actual);
    }


    private PanelResult makeResult(String message) {
        PanelResult result = new PanelResult();
        result.addErrorMessage(message);
        return result;
    }

}