package learn.house.domain;

import learn.house.data.DataAccessException;
import learn.house.data.GuestRepository;
import learn.house.models.Guest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {
    private final GuestRepository repository;

    public GuestService(GuestRepository repository) throws DataAccessException {
        this.repository = repository;
    }

    public List<Guest> findAll(Guest guest) throws DataAccessException {
        return repository.findAll();
    }

    public Guest findByEmail(String guestEmail) throws DataAccessException {
        return repository.findByEmail(guestEmail);
    }
}
