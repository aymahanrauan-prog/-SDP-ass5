package hotelbooking;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BookingRequest(
        String guestName,
        BigDecimal baseRatePerNight,
        LocalDate checkIn,
        int nights,
        LocalDate bookingDate) {

    public LocalDate nightAt(int index) {
        return checkIn.plusDays(index);
    }

    public long daysBeforeCheckIn() {
        return java.time.temporal.ChronoUnit.DAYS.between(bookingDate, checkIn);
    }
}
