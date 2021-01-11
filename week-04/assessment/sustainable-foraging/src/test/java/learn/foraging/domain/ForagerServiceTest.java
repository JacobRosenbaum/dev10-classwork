package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ForagerRepositoryDouble;
import learn.foraging.data.ItemRepositoryDouble;
import learn.foraging.models.Forage;
import learn.foraging.models.Forager;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ForagerServiceTest {

    ForagerService service = new ForagerService(new ForagerRepositoryDouble());

    @Test
    void shouldFindByState() throws DataException {
        List<Forager> expected = service.findByState("GA");

        assertEquals(expected.get(0).getLastName(), "Sisse");

    }

    @Test
    void shouldNotFindMissingState() throws DataException {
        List<Forager> expected = service.findByState("WI");

        assertEquals(expected.size(), 0);
    }

    //            * [ ] shouldFindByLastName
    @Test
    void shouldFindByLastName() throws DataException {
        List<Forager> expected = service.findByLastName("S");

        assertEquals(expected.get(0).getLastName(), "Sisse");
    }

    @Test
    void shouldFindByLastNameEvenIfLowerCase() throws DataException {
        List<Forager> expected = service.findByLastName("s");

        assertEquals(expected.get(0).getLastName(), "Sisse");
    }

    @Test
    void shouldNotFindByMissingLastName() throws DataException {
        List<Forager> expected = service.findByLastName("W");

        assertEquals(expected.size(), 0);
    }

    @Test
    void shouldAddForager() throws DataException {
        Forager forager = new Forager();
        forager.setFirstName("Joe");
        forager.setLastName("Smith");
        forager.setState("WI");

        Result<Forager> result = service.add(forager);
        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotAddDuplicateForager() throws DataException {
        Forager forager = new Forager();
        forager.setFirstName("Jilly");
        forager.setLastName("Sisse");
        forager.setState("GA");

        Result<Forager> result = service.add(forager);
        assertEquals(result.getErrorMessages().get(0), "Forager first name, last name, and state cannot be duplicated");
    }

    @Test
    void shouldNotAddNullForager() throws DataException {
        Forager forager = null;

        Result<Forager> result = service.add(forager);
        assertEquals(result.getErrorMessages().get(0), "Nothing to save.");
    }

    @Test
    void shouldNotAddMissingFirstName() throws DataException {
        Forager forager = new Forager();
        forager.setFirstName("");
        forager.setLastName("Sisse");
        forager.setState("GA");

        Result<Forager> result = service.add(forager);
        assertEquals(result.getErrorMessages().get(0), "Forager first name is required.");
    }

    @Test
    void shouldNotAddMissingLastName() throws DataException {
        Forager forager = new Forager();
        forager.setFirstName("Joe");
        forager.setLastName("");
        forager.setState("GA");

        Result<Forager> result = service.add(forager);
        assertEquals(result.getErrorMessages().get(0), "Forager last name is required.");
    }

    @Test
    void shouldNotAddMissingState() throws DataException {
        Forager forager = new Forager();
        forager.setFirstName("Joe");
        forager.setLastName("Smith");
        forager.setState("");

        Result<Forager> result = service.add(forager);
        assertEquals(result.getErrorMessages().get(0), "Forager state is required.");
    }

    @Test
    void shouldNotAddNonAbbrState() throws DataException {
        Forager forager = new Forager();
        forager.setFirstName("Joe");
        forager.setLastName("Smith");
        forager.setState("Alaska");

        Result<Forager> result = service.add(forager);
        assertEquals(result.getErrorMessages().get(0), "Forager state must be abbreviated");
    }
}
