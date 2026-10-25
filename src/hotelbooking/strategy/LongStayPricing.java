package hotelbooking.strategy;

import hotelbooking.BookingRequest;
import java.math.BigDecimal;

public class LongStayPricing implements PricingStrategy {

    private final int freeNightEvery;

    public LongStayPricing(int freeNightEvery) {
        this.freeNightEvery = freeNightEvery;
    }

    @Override
    public BigDecimal calculateTotal(BookingRequest request) {
        int freeNights = request.nights() / freeNightEvery;
        int paidNights = request.nights() - freeNights;
        return Money.round(request.baseRatePerNight().multiply(BigDecimal.valueOf(paidNights)));
    }

    @Override
    public String name() {
        return "Long stay (every " + freeNightEvery + "th night free)";
    }
}
