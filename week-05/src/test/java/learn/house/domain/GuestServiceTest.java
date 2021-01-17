package learn.house.domain;

import learn.house.data.DataAccessException;
import learn.house.data.GuestRepositoryDouble;
import learn.house.data.HostRepositoryDouble;
import learn.house.models.Guest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuestServiceTest {

    GuestService service;

    @BeforeEach
    void setUp() {
        service = new GuestService(
                new GuestRepositoryDouble());
    }

    @Test
    void shouldFindAllShouldReturnCorrectNumberOfGuests() throws DataAccessException {
        List<Guest> all = service.findAll();

        assertEquals(1, all.size());
    }

    @Test
    void shouldFindAllShouldReturnCorrectData() throws DataAccessException {
        List<Guest> all = service.findAll();

        assertEquals("Lomas", all.get(0).getFirstName());
    }

    @Test
    void shouldFindByEmail() throws DataAccessException {
        Guest guest = service.findByEmail("slomas0@mediafire.com");

        assertEquals("Lomas", guest.getFirstName());
    }

    @Test
    void shouldNotFindMissingEmail() throws DataAccessException {
        Guest guest = service.findByEmail("jacobrosenbaum95@gmail.com");

        assertNull(guest);
    }

}