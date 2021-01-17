package learn.house.domain;

import learn.house.data.*;
import learn.house.models.Guest;
import learn.house.models.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    ReservationService service;

    private static Guest makeGuest() {
        Guest guest = new Guest();

        guest.setGuestId(13);
        guest.setLastName("Test");
        guest.setFirstName("Guest");
        guest.setGuestEmail("test0@twins.com");
        guest.setPhoneNumber("(612) 7096363");
        guest.setState("MN");

        return guest;
    }

    @BeforeEach
    void setUp() {
        service = new ReservationService(
                new ReservationRepositoryDouble(),
                new HostRepositoryDouble(),
                new GuestRepositoryDouble()
        );
    }

    @Test
    void shouldFindByEmailReturnsCorrectNumberOfReservations() throws DataAccessException {
        List<Reservation> all = service.findReservationListByHostEmail("eyearnes0@sfgate.com");

        assertEquals(1, all.size());
    }

    @Test
    void shouldFindByEmailReturnsCorrectData() throws DataAccessException {
        List<Reservation> all = service.findReservationListByHostEmail("eyearnes0@sfgate.com");

        assertEquals(1, all.get(0).getGuest().getGuestId());
    }

    @Test
    void shouldNotFindMissingEmail() throws DataAccessException {
        List<Reservation> all = service.findReservationListByHostEmail("fake@fake.com");

        assertEquals(0, all.size());
    }

    @Test
    void shouldFindByGuestAndHostEmail() throws DataAccessException {
        Reservation expected = service.findReservationByGuestAndHostEmail("slomas0@mediafire.com",
                "eyearnes0@sfgate.com");

        assertEquals(expected.getTotal(), new BigDecimal("1000"));

    }

    @Test
    void shouldNotFindMissingGuestEmail() throws DataAccessException {
        Reservation expected = service.findReservationByGuestAndHostEmail(" ",
                "eyearnes0@ssssssssfgate.com");

        assertNull(expected);
    }

    @Test
    void shouldNotFindNullGuestEmail() throws DataAccessException {
        Reservation expected = service.findReservationByGuestAndHostEmail(null,
                "eyearnes0@sfgate.com");

        assertNull(expected);
    }

    @Test
    void shouldNotFindMissingHostEmail() throws DataAccessException {
        Reservation expected = service.findReservationByGuestAndHostEmail("slomas0@mediafire.com",
                " ");

        assertNull(expected);
    }

    @Test
    void shouldNotFindNullHostEmail() throws DataAccessException {
        Reservation expected = service.findReservationByGuestAndHostEmail("slomas0@mediafire.com",
                null);

        assertNull(expected);
    }

    @Test
    void shouldAddReservation() throws DataAccessException {
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2021, 6, 10));
        reservation.setEndDate(LocalDate.of(2021, 6, 21));
        reservation.setHost(HostRepositoryDouble.HOST);
        reservation.setGuest(makeGuest());
        reservation.setTotal(BigDecimal.TEN);

        Result<Reservation> result = service.add(reservation);
        assertTrue(result.isSuccess());

    }

    @Test
    void shouldNotAddNullReservation() throws DataAccessException {
        Reservation reservation = null;

        Result<Reservation> result = service.add(reservation);
        assertEquals(result.getErrorMessages().get(0), "Cannot find reservation.");
    }

    @Test
    void shouldNotAddNullGuest() throws DataAccessException {
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2021, 7, 10));
        reservation.setEndDate(LocalDate.of(2021, 7, 21));
        reservation.setHost(HostRepositoryDouble.HOST);
        reservation.setGuest(null);
        reservation.setTotal(BigDecimal.TEN);

        Result<Reservation> result = service.add(reservation);
        assertEquals(result.getErrorMessages().get(0), "Reservation guest is required.");
    }

    @Test
    void shouldNotAddNullStartDate() throws DataAccessException {
        Reservation reservation = new Reservation();
        reservation.setStartDate(null);
        reservation.setEndDate(LocalDate.of(2021, 7, 21));
        reservation.setHost(HostRepositoryDouble.HOST);
        reservation.setGuest(GuestRepositoryDouble.GUEST);
        reservation.setTotal(BigDecimal.TEN);

        Result<Reservation> result = service.add(reservation);
        assertEquals(result.getErrorMessages().get(0), "Reservation start date is required.");
    }

    @Test
    void shouldNotAddNullEndDate() throws DataAccessException {
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2020, 2, 12));
        reservation.setEndDate(null);
        reservation.setHost(HostRepositoryDouble.HOST);
        reservation.setGuest(GuestRepositoryDouble.GUEST);
        reservation.setTotal(BigDecimal.TEN);

        Result<Reservation> result = service.add(reservation);
        assertEquals(result.getErrorMessages().get(0), "Reservation end date is required.");
    }

    @Test
    void reservationDatesShouldNotClash() throws DataAccessException {
        Reservation reservation = new Reservation();
        reservation.setReservationId(2);
        reservation.setStartDate(LocalDate.of(2021, 7, 10));
        reservation.setEndDate(LocalDate.of(2021, 7, 21));
        reservation.setHost(HostRepositoryDouble.HOST);
        reservation.setGuest(makeGuest());
        reservation.setTotal(BigDecimal.TEN);

        Result<Reservation> result = service.add(reservation);
        assertEquals(result.getErrorMessages().get(0), "Reservation cannot overlap existing reservation.");
    }

    @Test
    void reservationStartDateCanMatchPreviousEndDate() throws DataAccessException {
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2021, 7, 27));
        reservation.setEndDate(LocalDate.of(2021, 7, 31));
        reservation.setHost(HostRepositoryDouble.HOST);
        reservation.setGuest(makeGuest());
        reservation.setTotal(BigDecimal.TEN);

        Result<Reservation> result = service.add(reservation);
        assertTrue(result.isSuccess());
    }

    @Test
    void reservationEndDateCanMatchPreviousStartDate() throws DataAccessException {
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2021, 7, 3));
        reservation.setEndDate(LocalDate.of(2021, 7, 11));
        reservation.setHost(HostRepositoryDouble.HOST);
        reservation.setGuest(makeGuest());
        reservation.setTotal(BigDecimal.TEN);

        Result<Reservation> result = service.add(reservation);
        assertTrue(result.isSuccess());
    }

    @Test
    void reservationStartDateCannotBeAfterEndDate() throws DataAccessException {
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2021, 7, 13));
        reservation.setEndDate(LocalDate.of(2021, 7, 11));
        reservation.setHost(HostRepositoryDouble.HOST);
        reservation.setGuest(makeGuest());
        reservation.setTotal(BigDecimal.TEN);

        Result<Reservation> result = service.add(reservation);
        assertEquals(result.getErrorMessages().get(0), "Reservation start date must be before end date.");
    }

    @Test
    void shouldUpdateReservation() throws DataAccessException {
        Reservation reservation = service.findReservationByGuestAndHostEmail("slomas0@mediafire.com",
                "eyearnes0@sfgate.com");
        reservation.setStartDate(LocalDate.of(2021, 7, 13));
        reservation.setEndDate(LocalDate.of(2021, 7, 24));
        reservation.setHost(HostRepositoryDouble.HOST);
        reservation.setGuest(makeGuest());
        reservation.setTotal(new BigDecimal("12345"));

        Result<Reservation> result = service.update(reservation);

        assertTrue(result.isSuccess());

    }

    @Test
    void shouldNotUpdateMissingReservation() throws DataAccessException {
        Reservation reservation = service.findReservationByGuestAndHostEmail("eyear$$$nes0@sfgate.com",
                "slomas0@mediafire.com");

        Result<Reservation> result = service.update(reservation);

        assertEquals(result.getErrorMessages().get(0), "Cannot find reservation.");

    }

    @Test
    void shouldDeleteReservation() throws DataAccessException {
        Reservation reservation = service.findReservationByGuestAndHostEmail("slomas0@mediafire.com",
                "eyearnes0@sfgate.com");

        Result<Reservation> result = service.delete(reservation);

        assertTrue(result.isSuccess());

    }

    @Test
    void shouldNotDeleteMissingReservation() throws DataAccessException {
        Reservation reservation = service.findReservationByGuestAndHostEmail("eyear$$$nes0@sfgate.com",
                "slomas0@mediafire.com");

        Result<Reservation> result = service.delete(reservation);

        assertEquals(result.getErrorMessages().get(0), "Cannot find reservation.");

    }


}