import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.time.temporal.ChronoUnit;

public class Exercise01 {

    // LocalDate
    // ========================
    // Complete the numbered tasks below.
    // Open and execute the accompanying tests to confirm your solution is correct.

    // 1. return today's date
    LocalDate getToday() {
        LocalDate today = LocalDate.now();
        return today;
    }

    // 2. return December 17, 1903 as a LocalDate
    LocalDate getFirstFlightDate() {
        LocalDate firstFlight = LocalDate.of(1903, 12, 17);
        return firstFlight;
    }

    // 3. if parameter is in the future, return null.
    // Otherwise, add 5 days to the parameter and return the result.
    LocalDate makeFutureNullShiftThePast(LocalDate date) {
        LocalDate now = LocalDate.now();
        if (date.isAfter(now)) {
            return null;
        } else {
            return date.plusDays(5);
        }
    }

    // 4. return the fifth Friday from the parameter date.
    // if the date is Friday, don't count it.
    LocalDate fiveFridaysFromDate(LocalDate date) {

//        LocalDate stop = date.plusWeeks(5);
//        int count;
//        LocalDate fifthFriday = LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth());
//
//        for (; date.compareTo(stop) < 0; ) {
//            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
//                count++;
//            }
//            if (count == 5) {
//                return null;
//            }
//        }
        return null;
    }

    // 5. given a date and a count,
    // return a list of the next `fridayCount` Fridays after the date.
    // if the date is Friday, don't include it.
    List<LocalDate> getNextFridays(LocalDate date, int fridayCount) {
        return null;
    }

    // 6. return the absolute value of the days between two dates.
    // one may be before two, two may be before one, but neither will be null
    int getDaysBetween(LocalDate one, LocalDate two) {

        return Math.abs((int)ChronoUnit.DAYS.between(one, two));

    }

}
