package data;

import models.Guest;
import models.Host;
import models.Reservation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationRepositoryDouble implements ReservationRepository {

    private final LocalDate startDate  = LocalDate.of(2020,7,11);
    private final LocalDate endDate  = LocalDate.of(2020,7,27);


    private final ArrayList<Reservation> reservations = new ArrayList<>();


    public ReservationRepositoryDouble() {
        Reservation reservation = new Reservation();

        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setTotal(new BigDecimal("1000"));
        reservation.setGuest(GuestRepositoryDouble.GUEST);
        reservation.setHost(HostRepositoryDouble.HOST);

        reservations.add(reservation);
    }

    @Override
    public List<Reservation> findByHostId(String hostId) throws DataAccessException {
        return reservations.stream()
                .filter(i -> i.getHost().getHostId().equals(hostId))
                .collect(Collectors.toList());
    }

    @Override
    public Reservation add(Reservation reservation) throws DataAccessException {
        reservations.add(reservation);
        return reservation;
    }

    @Override
    public boolean update(Reservation reservation) throws DataAccessException {
        return false;
    }

    @Override
    public boolean delete(Reservation reservation) throws DataAccessException {
        return false;
    }
}
