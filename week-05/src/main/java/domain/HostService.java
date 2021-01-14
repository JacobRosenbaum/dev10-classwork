package domain;

import data.DataAccessException;
import data.GuestRepository;
import data.HostRepository;
import models.Guest;
import models.Host;

import java.util.List;

public class HostService {
    private final HostRepository repository;

    public HostService(HostRepository repository) throws DataAccessException {
        this.repository = repository;
    }

    public List<Host> findAll(Host host) throws DataAccessException {
        return repository.findAll();
    }

    public Host findByEmail(String hostEmail) throws DataAccessException {
        return repository.findByEmail(hostEmail);
    }
}
