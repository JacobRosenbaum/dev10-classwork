package learn.unexplained.data;

import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;

import java.util.ArrayList;
import java.util.List;

public class EncounterRepositoryDouble implements EncounterRepository {

    private ArrayList<Encounter> encounters = new ArrayList<>(List.of(
            new Encounter(1, EncounterType.UFO,
                    "2020-01-01", "short test #1", 1),
            new Encounter(2, EncounterType.CREATURE,
                    "2020-02-01", "short test #2", 1),
            new Encounter(3, EncounterType.SOUND,
                    "2020-03-01", "short test #3", 1)

    ));

    @Override
    public List<Encounter> findAll() throws DataAccessException {
        return encounters;
    }

    @Override
    public Encounter findById(int encounterId) throws DataAccessException {
        return null;
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
    public Encounter add(Encounter encounter) throws DataAccessException {
        return encounter;
    }

    @Override
    public boolean update(Encounter encounter) throws DataAccessException {
        return encounter.getEncounterId() == 2;
    }

    @Override
    public boolean deleteById(int encounterId) throws DataAccessException {
        return encounterId == 2;    }
}
