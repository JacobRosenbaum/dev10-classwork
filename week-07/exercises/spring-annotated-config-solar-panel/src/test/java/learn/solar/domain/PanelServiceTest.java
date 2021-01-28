package learn.solar.domain;

import learn.solar.data.PanelRepository;
import learn.solar.data.PanelRepositoryDouble;
import learn.solar.models.Panel;
import learn.solar.models.PanelMaterial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PanelServiceTest {

    @MockBean
    PanelRepository repository;

    @Autowired
    PanelService service;


    @Test
    void findAllShouldReturnProperNumberOfPanels() {
        List<Panel> panelOut = new ArrayList<>();
        panelOut.add(new Panel());

        when(repository.findAll()).thenReturn(panelOut);

        List<Panel> results = service.findAll();

        assertNotNull(results);
        assertEquals(1, results.size());
    }

    @Test
    void findAllShouldReturnProperData() {
        List<Panel> panelOut = new ArrayList<>();
        Panel newPanel = new Panel();
        newPanel.setPanelId(1);
        panelOut.add(newPanel);

        when(repository.findAll()).thenReturn(panelOut);

        List<Panel> results = service.findAll();

        assertNotNull(results);
        assertEquals(1, results.get(0).getPanelId());

    }

    @Test
    void shouldFindBySection() {
        List<Panel> panelOut = new ArrayList<>();
        Panel newPanel = new Panel();
        newPanel.setSection("Camp Jacob");
        panelOut.add(newPanel);

        when(repository.findBySection("Camp Jacob")).thenReturn(panelOut);

        List<Panel> results = service.findBySection("Camp Jacob");

        assertNotNull(results);
        assertEquals(1, results.size());
    }

    @Test
    void shouldNotFindMissingSection() {
        List<Panel> panelOut = new ArrayList<>();
        Panel newPanel = new Panel();
        newPanel.setSection("Camp Jacob");
        panelOut.add(newPanel);

        when(repository.findBySection("Camp Jacob")).thenReturn(panelOut);

        List<Panel> results = service.findBySection("Camp Jaaaacob");

        assertNotNull(results);
        assertEquals(0, results.size());
    }

    @Test
    void shouldFindById() {
        Panel newPanel = new Panel();
        newPanel.setPanelId(1);

        when(repository.findById(1)).thenReturn(newPanel);

        Panel result = service.findById(1);

        assertNotNull(result);
        assertEquals(1, newPanel.getPanelId());
    }

    @Test
    void shouldNotFindMissingId(){
        Panel newPanel = new Panel();
        newPanel.setPanelId(1);

        when(repository.findById(1)).thenReturn(newPanel);

        Panel result = service.findById(2);

        assertNull(result);
    }

    @Test
    void shouldAddValidPanel() {
        Panel panelIn = new Panel();
        panelIn.setPanelId(4);
        panelIn.setSection("Camp Jacob");
        panelIn.setRow(6);
        panelIn.setColumn(6);
        panelIn.setMaterial(PanelMaterial.MONOCRYSTALLINE_SILICON);
        panelIn.setYearInstalled(2013);
        panelIn.setTracking(true);
        Panel panelOut = new Panel();

        when(repository.add(panelIn)).thenReturn(panelOut);

        PanelResult<Panel> expected = new PanelResult<>();
        expected.setPayload(panelIn);

        PanelResult<Panel> actual = service.add(panelIn);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddDuplicateId() {
        Panel panel = new Panel(1, "Test", 1, 2,
                PanelMaterial.MONOCRYSTALLINE_SILICON, 2013, false);

        PanelResult<Panel> expected = makeResult("Cannot enter duplicate Panel ID: " + panel.getPanelId(), ResultType.INVALID);
        PanelResult<Panel> actual = service.add(panel);
        assertEquals(expected, actual);

    }

    @Test
    void shouldNotAddNullPanel() {
        PanelResult<Panel> result = service.add(null);

        assertEquals(1, result.getMessages().size());
        assertTrue("Panel cannot be null".equals(result.getMessages().get(0)));
    }


    @Test
    void shouldNotAddEmptySection() {
        Panel panel = new Panel(13, " ", 1, 2,
                PanelMaterial.MONOCRYSTALLINE_SILICON, 2013, false);

        PanelResult<Panel> expected = makeResult("Section cannot be blank", ResultType.INVALID);
        PanelResult<Panel> actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullSection() {
        Panel panel = new Panel(12, null, 1, 2,
                PanelMaterial.MONOCRYSTALLINE_SILICON, 2013, false);

        PanelResult<Panel> expected = makeResult("Section cannot be blank", ResultType.INVALID);
        PanelResult<Panel> actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullMaterial() {
        Panel panel = new Panel(11, "Test", 1, 2,
                null, 2013, false);

        PanelResult<Panel> expected = makeResult("Material is required", ResultType.INVALID);
        PanelResult<Panel> actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddLowRow() {
        Panel panel = new Panel(10, "Tes", 0, 2,
                PanelMaterial.MONOCRYSTALLINE_SILICON, 2013, false);

        PanelResult<Panel> expected = makeResult("Row must be between [1-250]", ResultType.INVALID);
        PanelResult<Panel> actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddHighRow() {
        Panel panel = new Panel(9, "Tes", 251, 2,
                PanelMaterial.MONOCRYSTALLINE_SILICON, 2013, false);

        PanelResult<Panel> expected = makeResult("Row must be between [1-250]", ResultType.INVALID);
        PanelResult<Panel> actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddLowColumn() {
        Panel panel = new Panel(8, "Tes", 10, 0,
                PanelMaterial.MONOCRYSTALLINE_SILICON, 2013, false);

        PanelResult<Panel> expected = makeResult("Column must be between [1-250]", ResultType.INVALID);
        PanelResult<Panel> actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddHighColumn() {
        Panel panel = new Panel(7, "Tes", 10, 251,
                PanelMaterial.MONOCRYSTALLINE_SILICON, 2013, false);

        PanelResult<Panel> expected = makeResult("Column must be between [1-250]", ResultType.INVALID);
        PanelResult<Panel> actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddHighYearInstalled() {
        Panel panel = new Panel(6, "Tes", 10, 243,
                PanelMaterial.MONOCRYSTALLINE_SILICON, 2025, false);

        PanelResult<Panel> expected = makeResult("Year installed must be in the past", ResultType.INVALID);
        PanelResult<Panel> actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddDuplicatePanel() {
        Panel panel = new Panel(5, "Camp Jacob", 2, 4,
                PanelMaterial.MONOCRYSTALLINE_SILICON, 2015, false);

        PanelResult<Panel> expected = makeResult("Cannot enter duplicate Panel with same Section, Row, and Column", ResultType.INVALID);
        PanelResult<Panel> actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdateValidPanel() {
        Panel panel = service.findById(3);
        panel.setSection("Camp Jacob");
        panel.setRow(6);
        panel.setColumn(6);
        panel.setMaterial(PanelMaterial.MONOCRYSTALLINE_SILICON);
        panel.setYearInstalled(2013);
        panel.setTracking(true);

        PanelResult<Panel> expected = new PanelResult<Panel>();
        expected.setPayload(panel);
        PanelResult<Panel> actual = service.update(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdatePanelWithSameIdEvenIfRowColSectionAreSame() {
        Panel panel = service.findById(3);
        panel.setSection("Camp Aaron");
        panel.setRow(8);
        panel.setColumn(2);
        panel.setMaterial(PanelMaterial.MONOCRYSTALLINE_SILICON);
        panel.setYearInstalled(2008);
        panel.setTracking(false);

        PanelResult<Panel> expected = new PanelResult<Panel>();
        expected.setPayload(panel);
        PanelResult<Panel> actual = service.update(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateInvalidId() {
        Panel panel = new Panel();
        panel.setPanelId(33333);
        panel.setSection("Camp Jacob");
        panel.setRow(6);
        panel.setColumn(6);
        panel.setMaterial(PanelMaterial.MONOCRYSTALLINE_SILICON);
        panel.setYearInstalled(2013);
        panel.setTracking(true);

        PanelResult<Panel> expected = makeResult("Panel ID: 33333 was not found", ResultType.NOT_FOUND);

        PanelResult<Panel> actual = service.update(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullPanel() {
        PanelResult<Panel> result = service.update(null);

        assertEquals(1, result.getMessages().size());
        assertTrue("Panel cannot be null".equals(result.getMessages().get(0)));
    }

    @Test
    void shouldNotUpdateDuplicatePanel() {
        Panel panel = new Panel();
        panel.setPanelId(3);
        panel.setSection("Camp Jacob");
        panel.setRow(2);
        panel.setColumn(4);
        panel.setMaterial(PanelMaterial.MONOCRYSTALLINE_SILICON);
        panel.setYearInstalled(2013);
        panel.setTracking(true);

        PanelResult<Panel> expected = makeResult("Cannot enter duplicate Panel with same Section, Row, and Column\n", ResultType.INVALID);

        PanelResult<Panel> actual = service.update(panel);
        assertEquals(expected, actual);
    }


    @Test
    void shouldDeleteById() {
        PanelResult<Panel> expected = new PanelResult<Panel>();
        PanelResult<Panel> actual = service.deleteById(2);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotDeleteNullId() {
        PanelResult<Panel> expected = makeResult("Panel does not exist", ResultType.NOT_FOUND);
        PanelResult<Panel> actual = service.deleteById(0);

        assertEquals(expected, actual);
    }


//    @Test
//    void shouldDeleteByPanel() throws DataAccessException {
//        PanelResult expected = new PanelResult();
//        PanelResult actual = service.deleteByPanel("Camp Aaron", 8, 2);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void shouldNotDeleteInvalidPanel() throws DataAccessException {
//        PanelResult expected = makeResult("There is no Panel Camp Aaron-9-2");
//        PanelResult actual = service.deleteByPanel("Camp Aaron", 9, 2);
//
//        assertEquals(expected, actual);
//    }


    private PanelResult<Panel> makeResult(String message, ResultType type) {
        PanelResult<Panel> result = new PanelResult<>();
        result.addErrorMessage(message, type);
        return result;

    }

}