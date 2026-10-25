package hotelbooking.strategy;

import hotelbooking.BookingRequest;
import java.math.BigDecimal;

public interface PricingStrategy {

    BigDecimal calculateTotal(BookingRequest request);

    String name();
}
