package hotelbooking.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

final class Money {

    private Money() { }

    static BigDecimal round(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    static BigDecimal percentOf(BigDecimal amount, int percent) {
        return amount.multiply(BigDecimal.valueOf(percent)).divide(BigDecimal.valueOf(100));
    }
}
