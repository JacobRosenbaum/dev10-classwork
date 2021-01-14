package learn.house.data;

import learn.house.models.Guest;
import learn.house.models.Host;
import learn.house.models.Reservation;
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

    private final String hostId = "2e25f6f7-3ef0-4f38-8a1a-2b5eea81409c";
    private final LocalDate startDate = LocalDate.of(2020, 7, 11);
    private final LocalDate endDate = LocalDate.of(2020, 8, 1);
    private final BigDecimal standardRate = new BigDecimal("180");
    private final BigDecimal weekendRate = new BigDecimal("225");

    ReservationFileRepository repository = new ReservationFileRepository(TEST_DIR_PATH);

    @BeforeEach
    void setUp() throws IOException {
        Files.copy(
                Paths.get(SEED_FILE_PATH),
                Paths.get(TEST_FILE_PATH),
                StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void findByHostIdShouldReturnCorrectReservationFile() throws DataAccessException {
        List<Reservation> all = repository.findByHostId(hostId);

        assertEquals(13, all.size());

    }

    @Test
    void findByHostIdShouldReturnCorrectReservationData() throws DataAccessException {
        List<Reservation> all = repository.findByHostId(hostId);

        assertEquals(all.get(0).getGuest().getGuestId(), 643);
    }

    @Test
    void shouldThrowDataAccessExceptionIfIdNotValid() throws DataAccessException {
        Exception exception = assertThrows(DataAccessException.class, () -> {
            repository.findByHostId("1234");
        });

        assertEquals("./data/reservation_data_test/1234.csv (No such file or directory)", exception.getMessage());

    }

    @Test
    void shouldAddReservation() throws DataAccessException {
        Host host = new Host();

        host.setHostId(hostId);
        host.setStandardRate(standardRate);
        host.setWeekendRate(weekendRate);

        Reservation reservation = new Reservation();

        Guest guest = new Guest();
        guest.setGuestId(9);

        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setGuest(guest);
        reservation.setHost(host);
        reservation.setTotal(reservation.getTotal());

        repository.add(reservation);
        List<Reservation> all = repository.findByHostId(hostId);

        assertEquals(14, all.size());
    }

    @Test
    void shouldAddReturnsCorrectId() throws DataAccessException {
        Host host = new Host();

        host.setHostId(hostId);
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
        reservation.setTotal(reservation.getTotal());


        repository.add(reservation);
        List<Reservation> all = repository.findByHostId(hostId);

        assertEquals(14, all.size());
    }


    @Test
    void shouldUpdateReservation() throws DataAccessException {
        Host host = new Host();
        host.setHostId(hostId);

        Reservation reservation = new Reservation();

        Guest guest = new Guest();
        guest.setGuestId(9);

        reservation.setReservationId(2);
        reservation.setStartDate(startDate);
        reservation.setEndDate(LocalDate.of(2021, 7, 21));
        reservation.setGuest(guest);
        reservation.setHost(host);
        reservation.setTotal(new BigDecimal("1000"));
        boolean success = repository.update(reservation);

        assertTrue(success);
    }

    @Test
    void shouldNotUpdateMissingReservationId() throws DataAccessException {
        Host host = new Host();
        host.setHostId(hostId);

        Reservation reservation = new Reservation();

        Guest guest = new Guest();
        guest.setGuestId(9);

        reservation.setReservationId(2222);
        reservation.setStartDate(startDate);
        reservation.setEndDate(LocalDate.of(2021, 7, 21));
        reservation.setGuest(guest);
        reservation.setHost(host);
        reservation.setTotal(new BigDecimal("1000"));
        boolean success = repository.update(reservation);

        assertFalse(success);
    }

    @Test
    void shouldDeleteReservation() throws DataAccessException {
        Host host = new Host();
        host.setHostId(hostId);

        Reservation reservation = new Reservation();

        Guest guest = new Guest();
        guest.setGuestId(9);

        reservation.setReservationId(2);
        reservation.setGuest(guest);
        reservation.setHost(host);

        boolean success = repository.delete(reservation);

        assertTrue(success);
    }

    @Test
    void shouldNotDeleteReservationId() throws DataAccessException {
        Host host = new Host();
        host.setHostId(hostId);

        Reservation reservation = new Reservation();

        Guest guest = new Guest();
        guest.setGuestId(9);

        reservation.setReservationId(22222);
        reservation.setGuest(guest);
        reservation.setHost(host);

        boolean success = repository.delete(reservation);

        assertFalse(success);
    }



    @Test
    void serializeCalculatesCorrectTotalIfNull() throws DataAccessException {
        Host host = new Host();

        host.setHostId(hostId);
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
        List<Reservation> all = repository.findByHostId(hostId);

        assertEquals(540, all.get(4).getTotal().doubleValue());
    }


}