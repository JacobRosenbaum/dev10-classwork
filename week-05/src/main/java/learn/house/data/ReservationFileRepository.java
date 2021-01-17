package learn.house.data;

import learn.house.models.Guest;
import learn.house.models.Host;
import learn.house.models.Reservation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class ReservationFileRepository implements ReservationRepository {

    private static final String HEADER = "id,start_date,end_date,guest_id,total";
    private final String directory;

    public ReservationFileRepository(@Value("${reservationFilePath}") String directory) {
        this.directory = directory;
    }

    @Override
    public List<Path> findAllReservationsFilePaths() {
        try (Stream<Path> paths = Files.walk(Paths.get(directory))) {
            return paths
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            //don't throw
        }
        return null;
    }

    @Override
    public List<Reservation> findReservationsByPath() {
        List<Path> paths = findAllReservationsFilePaths();
        List<Reservation> reservations = new ArrayList<>();
        for (Path path : paths) {
            List<Reservation> list = readFile(path);
            reservations.addAll(list);
        }
        return reservations;
    }

    @Override
    public List<Reservation> findByHostId(String hostId) throws DataAccessException {
        return readFile(getFilePath(hostId));
    }

    @Override
    public Reservation add(Reservation reservation) throws DataAccessException {
        List<Reservation> all = this.findByHostId(reservation.getHost().getHostId());
        reservation.setReservationId(getNextId(all));
        all.add(reservation);
        writeAll(all, reservation.getHost().getHostId());
        return reservation;
    }

    @Override
    public boolean update(Reservation reservation) throws DataAccessException {
        List<Reservation> all = this.findByHostId(reservation.getHost().getHostId());
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getReservationId() == (reservation.getReservationId())) {
                all.set(i, reservation);
                writeAll(all, reservation.getHost().getHostId());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Reservation reservation) throws DataAccessException {
        List<Reservation> all = this.findByHostId(reservation.getHost().getHostId());
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getReservationId() == (reservation.getReservationId())) {
                all.remove(i);
                writeAll(all, reservation.getHost().getHostId());
                return true;
            }
        }
        return false;
    }

    private int getNextId(List<Reservation> reservations) {
        int maxReservationId = 0;
        for (Reservation reservation : reservations) {
            if (maxReservationId < reservation.getReservationId()) {
                maxReservationId = reservation.getReservationId();
            }
        }
        return maxReservationId + 1;
    }

    private Path getFilePath(String hostId) {
        return Paths.get(directory, hostId + ".csv");
    }

    private void writeAll(List<Reservation> reservations, String hostId) throws DataAccessException {
        try (PrintWriter writer = new PrintWriter(getFilePath(hostId).toString())) {

            writer.println(HEADER);

            for (Reservation reservation : reservations) {

                writer.println(serialize(reservation));

            }
        } catch (FileNotFoundException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }
    }

    private String serialize(Reservation reservation) {

        return String.format("%s,%s,%s,%s,%s",
                reservation.getReservationId(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getGuest().getGuestId(),
                reservation.getTotal());
    }

    private Reservation deserialize(String[] fields, Path filePath) {
        Reservation result = new Reservation();

        result.setReservationId(Integer.parseInt(fields[0]));
        result.setStartDate(LocalDate.parse(fields[1]));
        result.setEndDate(LocalDate.parse(fields[2]));

        Guest guest = new Guest();
        guest.setGuestId(Integer.parseInt(fields[3]));
        result.setGuest(guest);

        Host host = new Host();
        String fileName = filePath.getFileName().toString()
                .replace(".csv", "");
        host.setHostId(fileName);
        result.setHost(host);


        result.setTotal(BigDecimal.valueOf(Double.parseDouble(fields[4])));

        return result;
    }

    private List<Reservation> readFile(Path filePath) {
        ArrayList<Reservation> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toString()))) {

            reader.readLine(); // read header

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {

                String[] fields = line.split(",", -1);
                if (fields.length == 5) {
                    result.add(deserialize(fields, filePath));
                }
            }
        } catch (IOException ex) {
            // don't throw
        }
        return result;
    }

}
