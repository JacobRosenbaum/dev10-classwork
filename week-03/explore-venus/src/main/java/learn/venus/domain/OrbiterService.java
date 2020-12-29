package learn.venus.domain;

import learn.venus.data.DataAccessException;
import learn.venus.data.OrbiterRepository;
import learn.venus.models.Orbiter;
import learn.venus.models.OrbiterType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrbiterService {

    private final OrbiterRepository repository;

    public OrbiterService(OrbiterRepository repository) {
        this.repository = repository;

    }

    public List<Orbiter> findByType(OrbiterType type) throws DataAccessException{
        return repository.findByType(type);
    }

    public OrbiterResult add(Orbiter orbiter) throws DataAccessException {
        OrbiterResult result = validateInput(orbiter);

        if (!result.isSuccess()) {
            return result;
        }

        Map<OrbiterType, Integer> counts = countTypes();
        counts.put(orbiter.getType(), counts.get(orbiter.getType()) + 1);
        result = validateDomain(counts);
        if (!result.isSuccess()) {
            return result;
        }

        Orbiter o = repository.add(orbiter);
        result.setPayLoad(o);

        return result;
    }


    public OrbiterResult update(Orbiter orbiter) throws DataAccessException {
        OrbiterResult result = validateInput(orbiter);

        if (!result.isSuccess()) {
            return result;
        }

        Orbiter existing = repository.findById(orbiter.getOrbiterId());
        if (existing == null) {
            result.addErrorMessages("Orbiter Id " + orbiter.getOrbiterId() + " not found");
            return result;
        }

        if (existing.getType() != orbiter.getType()) {
            result.addErrorMessages("Cannot update type.");
        }

        boolean success = repository.update(orbiter);

        if (!success) {
            result.addErrorMessages("Could not find Orbiter Id " + orbiter.getOrbiterId());
        }

        return result;

    }

    public OrbiterResult deleteById(int orbiterId) throws DataAccessException {
        OrbiterResult result = new OrbiterResult();
        Orbiter orbiter = repository.findById(orbiterId);

        if (orbiter == null) {
            result.addErrorMessages("Could not find Orbiter Id " + orbiter.getOrbiterId());
            return result;
        }

        Map<OrbiterType, Integer> counts = countTypes();
        counts.put(orbiter.getType(), counts.get(orbiter.getType()) - 1);
        result = validateDomain(counts);

        if (!result.isSuccess()) {
            return result;
        }

        boolean success = repository.deleteById(orbiterId);

        if (!success) {
            result.addErrorMessages("Could not find Orbiter Id " + orbiter.getOrbiterId());
            return result;
        }

        return result;
    }

    private OrbiterResult validateInput(Orbiter orbiter) {
        OrbiterResult result = new OrbiterResult();

        if (orbiter == null) {
            result.addErrorMessages("orbiter cannot be null");
            return result;
        }

        if (orbiter.getName() == null || orbiter.getName().trim().length() == 0) {
            result.addErrorMessages("name is required");
        }
        return result;
    }

    private HashMap<OrbiterType, Integer> countTypes() {
        HashMap<OrbiterType, Integer> counts = new HashMap<>();
        counts.put(OrbiterType.MODULE, 0);
        counts.put(OrbiterType.MODULE_WITH_DOCK, 0);
        counts.put(OrbiterType.SHUTTLE, 0);
        counts.put(OrbiterType.ASTRONAUT, 0);
        counts.put(OrbiterType.VENUSIAN, 0);

        try {
            List<Orbiter> allOrbiters = repository.findAll();
            for (Orbiter o : allOrbiters) {
                switch (o.getType()) {
                    case MODULE:
                        counts.put(OrbiterType.MODULE, counts.get(OrbiterType.MODULE) + 1);
                        break;
                    case MODULE_WITH_DOCK:
                        counts.put(OrbiterType.MODULE_WITH_DOCK, counts.get(OrbiterType.MODULE_WITH_DOCK) + 1);
                        break;
                    case SHUTTLE:
                        counts.put(OrbiterType.SHUTTLE, counts.get(OrbiterType.SHUTTLE) + 1);

                        break;
                    case ASTRONAUT:
                        counts.put(OrbiterType.ASTRONAUT, counts.get(OrbiterType.ASTRONAUT) + 1);
                        break;
                    case VENUSIAN:
                        counts.put(OrbiterType.VENUSIAN, counts.get(OrbiterType.VENUSIAN) + 1);
                        break;
                }
            }


        } catch (DataAccessException e) {

        }
        return counts;
    }

    private OrbiterResult validateDomain(Map<OrbiterType, Integer> counts) {

        int astroCount = counts.get(OrbiterType.ASTRONAUT);
        int shuttleCount = counts.get(OrbiterType.SHUTTLE);
        int dockCount = counts.get(OrbiterType.MODULE_WITH_DOCK);
        int modCount = counts.get(OrbiterType.MODULE);

        OrbiterResult result = new OrbiterResult();

        if (astroCount > modCount * 4 + dockCount * 2) {
            result.addErrorMessages("no room for an astronaut");
        }

        if (shuttleCount > dockCount) {
            result.addErrorMessages("no room for a shuttle");
        }
        return result;
    }
}
