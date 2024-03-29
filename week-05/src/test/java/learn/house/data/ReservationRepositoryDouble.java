package learn.house.data;

import learn.house.models.Reservation;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationRepositoryDouble implements ReservationRepository {

    private final LocalDate startDate = LocalDate.of(2021, 7, 11);
    private final LocalDate endDate = LocalDate.of(2021, 7, 27);


    private final ArrayList<Reservation> reservations = new ArrayList<>();
    private final ArrayList<Path> paths = new ArrayList<>();

    public ReservationRepositoryDouble() {
        Reservation reservation = new Reservation();
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setTotal(new BigDecimal("1000"));
        reservation.setGuest(GuestRepositoryDouble.GUEST);
        reservation.setHost(HostRepositoryDouble.HOST);
        reservations.add(reservation);

        Path path = Paths.get( "./data/reservation_data_test", ".csv");
        paths.add(path);
    }

    @Override
    public List<Path> findAllReservationsFilePaths() {
        return paths;
    }

    @Override
    public List<Reservation> findReservationsByPath() {
        return reservations;
    }

    @Override
    public List<Reservation> findByHostId(String hostId) {
        return reservations.stream()
                .filter(i -> i.getHost().getHostId().equals(hostId))
                .collect(Collectors.toList());
    }

    @Override
    public Reservation add(Reservation reservation) {
        reservations.add(reservation);
        return reservation;
    }

    @Override
    public boolean update(Reservation reservation) {
        return reservation.getTotal().intValue() == 12345;
    }

    @Override
    public boolean delete(Reservation reservation) {
        return reservations.remove(reservation);
    }

}
