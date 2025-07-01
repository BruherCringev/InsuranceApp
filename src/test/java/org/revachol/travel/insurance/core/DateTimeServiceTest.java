package org.revachol.travel.insurance.core;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DateTimeServiceTest {
    private DateTimeService timeService = new DateTimeService();

    @Test
    public void testTimeServicePositive() {
        LocalDate date1 = LocalDate.of(2025, 01, 10);
        LocalDate date2 = LocalDate.of(2025, 01, 20);
        var daysBetween = timeService.calculateDaysBetween(date1, date2);
        assertEquals(10, daysBetween);
    }
    @Test
    public void testTimeServiceNegative() {
        LocalDate date1 = LocalDate.of(2025, 10, 10);
        LocalDate date2 = LocalDate.of(2025, 10, 5);
        var daysBetween = timeService.calculateDaysBetween(date1, date2);
        assertEquals(-5, daysBetween);
    }
    @Test
    public void testTimeServiceZero() {
        LocalDate date1 = LocalDate.of(2025, 01, 10);
        LocalDate date2 = LocalDate.of(2025, 01, 10);
        var daysBetween = timeService.calculateDaysBetween(date1, date2);
        assertEquals(0, daysBetween);
    }
}
