package hotelbooking.strategy;

import hotelbooking.BookingRequest;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.EnumSet;
import java.util.Set;

public class WeekendSurchargePricing implements PricingStrategy {

    private static final Set<DayOfWeek> WEEKEND_NIGHTS = EnumSet.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);

    private final int surchargePercent;

    public WeekendSurchargePricing(int surchargePercent) {
        this.surchargePercent = surchargePercent;
    }

    @Override
    public BigDecimal calculateTotal(BookingRequest request) {
        BigDecimal total = BigDecimal.ZERO;
        for (int night = 0; night < request.nights(); night++) {
            total = total.add(priceOfNight(request, night));
        }
        return Money.round(total);
    }

    private BigDecimal priceOfNight(BookingRequest request, int night) {
        BigDecimal rate = request.baseRatePerNight();
        boolean isWeekend = WEEKEND_NIGHTS.contains(request.nightAt(night).getDayOfWeek());
        return isWeekend ? rate.add(Money.percentOf(rate, surchargePercent)) : rate;
    }

    @Override
    public String name() {
        return "Weekend surcharge +" + surchargePercent + "%";
    }
}
