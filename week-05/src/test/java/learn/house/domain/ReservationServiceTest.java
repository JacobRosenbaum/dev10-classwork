package learn.house.domain;

import learn.house.data.*;
import learn.house.models.Guest;
import learn.house.models.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    void shouldFindCorrectNumberOfReservationListsByState() throws DataAccessException {
        List<Reservation> all = service.findReservationListsByState("TX");

        assertEquals(1, all.size());
    }


    @Test
    void shouldNotFindMissingReservationListsByState() throws DataAccessException {
        List<Reservation> all = service.findReservationListsByState("AK");

        assertEquals(0, all.size());
    }

    @Test
    void shouldFindCorrectNumberOfReservationByGuestEmail() throws DataAccessException {
        List<Reservation> all = service.findReservationListByGuestEmail("slomas0@mediafire.com");

        assertEquals(1, all.size());
    }

    @Test
    void shouldFindReservationDataByGuestEmail() throws DataAccessException {
        List<Reservation> all = service.findReservationListByGuestEmail("slomas0@mediafire.com");

        assertEquals(new BigDecimal("1000"), all.get(0).getTotal());
    }

    @Test
    void shouldNotFindMissingReservationByGuestEmail() throws DataAccessException {
        List<Reservation> all = service.findReservationListByGuestEmail("sl$$$omas0@mediafire.com");

        assertNull(all);
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
    void shouldNotAddNullHost() throws DataAccessException {
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2021, 7, 10));
        reservation.setEndDate(LocalDate.of(2021, 7, 21));
        reservation.setHost(null);
        reservation.setGuest(GuestRepositoryDouble.GUEST);
        reservation.setTotal(BigDecimal.TEN);

        Result<Reservation> result = service.add(reservation);
        assertEquals(result.getErrorMessages().get(0), "Reservation host is required.");
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
    void shouldCalculateCorrectWeekDays() throws DataAccessException {
        Reservation reservation = new Reservation();
        reservation.setReservationId(2);
        reservation.setStartDate(LocalDate.of(2021, 7, 11));
        reservation.setEndDate(LocalDate.of(2021, 7, 15));
        reservation.setHost(HostRepositoryDouble.HOST);
        reservation.setGuest(GuestRepositoryDouble.GUEST);

        BigDecimal total = service.calculateTotal(reservation.getStartDate(),
                reservation.getEndDate(), reservation.getHost());
//        standard rate = 340
//        total days = 4
//        weekdays = 4 ($1,360)
//        total = $1,360
        assertEquals(BigDecimal.valueOf(1360).setScale(2, RoundingMode.HALF_UP), total);
    }

    @Test
    void shouldCalculateCorrectWeekEndDays() throws DataAccessException {
        Reservation reservation = new Reservation();
        reservation.setReservationId(2);
        reservation.setStartDate(LocalDate.of(2021, 7, 9));
        reservation.setEndDate(LocalDate.of(2021, 7, 11));
        reservation.setHost(HostRepositoryDouble.HOST);
        reservation.setGuest(GuestRepositoryDouble.GUEST);

        BigDecimal total = service.calculateTotal(reservation.getStartDate(),
                reservation.getEndDate(), reservation.getHost());
//        weekend rate = 425
//        total days = 2
//        weekend days = 2 ($850)
//        total = $850
        assertEquals(BigDecimal.valueOf(850).setScale(2, RoundingMode.HALF_UP), total);
    }

    @Test
    void shouldCalculateCorrectTotal() throws DataAccessException {
        Reservation reservation = new Reservation();
        reservation.setReservationId(2);
        reservation.setStartDate(LocalDate.of(2021, 7, 10));
        reservation.setEndDate(LocalDate.of(2021, 7, 21));
        reservation.setHost(HostRepositoryDouble.HOST);
        reservation.setGuest(GuestRepositoryDouble.GUEST);

        BigDecimal total = service.calculateTotal(reservation.getStartDate(),
                reservation.getEndDate(), reservation.getHost());
//        standard rate = 340
//        weekend rate = 425
//        total days = 11
//        weekdays = 8 ($2,720)
//        weekendDays = 3 ($1,275)
//        total = $3,995
        assertEquals(BigDecimal.valueOf(3995).setScale(2, RoundingMode.HALF_UP), total);
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
    void reservationStartDateCannotBeInPast() throws DataAccessException {
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2020, 7, 13));
        reservation.setEndDate(LocalDate.of(2020, 7, 17));
        reservation.setHost(HostRepositoryDouble.HOST);
        reservation.setGuest(makeGuest());
        reservation.setTotal(BigDecimal.TEN);

        Result<Reservation> result = service.add(reservation);
        assertEquals(result.getErrorMessages().get(0), "Reservation start date must be in the future.");
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

    @Test
    void shouldNotDeleteReservationInPast() throws DataAccessException {
        Reservation reservation = service.findReservationByGuestAndHostEmail("slomas0@mediafire.com",
                "eyearnes0@sfgate.com");
        reservation.setStartDate(LocalDate.of(2020, 8, 12));
        reservation.setEndDate(LocalDate.of(2020, 8, 15));
        Result<Reservation> result = service.delete(reservation);

        assertEquals(result.getErrorMessages().get(0), "Cannot delete a reservation in the past.");

    }


}