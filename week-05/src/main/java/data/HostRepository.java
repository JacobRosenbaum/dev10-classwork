package data;

import models.Host;

import java.util.List;

public interface HostRepository {

   List<Host> findAll() throws DataAccessException;

   Host findByEmail(String hostEmail) throws DataAccessException;

}
