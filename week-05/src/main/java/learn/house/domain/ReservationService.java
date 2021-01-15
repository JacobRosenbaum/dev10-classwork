package learn.house.domain;

import learn.house.data.*;
import learn.house.models.Host;
import learn.house.models.Guest;
import learn.house.models.Reservation;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
        Map<Integer, Guest> guestMap = guestRepository.findAll()
                .stream()
                .collect(Collectors.toMap(Guest::getGuestId, i -> i));

        Host host = hostRepository.findByEmail(hostEmail);
        List<Reservation> reservations = new ArrayList<>();

        String hostId;
        if (host == null) {
            return reservations;
        } else {
            hostId = host.getHostId();
        }

        reservations = reservationRepository.findByHostId(hostId);

        for (Reservation reservation : reservations) {
            reservation.setGuest(guestMap.get(reservation.getGuest().getGuestId()));
            reservation.setHost(host);
        }

        return reservations.stream()
                .sorted(Comparator.comparing(Reservation::getStartDate))
                .collect(Collectors.toList());
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

    public BigDecimal calculateTotal(LocalDate startDate, LocalDate endDate, Host host) {
        if (startDate == null || endDate == null || host == null) {
            return BigDecimal.ZERO;
        }

        BigDecimal weekDays = new BigDecimal(getWeekDays(startDate, endDate));
        BigDecimal weekEndDays = new BigDecimal(getWeekEndDays(startDate, endDate));

        BigDecimal weekDayTotal = weekDays.multiply(host.getStandardRate());
        BigDecimal weekEndTotal = weekEndDays.multiply(host.getWeekendRate());

        return weekDayTotal.add(weekEndTotal).setScale(2, RoundingMode.HALF_UP);

    }

    private Long getWeekDays(LocalDate startDate, LocalDate endDate) {
        Set<DayOfWeek> weekend = EnumSet.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
        return startDate.datesUntil(endDate)
                .filter(d -> !weekend.contains(d.getDayOfWeek()))
                .count();
    }

    private Long getWeekEndDays(LocalDate startDate, LocalDate endDate) {
        Set<DayOfWeek> weekend = EnumSet.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
        return startDate.datesUntil(endDate)
                .filter(d -> weekend.contains(d.getDayOfWeek()))
                .count();
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
