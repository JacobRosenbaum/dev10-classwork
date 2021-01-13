package data;

import models.Guest;
import models.Host;
import models.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationFileRepositoryTest {
    static final String SEED_FILE_PATH = "./data/reservation-seed-2e25f6f7-3ef0-4f38-8a1a-2b5eea81409c.csv";
    static final String TEST_FILE_PATH = "./data/reservation_data_test/2e25f6f7-3ef0-4f38-8a1a-2b5eea81409c.csv";
    static final String TEST_DIR_PATH = "./data/reservation_data_test";
    static final String HOST_SEED_FILE_PATH = "./data/hosts-seed.csv";
    private final HostFileRepository hostFileRepository = new HostFileRepository(HOST_SEED_FILE_PATH);
    private final String hostEmail = "irosenkranc8w@reverbnation.com";
    private final LocalDate startDate = LocalDate.of(2020, 7, 11);
    private final LocalDate endDate = LocalDate.of(2020, 8, 1);
    private final BigDecimal standardRate = new BigDecimal("180");
    private final BigDecimal weekendRate = new BigDecimal("225");


    ReservationFileRepository repository = new ReservationFileRepository(TEST_DIR_PATH, hostFileRepository);

    @BeforeEach
    void setUp() throws IOException {
        Files.copy(
                Paths.get(SEED_FILE_PATH),
                Paths.get(TEST_FILE_PATH),
                StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void findByHostEmailShouldReturnCorrectReservationFile() throws DataAccessException {
        List<Reservation> all = repository.findByHostEmail(hostEmail);

        assertEquals(13, all.size());

    }

    @Test
    void findByHostEmailShouldReturnCorrectReservationData() throws DataAccessException {
        List<Reservation> all = repository.findByHostEmail(hostEmail);

        assertEquals(all.get(0).getGuest().getGuestId(), 643);
    }

    @Test
    void findByHostEmailShouldNotReturnMissingReservationFile() throws DataAccessException {
        List<Reservation> all = repository.findByHostEmail("jacobrosenbaum95@gmail.com");

        assertEquals(0, all.size());

    }

    @Test
    void shouldAddReservation() throws DataAccessException {
        Host host = new Host();

        host.setHostEmail(hostEmail);
        host.setLastName("Rosenkranc");
        host.setPhoneNumber("(970) 7391162");
        host.setAddress("7 Kennedy Plaza");
        host.setCity("Greeley");
        host.setState("CO");
        host.setPostalCode(80638);
        host.setHostId("2e25f6f7-3ef0-4f38-8a1a-2b5eea81409c");
        host.setStandardRate(standardRate);
        host.setWeekendRate(weekendRate);

        Reservation reservation = new Reservation();

        Guest guest = new Guest();
        guest.setGuestId(9);

        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setGuest(guest);
        reservation.setHost(host);

        repository.add(reservation);
        List<Reservation> all = repository.findByHostEmail(hostEmail);

        assertEquals(14, all.size());
    }


    @Test
    void shouldAddReturnsCorrectId() throws DataAccessException {
        Host host = new Host();

        host.setHostEmail(hostEmail);
        host.setLastName("Rosenkranc");
        host.setPhoneNumber("(970) 7391162");
        host.setAddress("7 Kennedy Plaza");
        host.setCity("Greeley");
        host.setState("CO");
        host.setPostalCode(80638);
        host.setHostId("2e25f6f7-3ef0-4f38-8a1a-2b5eea81409c");
        host.setStandardRate(standardRate);
        host.setWeekendRate(weekendRate);

        Reservation reservation = new Reservation();

        Guest guest = new Guest();
        guest.setGuestId(9);

        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setGuest(guest);
        reservation.setHost(host);

        reservation = repository.add(reservation);

        assertEquals(14, reservation.getReservationId());
    }

    @Test
    void serializeCalculatesCorrectTotalIfNull() throws DataAccessException {
        Host host = new Host();

        host.setHostEmail(hostEmail);
        host.setLastName("Rosenkranc");
        host.setPhoneNumber("(970) 7391162");
        host.setAddress("7 Kennedy Plaza");
        host.setCity("Greeley");
        host.setState("CO");
        host.setPostalCode(80638);
        host.setHostId("2e25f6f7-3ef0-4f38-8a1a-2b5eea81409c");
        host.setStandardRate(standardRate);
        host.setWeekendRate(weekendRate);

        Reservation reservation = new Reservation();

        Guest guest = new Guest();
        guest.setGuestId(9);

        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setGuest(guest);
        reservation.setHost(host);

        reservation = repository.add(reservation);

        assertEquals(4050.00, reservation.getTotal().doubleValue());
    }

    @Test
    void serializeGetsCorrectExistingTotal() throws DataAccessException {
        List<Reservation> all = repository.findByHostEmail(hostEmail);

        assertEquals(540, all.get(4).getTotal().doubleValue());
    }


}