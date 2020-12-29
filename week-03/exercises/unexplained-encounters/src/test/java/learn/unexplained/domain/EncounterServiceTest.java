package learn.unexplained.domain;

import learn.unexplained.data.DataAccessException;
import learn.unexplained.data.EncounterRepositoryDouble;
import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EncounterServiceTest {

    EncounterService service = new EncounterService(new EncounterRepositoryDouble());

    @Test
    void shouldFindAll() throws DataAccessException {
        List<Encounter> actual = service.findAll();

        assertNotNull(actual);
        assertEquals(3, actual.size());
    }

    @Test
    void shouldByType() throws DataAccessException {
        List<Encounter> actual = service.findByType(EncounterType.UFO);

        assertNotNull(actual);
        assertEquals(1, actual.size());
    }


    @Test
    void shouldNotAddNull() throws DataAccessException {
        EncounterResult expected = makeResult("encounter cannot be null");
        EncounterResult actual = service.add(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddEmptyWhen() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, " ", "test desc", 1);
        EncounterResult expected = makeResult("when is required");
        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }


    @Test
    void shouldNotAddEmptyDescription() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, "2/2/2019", "  ", 1);
        EncounterResult expected = makeResult("description is required");
        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullDescription() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, "2/2/2019", null, 1);
        EncounterResult expected = makeResult("description is required");
        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddZeroOccurrences() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, "2/2/2019", "test description", 0);
        EncounterResult expected = makeResult("occurrences must be greater than 0");
        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddDuplicate() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, "2020-02-01", "short test #2", 1);
        EncounterResult expected = makeResult("duplicate encounter is not allowed");
        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, "2/2/2019", "test description", 1);
        EncounterResult expected = new EncounterResult();
        expected.setPayload(encounter);

        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByType() throws DataAccessException{
        List<Encounter> creatures = service.findByType(EncounterType.CREATURE);
        assertNotNull(creatures);
        assertEquals(1, creatures.size());
    }

    @Test
    void shouldNotFindNullType() throws DataAccessException{
        List<Encounter> ufos = service.findByType(EncounterType.UFO);
        assertNull(ufos);
    }

    @Test
    void shouldUpdate() throws DataAccessException {
        EncounterResult result = service.update(new Encounter());
        assertTrue(result.isSuccess());
    }

//    @Test
//    void shouldNotUpdate() throws DataAccessException {
//        OrbiterResult result = service.update(new Orbiter(3,
//                "Updated Astro",
//                OrbiterType.VENUSIAN, null));
//        assertFalse(result.isSuccess());
//    }
//
//    @Test
//    void shouldNotUpdateEmptyName() throws DataAccessException {
//        OrbiterResult result = service.update(new Orbiter(3,
//                " ",
//                OrbiterType.VENUSIAN, null));
//        assertFalse(result.isSuccess());
//    }

    private EncounterResult makeResult(String message) {
        EncounterResult result = new EncounterResult();
        result.addErrorMessage(message);
        return result;
    }
}