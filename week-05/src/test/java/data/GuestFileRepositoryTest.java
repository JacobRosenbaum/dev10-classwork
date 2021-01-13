package data;

import models.Guest;
import models.Host;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuestFileRepositoryTest {
    static final String SEED_FILE_PATH = "data/guests-seed.csv";
    static final String TEST_FILE_PATH = "data/guests-test.csv";

    GuestFileRepository repository = new GuestFileRepository(SEED_FILE_PATH);

    @BeforeEach
    void setUp() throws IOException {
        Files.copy(
                Paths.get(SEED_FILE_PATH),
                Paths.get(TEST_FILE_PATH),
                StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindAllShouldReturnCorrectNumberOfGuests() throws DataAccessException {
        List<Guest> all = repository.findAll();

        assertEquals(10, all.size());
    }

    @Test
    void shouldFindAllShouldReturnCorrectData() throws DataAccessException {
        List<Guest> all = repository.findAll();

        assertEquals("Lomas", all.get(0).getLastName());
    }

    @Test
    void shouldFindByEmail() throws DataAccessException {
        Guest guest = repository.findByEmail("slomas0@mediafire.com");

        assertEquals("Lomas", guest.getLastName());
    }

    @Test
    void shouldNotFindMissingEmail() throws DataAccessException {
        Guest guest = repository.findByEmail("jacobrosenbaum95@gmail.com");

        assertNull(guest);
    }
}
