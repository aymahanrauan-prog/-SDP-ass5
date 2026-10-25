package hotelbooking.strategy;

import hotelbooking.BookingRequest;
import java.math.BigDecimal;

public class StandardPricing implements PricingStrategy {

    @Override
    public BigDecimal calculateTotal(BookingRequest request) {
        return Money.round(request.baseRatePerNight().multiply(BigDecimal.valueOf(request.nights())));
    }

    @Override
    public String name() {
        return "Standard";
    }
}
