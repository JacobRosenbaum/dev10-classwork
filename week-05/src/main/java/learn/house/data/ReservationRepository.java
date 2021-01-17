package learn.house.data;

import learn.house.models.Reservation;

import java.nio.file.Path;
import java.util.List;

public interface ReservationRepository {

    List<Path> findAllReservationsFilePaths();

    public List<Reservation> findReservationsByPath();

    List<Reservation> findByHostId(String hostId) throws DataAccessException;

    Reservation add(Reservation reservation) throws DataAccessException;

    boolean update(Reservation reservation) throws DataAccessException;

    boolean delete(Reservation reservation) throws DataAccessException;

}
