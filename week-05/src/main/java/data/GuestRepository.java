package data;

import models.Guest;

import java.util.List;

public interface GuestRepository {
    List<Guest> findAll() throws DataAccessException;

    Guest findByEmail(String guestEmail) throws DataAccessException;
}
