package learn.house.data;

import learn.house.models.Reservation;

import java.util.List;

public interface ReservationRepository {

    List<Reservation> findByHostId(String hostId) throws DataAccessException;

    Reservation add(Reservation reservation) throws DataAccessException;

    boolean update(Reservation reservation) throws DataAccessException;

    boolean delete(Reservation reservation) throws DataAccessException;

}
