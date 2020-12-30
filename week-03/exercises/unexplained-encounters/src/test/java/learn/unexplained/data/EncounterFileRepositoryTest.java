package learn.unexplained.data;

import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EncounterFileRepositoryTest {

    private static final String SEED_PATH = "./data/encounters-seed.csv";
    private static final String TEST_PATH = "./data/encounters-test.csv";

    private EncounterFileRepository repository = new EncounterFileRepository(TEST_PATH);

    @BeforeEach
    void setUp() throws IOException {
        Files.copy(
                Paths.get(SEED_PATH),
                Paths.get(TEST_PATH),
                StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindAll() throws DataAccessException {
        List<Encounter> actual = repository.findAll();

        assertNotNull(actual);
        assertEquals(3, actual.size());
    }

    @Test
    void shouldAdd() throws DataAccessException {
        Encounter encounter = new Encounter();
        encounter.setType(EncounterType.UFO);
        encounter.setWhen("Jan 15, 2005");
        encounter.setDescription("moving pinpoint of light." +
                "seemed to move with me along the highway. " +
                "then suddenly reversed direction without slowing down. it just reversed.");
        encounter.setOccurrences(1);

        Encounter actual = repository.add(encounter);

        assertNotNull(actual);
        assertEquals(4, actual.getEncounterId());
    }

    @Test
    void shouldFindOneOfEachType() throws DataAccessException {
        List<Encounter> ufos = repository.findByType(EncounterType.UFO);
        assertNotNull(ufos);
        assertEquals(1, ufos.size());

        List<Encounter> creatures = repository.findByType(EncounterType.CREATURE);
        assertNotNull(creatures);
        assertEquals(1, creatures.size());

        List<Encounter> sounds = repository.findByType(EncounterType.SOUND);
        assertNotNull(sounds);
        assertEquals(1, sounds.size());


    }

    @Test
    void shouldNotFindMissingType() throws DataAccessException {
        List<Encounter> visions = repository.findByType(EncounterType.VISION);
        assertEquals(0, visions.size());
    }

    @Test
    void shouldFindById() throws DataAccessException{
        Encounter encounter = repository.findById(1);

        assertNotNull(encounter);
        assertEquals(1, encounter.getEncounterId());
        assertEquals(EncounterType.UFO, encounter.getType());
        assertEquals("2020-01-01", encounter.getWhen());
        assertEquals("short test #1", encounter.getDescription());
        assertEquals(1, encounter.getOccurrences());
    }

    @Test
    void shouldUpdateExisting() throws DataAccessException {
        Encounter encounter = new Encounter();
        encounter.setEncounterId(2);
        encounter.setType(EncounterType.CREATURE);
        encounter.setWhen("updated time 2015-07-07");
        encounter.setDescription("updated Test #2");
        encounter.setOccurrences(2);

        boolean success = repository.update(encounter);
        assertTrue(success);

        Encounter actual = repository.findById(2);
        assertNotNull(actual);
        assertEquals("updated time 2015-07-07", actual.getWhen());
        assertEquals("updated Test #2", actual.getDescription());
        assertEquals(2, actual.getOccurrences());
    }

    @Test
    void shouldNotUpdateMissing() throws DataAccessException {
        Encounter encounter = new Encounter();
        encounter.setEncounterId(12345);

        boolean actual = repository.update(encounter);
        assertFalse(actual);

    }

    @Test
    void shouldDeleteExisting() throws DataAccessException {
        boolean actual = repository.deleteById(3);
        assertTrue(actual);

        Encounter encounter = repository.findById(3);
        assertNull(encounter);
    }

    @Test
    void shouldNotDeleteMissing() throws DataAccessException {
        boolean actual = repository.deleteById(41);
        assertFalse(actual);
    }

}