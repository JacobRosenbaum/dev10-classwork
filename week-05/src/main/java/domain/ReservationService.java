package domain;

import data.*;
import models.Host;
import models.Reservation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final HostRepository hostRepository;
    private final GuestRepository guestRepository;


    public ReservationService(ReservationRepository reservationRepository, HostRepository hostRepository, GuestRepository guestRepository) {
        this.reservationRepository = reservationRepository;
        this.hostRepository = hostRepository;
        this.guestRepository = guestRepository;
    }


    public List<Reservation> findByHostEmail(String hostEmail) throws DataAccessException {
        List<Reservation> result = new ArrayList<>();

        Host host = hostRepository.findByEmail(hostEmail);

        String hostId;
        if (host == null) {
            return result;
        } else {
            hostId = host.getHostId();
        }

        result = reservationRepository.findByHostId(hostId);


        return result;
    }

    public Result<Reservation> add(Reservation reservation) throws DataAccessException {
        Result<Reservation> result = validate(reservation);
        if (!result.isSuccess()) {
            return result;
        }

        result.setPayload(reservationRepository.add(reservation));

        return result;
    }

    private Result<Reservation> validate(Reservation reservation) throws DataAccessException {
        Result<Reservation> result = new Result<>();

        if (reservation == null) {
            result.addErrorMessage("Nothing to save.");
            return result;
        }

        if (reservation.getGuest() == null) {
            result.addErrorMessage("Reservation guest is required.");
        }

        if (reservation.getStartDate() == null) {
            result.addErrorMessage("Reservation start date is required.");
        }

        if (reservation.getEndDate() == null) {
            result.addErrorMessage("Reservation end date is required.");
        }

        if (reservation.getEndDate().isBefore(reservation.getStartDate()) ||
                reservation.getEndDate().equals(reservation.getStartDate())) {
            result.addErrorMessage("Reservation start date must be before end date.");
        }

        validateDatesDoNotClash(reservation, result);

        return result;
    }

    private void validateDatesDoNotClash(Reservation reservation, Result<Reservation> result) throws DataAccessException {
        List<Reservation> reservations = findByHostEmail(reservation.getHost().getHostEmail());
        LocalDate startDate = reservation.getStartDate();
        LocalDate endDate = reservation.getEndDate();

        for (Reservation r : reservations) {
            if ((startDate.isBefore(r.getEndDate())) &&
                    (r.getStartDate().isAfter(endDate))) ;
            result.addErrorMessage("Reservation cannot overlap existing reservation.");
        }
    }
}
