package learn.foraging.data;

import learn.foraging.models.Category;
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

    @Test
    void shouldFindAll() {
        List<Forager> all = repository.findAll();
        assertEquals(10, all.size());
    }

    @Test
    void shouldAdd() throws DataException {

        Forager expected = new Forager();
        expected.setFirstName("T,est");
        expected.setLastName("Forager");
        expected.setState("MN");

        Forager actual = repository.add(expected);

        assertEquals(expected, actual);
    }
}