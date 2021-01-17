package learn.house.domain;

import learn.house.data.DataAccessException;
import learn.house.data.GuestRepositoryDouble;
import learn.house.data.HostRepositoryDouble;
import learn.house.data.ReservationRepositoryDouble;
import learn.house.models.Host;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HostServiceTest {

    HostService service;

    @BeforeEach
    void setUp() {
        service = new HostService(
                new HostRepositoryDouble());
    }

    @Test
    void shouldFindAllShouldReturnCorrectNumberOfHosts() throws DataAccessException {
        List<Host> all = service.findAll();

        assertEquals(1, all.size());
    }

    @Test
    void shouldFindAllShouldReturnCorrectData() throws DataAccessException {
        List<Host> all = service.findAll();

        assertEquals("Yearnes", all.get(0).getLastName());
    }

    @Test
    void shouldFindByEmail() throws DataAccessException {
        Host host = service.findByEmail("eyearnes0@sfgate.com");

        assertEquals("Yearnes", host.getLastName());
    }

    @Test
    void shouldNotFindMissingEmail() throws DataAccessException {
        Host host = service.findByEmail("jacobrosenbaum95@gmail.com");

        assertNull(host);
    }

}