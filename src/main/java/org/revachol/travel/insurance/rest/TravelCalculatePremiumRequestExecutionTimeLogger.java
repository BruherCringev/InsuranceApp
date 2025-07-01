package org.revachol.travel.insurance.rest;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
class TravelCalculatePremiumRequestExecutionTimeLogger {

    public <T> T logExecutionTime(SupplierWithException<T> supplier) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            return supplier.get();
        } finally {
            stopwatch.stop();
            log.info("Request processing time (ms): {}", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        }
    }

    @FunctionalInterface
    interface SupplierWithException<T> {
        T get() throws RuntimeException;
    }
}