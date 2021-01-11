package learn.foraging.data;

import learn.foraging.models.Category;
import learn.foraging.models.Forage;
import learn.foraging.models.Forager;
import learn.foraging.models.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ForagerFileRepositoryTest {

    private static final String SEED_PATH = "./data/foragers-seed.csv";
    private static final String TEST_PATH = "./data/foragers-test.csv";

    private ForagerFileRepository repository = new ForagerFileRepository(TEST_PATH);

    @BeforeEach
    void setUp() throws IOException {
        Files.copy(
                Paths.get(SEED_PATH),
                Paths.get(TEST_PATH),
                StandardCopyOption.REPLACE_EXISTING);
    }

    //NEW
    @Test
    void findAllShouldReturnCorrectNumberOfForagers() {
        List<Forager> all = repository.findAll();
        assertEquals(10, all.size());
    }

    //NEW
    @Test
    void findAllShouldReturnCorrectForager() {
        List<Forager> all = repository.findAll();
        assertEquals(true, "Sisse".equals(all.get(0).getLastName()));
    }

    //NEW
    @Test
    void shouldFindById() throws DataException {
        Forager forager = repository.findById("0ffc0532-8976-4859-af6e-fedbe192fca7");

        assertEquals(true, forager.getLastName().equals("Domerc"));
    }

    //NEW
    @Test
    void shouldNotFindMissingId() throws DataException {
        Forager forager = repository.findById("0ffc0532-8976-485sdfdfdf9-af6e-fedbe192fca7");

        assertNull(forager);
    }

    //NEW
    @Test
    void shouldFindByState() throws DataException {
        List<Forager> foragers = repository.findByState("TX");

        assertEquals(true, foragers.size() == 1);

    }

    //NEW
    @Test
    void shouldNotFindMissingState() throws DataException {
        List<Forager> foragers = repository.findByState("MN");

        assertEquals(true, foragers.size() == 0);
    }

    @Test
    void shouldAdd() throws DataException {

        Forager expected = new Forager();
        expected.setFirstName("Test");
        expected.setLastName("Forager");
        expected.setState("MN");

        Forager actual = repository.add(expected);

        assertEquals(expected, actual);
    }

    //NEW
    @Test
    void userInputDelimiterShouldNotRuinAdd() throws DataException {
        Forager expected = new Forager();
        expected.setFirstName("T,est");
        expected.setLastName("Forager");
        expected.setState("MN");

        Forager actual = repository.add(expected);

        assertEquals(true, actual.getState().equals("MN"));
    }

}