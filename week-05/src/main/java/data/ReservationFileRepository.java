package data;

import models.Guest;
import models.Host;
import models.Reservation;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationFileRepository implements ReservationRepository {

    private static final String HEADER = "id,start_date,end_date,guest_id,total";
    private final String directory;
    private final HostFileRepository hostFileRepository;

    public ReservationFileRepository(String directory, HostFileRepository hostFileRepository) {
        this.directory = directory;
        this.hostFileRepository = hostFileRepository;
    }

    @Override
    public List<Reservation> findByHostEmail(String hostEmail) throws DataAccessException {
        ArrayList<Reservation> result = new ArrayList<>();
        String hostId;
        Host host = hostFileRepository.findByEmail(hostEmail);
        if (host == null) {
            return result;
        } else {
            hostId = host.getHostId();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath(hostId)))) {

            reader.readLine(); // read header

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {

                String[] fields = line.split(",", -1);
                if (fields.length == 5) {
                    result.add(deserialize(fields));
                }
            }
        } catch (IOException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }
        return result;
    }

    @Override
    public Reservation add(Reservation reservation) throws DataAccessException {
        List<Reservation> all = this.findByHostEmail(reservation.getHost().getHostEmail());
        reservation.setReservationId(getNextId(all));
        all.add(reservation);
        writeAll(all, reservation.getHost().getHostId(), true);
        return reservation;
    }

    @Override
    public boolean update(Reservation reservation) throws DataAccessException {
        List<Reservation> all = this.findByHostEmail(reservation.getHost().getHostId());
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getReservationId() == (reservation.getReservationId())) {
                all.set(i, reservation);
                writeAll(all, reservation.getHost().getHostId(), false);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Reservation reservation) throws DataAccessException {
        List<Reservation> all = this.findByHostEmail(reservation.getHost().getHostId());
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getReservationId() == (reservation.getReservationId())) {
                all.remove(i);
                writeAll(all, reservation.getHost().getHostId(), true);
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

    private String getFilePath(String hostId) {
        return Paths.get(directory, hostId + ".csv").toString();
    }

    private void writeAll(List<Reservation> reservations, String hostId, boolean add) throws DataAccessException {
        try (PrintWriter writer = new PrintWriter(getFilePath(hostId))) {

            writer.println(HEADER);

            for (Reservation reservation : reservations) {
                if (add) {
                    writer.println(serialize(reservation, true));
                } else {
                    writer.println(serialize(reservation, false));
                }
            }
        } catch (FileNotFoundException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }
    }

    private String serialize(Reservation reservation, boolean add) {
        BigDecimal total;
        if (add) {
            total = reservation.getTotal();
        } else {
            total = reservation.calculateTotal();
        }

        return String.format("%s,%s,%s,%s,%s",
                reservation.getReservationId(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getGuest().getGuestId(),
                total);
    }

    private Reservation deserialize(String[] fields) {
        int startYear = Integer.parseInt(fields[1].substring(0, 4));
        int startMonth = Integer.parseInt(fields[1].substring(5, 7));
        int startDay = Integer.parseInt(fields[1].substring(8, 10));

        int endYear = Integer.parseInt(fields[2].substring(0, 4));
        int endMonth = Integer.parseInt(fields[2].substring(5, 7));
        int endDay = Integer.parseInt(fields[2].substring(8, 10));

        Reservation result = new Reservation();

        result.setReservationId(Integer.parseInt(fields[0]));
        result.setStartDate(LocalDate.of(startYear, startMonth, startDay));
        result.setEndDate(LocalDate.of(endYear, endMonth, endDay));

        Guest guest = new Guest();
        guest.setGuestId(Integer.parseInt(fields[3]));
        result.setGuest(guest);

        result.setTotal(BigDecimal.valueOf(Double.parseDouble(fields[4])));

        return result;
    }

}
