package learn.unexplained.data;

import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;

import java.util.ArrayList;
import java.util.List;

public class EncounterRepositoryDouble implements EncounterRepository {

    private ArrayList<Encounter> encounters = new ArrayList<>();

    public EncounterRepositoryDouble() {
        encounters.add(new Encounter(1, EncounterType.CREATURE, "2018-04-03", "A crazy encounter", 0));

    }

    @Override
    public List<Encounter> findAll() throws DataAccessException {
        return List.of(new Encounter(2, EncounterType.CREATURE, "1/1/2015", "test description", 1));
    }

    @Override
    public Encounter add(Encounter encounter) throws DataAccessException {
        return encounter;
    }

    @Override
    public boolean deleteById(int encounterId) throws DataAccessException {
        return false;
    }

    @Override
    public List<Encounter> findByType(EncounterType type) throws DataAccessException {
        ArrayList<Encounter> result = new ArrayList<>();
        for (Encounter e : encounters) {
            if (type == e.getType()) {
                result.add(e);
            }
        }

        return result;
    }

    @Override
    public Encounter findById(int encounterId) throws DataAccessException {
        return null;
    }

    @Override
    public boolean update(Encounter encounter) throws DataAccessException {
        return false;
    }
}
