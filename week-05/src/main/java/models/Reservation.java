package models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Set;

public class Reservation {

    private int reservationId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Host host;
    private Guest guest;
    private BigDecimal total;

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public BigDecimal getTotal() {
        //total exists, so we just grab it from database
        if (total != null) {
            return total;
        }
        // total does not exist, so we are adding it, and must calculate it first
        total = calculateTotal();
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal calculateTotal() {
        if (startDate == null || endDate == null) {
            return BigDecimal.ZERO;
        }

        BigDecimal weekDays = new BigDecimal(getWeekDays());
        BigDecimal weekEndDays = new BigDecimal(getWeekEndDays());

        BigDecimal weekDayTotal = weekDays.multiply(host.getStandardRate());
        BigDecimal weekEndTotal = weekEndDays.multiply(host.getWeekendRate());

        total = weekDayTotal.add(weekEndTotal).setScale(2, RoundingMode.HALF_UP);

        return total;
    }

    private Long getWeekDays() {
        Set<DayOfWeek> weekend = EnumSet.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
        return startDate.datesUntil(endDate)
                .filter(d -> !weekend.contains(d.getDayOfWeek()))
                .count();
    }

    private Long getWeekEndDays() {
        Set<DayOfWeek> weekend = EnumSet.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
        return startDate.datesUntil(endDate)
                .filter(d -> weekend.contains(d.getDayOfWeek()))
                .count();
    }

}
