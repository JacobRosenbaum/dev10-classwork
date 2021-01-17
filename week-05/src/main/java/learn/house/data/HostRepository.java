package learn.house.data;

import learn.house.models.Host;

import java.util.List;

public interface HostRepository {

   List<Host> findAll() throws DataAccessException;

   Host findByEmail(String hostEmail) throws DataAccessException;

   public Host findById(String hostId) throws DataAccessException;

}
