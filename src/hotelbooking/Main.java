package hotelbooking;

import hotelbooking.strategy.EarlyBirdPricing;
import hotelbooking.strategy.LongStayPricing;
import hotelbooking.strategy.PricingStrategy;
import hotelbooking.strategy.StandardPricing;
import hotelbooking.strategy.WeekendSurchargePricing;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<String, PricingStrategy> strategies = new LinkedHashMap<>();
        strategies.put("standard", new StandardPricing());
        strategies.put("weekend", new WeekendSurchargePricing(25));
        strategies.put("early-bird", new EarlyBirdPricing(30, 15));
        strategies.put("long-stay", new LongStayPricing(7));

        BookingRequest request = new BookingRequest(
                "Aigerim",
                new BigDecimal("40000.00"),          // KZT per night
                LocalDate.of(2026, 12, 3),           // Thursday
                8,
                LocalDate.of(2026, 10, 25));

        System.out.printf("Guest: %s, %d nights from %s, base rate %s KZT/night%n%n",
                request.guestName(), request.nights(), request.checkIn(), request.baseRatePerNight());

        BookingPriceCalculator calculator = new BookingPriceCalculator(strategies.get("standard"));

        for (Map.Entry<String, PricingStrategy> entry : strategies.entrySet()) {
            calculator.setPricingStrategy(entry.getValue());   // runtime switch
            System.out.printf("%-12s %-40s -> %s KZT%n",
                    entry.getKey(), calculator.currentStrategyName(), calculator.calculateTotal(request));
        }
    }
}
