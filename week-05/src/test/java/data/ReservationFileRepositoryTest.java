package data;

import models.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationFileRepositoryTest {
    static final String SEED_FILE_PATH = "./data/reservation-seed-2e25f6f7-3ef0-4f38-8a1a-2b5eea81409c.csv";
    static final String TEST_FILE_PATH = "./data/reservation_data_test/2e25f6f7-3ef0-4f38-8a1a-2b5eea81409c.csv";
    static final String TEST_DIR_PATH = "./data/reservation_data_test";
    static final String HOST_SEED_FILE_PATH = "./data/hosts-seed.csv";
    private final HostFileRepository hostFileRepository = new HostFileRepository(HOST_SEED_FILE_PATH);
    private final String hostEmail = "irosenkranc8w@reverbnation.com";


    ReservationFileRepository repository = new ReservationFileRepository(TEST_DIR_PATH, hostFileRepository);

    @BeforeEach
    void setUp() throws IOException {
        Files.copy(
                Paths.get(SEED_FILE_PATH),
                Paths.get(TEST_FILE_PATH),
                StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void findByHostEmailShouldReturnCorrectReservationFile() throws DataAccessException{
        List<Reservation> all = repository.findByHostEmail(hostEmail);

        assertEquals(13, all.size());

    }

    @Test
    void findByHostEmailShouldReturnCorrectReservationData() throws DataAccessException{
        List<Reservation> all = repository.findByHostEmail(hostEmail);

        assertEquals(all.get(0).getGuest().getGuestId(), 643);
    }

    @Test
    void findByHostEmailShouldNotReturnMissingReservationFile() throws DataAccessException{
        List<Reservation> all = repository.findByHostEmail("jacobrosenbaum95@gmail.com");

        assertEquals(0, all.size());

    }


}