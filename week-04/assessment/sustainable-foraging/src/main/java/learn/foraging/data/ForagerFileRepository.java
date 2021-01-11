package learn.foraging.data;

import learn.foraging.models.Forager;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Repository
public class ForagerFileRepository implements ForagerRepository {

    private static final String HEADER = "id,first_name,last_name,state";
    private static final String DELIMITER = ",";
    private static final String REPLACEMENT_DELIMITER = "$$$";
    private static final String SPACE = "";
    private final String filePath;

    public ForagerFileRepository(@Value("${foragerFilePath}")String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Forager> findAll() {
        ArrayList<Forager> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            reader.readLine(); // read header

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {

                String[] fields = line.split(",", -1);
                if (fields.length == 4) {
                    result.add(deserialize(fields));
                }
            }
        } catch (IOException ex) {
            // don't throw on read
        }
        return result;
    }

    @Override
    public Forager findById(String id) {
        return findAll().stream()
                .filter(i -> i.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Forager> findByState(String stateAbbr) {
        return findAll().stream()
                .filter(i -> i.getState().equalsIgnoreCase(stateAbbr))
                .sorted(Comparator.comparing(Forager::getFirstName))
                .collect(Collectors.toList());
    }

    @Override
    public Forager add(Forager forager) throws DataException {
        List<Forager> all = findAll();
        forager.setId(java.util.UUID.randomUUID().toString());
        all.add(forager);
        writeAll(all);
        return forager;
    }

    protected void writeAll(List<Forager> foragers) throws DataException {
        try (PrintWriter writer = new PrintWriter(filePath)) {

            writer.println(HEADER);

            if (foragers == null) {
                return;
            }

            for (Forager forager : foragers) {
                writer.println(serialize(forager));
            }

        } catch (FileNotFoundException ex) {
            throw new DataException(ex);
        }
    }

    private String serialize(Forager forager) {
        String firstName = forager.getFirstName();
        firstName = firstName.substring(0, 1).toUpperCase() +
                firstName.substring(1).toLowerCase();

        String lastName = forager.getLastName();
        lastName = lastName.substring(0, 1).toUpperCase() +
                lastName.substring(1).toLowerCase();

        return String.format("%s,%s,%s,%s",
                forager.getId(),
                cleanField(firstName),
                cleanField(lastName),
                cleanField(forager.getState().toUpperCase(Locale.ROOT)));
    }

    private Forager deserialize(String[] fields) {
        Forager result = new Forager();
        result.setId(fields[0]);
        result.setFirstName(restoreField(fields[1]));
        result.setLastName(restoreField(fields[2]));
        result.setState(restoreField(fields[3]));
        return result;
    }

    private String cleanField(String value) {
        return value.replace(DELIMITER, REPLACEMENT_DELIMITER)
                .replace("/r", SPACE)
                .replace("/n", SPACE);
    }

    private String restoreField(String value) {
        return value.replace(REPLACEMENT_DELIMITER, DELIMITER);

    }

}
