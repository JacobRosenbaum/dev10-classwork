package learn.unexplained.domain;

import learn.unexplained.data.DataAccessException;
import learn.unexplained.data.EncounterRepositoryDouble;
import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;
import org.junit.jupiter.api.Disabled;
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
    void shouldFindByType() throws DataAccessException{
        List<Encounter> creatures = service.findByType(EncounterType.CREATURE);
        assertNotNull(creatures);
        assertEquals(1, creatures.size());
    }

    @Test
    void shouldNotFindNullType() throws DataAccessException{
        List<Encounter> vision = service.findByType(EncounterType.VISION);
        assertEquals(0, vision.size());
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
    void shouldNotAddNullWhen() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, null, "test desc", 1);
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
    void shouldUpdate() throws DataAccessException {
        Encounter encounter = new Encounter();
        encounter.setEncounterId(2);
        encounter.setType(EncounterType.CREATURE);
        encounter.setWhen("updated time 2015-07-07");
        encounter.setDescription("updated test #2");
        encounter.setOccurrences(2);

        EncounterResult expected = new EncounterResult();
        expected.setPayload(encounter);

        EncounterResult actual = service.update(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateUnknownEncounterId() throws DataAccessException {
        Encounter encounter = new Encounter();
        encounter.setEncounterId(33333);
        encounter.setType(EncounterType.CREATURE);
        encounter.setWhen("updated time 33333");
        encounter.setDescription("updated test #33333");
        encounter.setOccurrences(33333);

        EncounterResult expected = makeResult("Encounter Id: 33333 was not found");

        EncounterResult actual = service.update(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNoEncounterId() throws DataAccessException {
        Encounter encounter = new Encounter();
        encounter.setEncounterId(0);
        encounter.setType(EncounterType.CREATURE);
        encounter.setWhen("updated time 33333");
        encounter.setDescription("updated test #33333");
        encounter.setOccurrences(33333);

        EncounterResult expected = makeResult("Encounter Id is required");

        EncounterResult actual = service.update(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteById() throws DataAccessException {
        EncounterResult expected = new EncounterResult();
        EncounterResult actual = service.deleteById(2);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotDeleteById() throws DataAccessException {
        EncounterResult expected = makeResult("Encounter Id: 33333 was not found");
        EncounterResult actual = service.deleteById(33333);


        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNull() throws DataAccessException {
        EncounterResult expected = makeResult("encounter cannot be null");
        EncounterResult actual = service.update(null);
        assertEquals(expected, actual);
    }


    private EncounterResult makeResult(String message) {
        EncounterResult result = new EncounterResult();
        result.addErrorMessage(message);
        return result;
    }
}