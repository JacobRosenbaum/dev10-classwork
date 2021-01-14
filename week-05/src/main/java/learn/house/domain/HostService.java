package learn.house.domain;

import learn.house.data.DataAccessException;
import learn.house.data.HostRepository;
import learn.house.models.Host;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
