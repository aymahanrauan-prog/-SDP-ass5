package hotelbooking;

import hotelbooking.strategy.PricingStrategy;
import java.math.BigDecimal;
import java.util.Objects;

public class BookingPriceCalculator {

    private PricingStrategy pricingStrategy;

    public BookingPriceCalculator(PricingStrategy pricingStrategy) {
        setPricingStrategy(pricingStrategy);
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = Objects.requireNonNull(pricingStrategy, "pricingStrategy");
    }

    public BigDecimal calculateTotal(BookingRequest request) {
        return pricingStrategy.calculateTotal(request);
    }

    public String currentStrategyName() {
        return pricingStrategy.name();
    }
}
