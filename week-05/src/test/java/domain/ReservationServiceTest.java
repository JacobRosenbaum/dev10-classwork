package domain;

import data.*;
import models.Guest;
import models.Reservation;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    ReservationService service = new ReservationService(
            new ReservationRepositoryDouble(),
            new HostRepositoryDouble(),
            new GuestRepositoryDouble()
    );

    @Test
    void shouldFindByEmailReturnsCorrectNumberOfReservations() throws DataAccessException {
        List<Reservation> all = service.findByHostEmail("eyearnes0@sfgate.com");

        assertEquals(1, all.size());
    }

    @Test
    void shouldFindByEmailReturnsCorrectData() throws DataAccessException {
        List<Reservation> all = service.findByHostEmail("eyearnes0@sfgate.com");

        assertEquals(1, all.get(0).getGuest().getGuestId());
    }

    @Test
    void reservationDatesShouldNotClash() throws DataAccessException{
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2020, 7, 10));
        reservation.setEndDate(LocalDate.of(2020, 7, 21));
        reservation.setHost(HostRepositoryDouble.HOST);
        reservation.setGuest(makeGuest());
        reservation.setTotal(BigDecimal.TEN);

        Result<Reservation> result = service.add(reservation);
        assertEquals(result.getErrorMessages().get(0),"Reservation cannot overlap existing reservation.");
    }

    @Test
    void reservationStartDateCanMatchPreviousEndDate() throws DataAccessException{
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2020, 7, 27));
        reservation.setEndDate(LocalDate.of(2020, 7, 31));
        reservation.setHost(HostRepositoryDouble.HOST);
        reservation.setGuest(makeGuest());
        reservation.setTotal(BigDecimal.TEN);

        Result<Reservation> result = service.add(reservation);
        assertEquals(result.getErrorMessages().get(0),"Reservation cannot overlap existing reservation.");
    }

    @Test
    void reservationEndDateCanMatchPreviousStartDate() throws DataAccessException{
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2020, 7, 3));
        reservation.setEndDate(LocalDate.of(2020, 7, 11));
        reservation.setHost(HostRepositoryDouble.HOST);
        reservation.setGuest(makeGuest());
        reservation.setTotal(BigDecimal.TEN);

        Result<Reservation> result = service.add(reservation);
        assertEquals(result.getErrorMessages().get(0),"Reservation cannot overlap existing reservation.");
    }

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

}