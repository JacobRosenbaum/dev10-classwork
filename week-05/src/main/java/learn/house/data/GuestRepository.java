package learn.house.data;

import learn.house.models.Guest;

import java.util.List;

public interface GuestRepository {
    List<Guest> findAll() throws DataAccessException;

    Guest findByEmail(String guestEmail) throws DataAccessException;

    Guest findById(int id) throws DataAccessException;

}
