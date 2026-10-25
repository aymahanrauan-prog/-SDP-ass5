package hotelbooking.strategy;

import hotelbooking.BookingRequest;
import java.math.BigDecimal;

public class EarlyBirdPricing implements PricingStrategy {

    private final int minDaysInAdvance;
    private final int discountPercent;

    public EarlyBirdPricing(int minDaysInAdvance, int discountPercent) {
        this.minDaysInAdvance = minDaysInAdvance;
        this.discountPercent = discountPercent;
    }

    @Override
    public BigDecimal calculateTotal(BookingRequest request) {
        BigDecimal full = new StandardPricing().calculateTotal(request);
        return qualifies(request) ? Money.round(full.subtract(Money.percentOf(full, discountPercent))) : full;
    }

    private boolean qualifies(BookingRequest request) {
        return request.daysBeforeCheckIn() >= minDaysInAdvance;
    }

    @Override
    public String name() {
        return "Early bird -" + discountPercent + "% (" + minDaysInAdvance + "+ days ahead)";
    }
}
