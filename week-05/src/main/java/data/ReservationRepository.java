package data;

import models.Reservation;

import java.util.List;

public interface ReservationRepository {

    List<Reservation> findByHostEmail(String hostEmail) throws DataAccessException;

    Reservation add(Reservation reservation) throws DataAccessException;

    boolean update(Reservation reservation) throws DataAccessException;

    boolean delete(Reservation reservation) throws DataAccessException;

}
