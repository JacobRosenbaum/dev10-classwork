package domain;

import data.*;
import models.Host;
import models.Reservation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final HostRepository hostRepository;
    private final GuestRepository guestRepository;


    public ReservationService(ReservationRepository reservationRepository, HostRepository hostRepository, GuestRepository guestRepository) {
        this.reservationRepository = reservationRepository;
        this.hostRepository = hostRepository;
        this.guestRepository = guestRepository;
    }


    public List<Reservation> findReservationListByHostEmail(String hostEmail) throws DataAccessException {
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

    public Reservation findReservationByGuestAndHostEmail(String hostEmail, String guestEmail) throws DataAccessException {
        if (guestEmail != null || hostEmail != null) {
            List<Reservation> reservations = findReservationListByHostEmail(hostEmail);

            for (Reservation reservation : reservations) {
                if (reservation.getGuest().getGuestEmail().equals(guestEmail)) {
                    return reservation;
                }
            }
        }
        return null;
    }

    public Result<Reservation> add(Reservation reservation) throws DataAccessException {
        Result<Reservation> result = validate(reservation);
        if (!result.isSuccess()) {
            return result;
        }

        result.setPayload(reservationRepository.add(reservation));

        return result;
    }

    public Result<Reservation> update(Reservation reservation) throws DataAccessException {
        Result<Reservation> result = validate(reservation);

        if (!result.isSuccess()) {
            return result;
        }

        if (result.isSuccess()) {
            if (reservationRepository.update(reservation)) {
                result.setPayload(reservation);
            } else {
                String message = String.format("Reservation ID: %s was not found", reservation.getReservationId());
                result.addErrorMessage(message);
            }
        }

        return result;
    }

    public Result<Reservation> delete(Reservation reservation) throws DataAccessException {
        Result<Reservation> result = validate(reservation);

        if (!result.isSuccess()) {
            return result;
        }

        if (result.isSuccess()) {
            if (reservationRepository.delete(reservation)) {
                result.setPayload(reservation);
            } else {
                String message = String.format("Reservation ID: %s was not found", reservation.getReservationId());
                result.addErrorMessage(message);
            }
        }

        return result;
    }

    private Result<Reservation> validate(Reservation reservation) throws DataAccessException {
        Result<Reservation> result = new Result<>();

        if (reservation == null) {
            result.addErrorMessage("Cannot find reservation.");
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

        validateStartDateAfterEndDate(reservation, result);

        validateDatesDoNotClash(reservation, result);

        return result;
    }

    private void validateStartDateAfterEndDate(Reservation reservation, Result<Reservation> result) throws DataAccessException {
        LocalDate startDate = reservation.getStartDate();
        LocalDate endDate = reservation.getEndDate();
        if (startDate != null && endDate != null) {
            if (endDate.isBefore(startDate) ||
                    endDate.equals(startDate)) {
                result.addErrorMessage("Reservation start date must be before end date.");
            }
        }
    }

    private void validateDatesDoNotClash(Reservation reservation, Result<Reservation> result) throws DataAccessException {
        List<Reservation> reservations = findReservationListByHostEmail(reservation.getHost().getHostEmail());
        LocalDate startDate = reservation.getStartDate();
        LocalDate endDate = reservation.getEndDate();
        if (startDate != null && endDate != null) {
            for (Reservation r : reservations) {
                if ((startDate.isBefore(r.getEndDate())) &&
                        (r.getStartDate().isBefore(endDate)) &&
                        reservation.getReservationId() != r.getReservationId()) {
                    result.addErrorMessage("Reservation cannot overlap existing reservation.");
                }
            }
        }
    }
//
//    private Result<Reservation> validateEmail(String hostEmail, String guestEmail) throws DataAccessException {
//        Result<Reservation> result = new Result<>();
//        if (guestEmail == null) {
//            result.addErrorMessage("Guest Email cannot be null.");
//            return result;
//        } else if (hostEmail == null) {
//            result.addErrorMessage("Host Email cannot be null.");
//            return result;
//        }
//        return result;
//    }
}
