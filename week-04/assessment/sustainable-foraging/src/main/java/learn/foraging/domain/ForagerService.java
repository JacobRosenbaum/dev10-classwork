package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ForagerRepository;
import learn.foraging.models.Forage;
import learn.foraging.models.Forager;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ForagerService {

    private final ForagerRepository repository;

    public ForagerService(ForagerRepository repository) {
        this.repository = repository;
    }

    public List<Forager> findByState(String stateAbbr) {
        return repository.findByState(stateAbbr);
    }

    public List<Forager> findByLastName(String prefix) {
        return repository.findAll().stream()
                .filter(i -> i.getLastName().startsWith(prefix.toUpperCase(Locale.ROOT)))
                .sorted(Comparator.comparing(Forager::getLastName))
                .collect(Collectors.toList());
    }

    public Result<Forager> add(Forager forager) throws DataException {
        Result<Forager> result = validate(forager);
        if (!result.isSuccess()) {
            return result;
        }

        result.setPayload(repository.add(forager));

        return result;
    }

    private Result<Forager> validate(Forager forager) {
        Result<Forager> result = new Result<>();
        List<Forager> all = repository.findAll();

        if (forager == null) {
            result.addErrorMessage("Nothing to save.");
            return result;
        }

        if (forager.getFirstName().isEmpty() || forager.getFirstName().isBlank()) {
            result.addErrorMessage("Forager first name is required.");
        }

        if (forager.getLastName().isEmpty() || forager.getLastName().isBlank()) {
            result.addErrorMessage("Forager last name is required.");
        }

        if (forager.getState().isEmpty() || forager.getState().isBlank()) {
            result.addErrorMessage("Forager state is required.");
        }

        if (forager.getState().length() != 2){
            result.addErrorMessage("Forager state must be abbreviated");
        }

        for (int i = 0; i < all.size(); i++){
            if (forager.getFirstName().equalsIgnoreCase(all.get(i).getFirstName()) &&
                    forager.getLastName().equalsIgnoreCase(all.get(i).getLastName()) &&
                    forager.getState().equalsIgnoreCase(all.get(i).getState())){
                result.addErrorMessage("Forager first name, last name, and state cannot be duplicated");
            }
        }

        return result;
    }

}
