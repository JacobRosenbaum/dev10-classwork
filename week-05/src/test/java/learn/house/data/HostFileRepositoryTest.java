package learn.house.data;

import learn.house.models.Host;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HostFileRepositoryTest {
    static final String SEED_FILE_PATH = "data/hosts-seed.csv";
    static final String TEST_FILE_PATH = "data/hosts-test.csv";

    HostFileRepository repository = new HostFileRepository(SEED_FILE_PATH);

    @BeforeEach
    void setUp() throws IOException {
        Files.copy(
                Paths.get(SEED_FILE_PATH),
                Paths.get(TEST_FILE_PATH),
                StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindAllShouldReturnCorrectNumberOfHosts() throws DataAccessException {
        List<Host> all = repository.findAll();

        assertEquals(10, all.size());
    }

    @Test
    void shouldFindAllShouldReturnCorrectData() throws DataAccessException {
        List<Host> all = repository.findAll();

        assertEquals("Yearnes", all.get(0).getLastName());
    }

    @Test
    void shouldFindByEmail() throws DataAccessException {
        Host host = repository.findByEmail("eyearnes0@sfgate.com");

        assertEquals("Yearnes", host.getLastName());
    }

    @Test
    void shouldNotFindMissingEmail() throws DataAccessException {
        Host host = repository.findByEmail("jacobrosenbaum95@gmail.com");

        assertNull(host);
    }
}