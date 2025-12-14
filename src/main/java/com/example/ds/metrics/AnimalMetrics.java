package com.example.ds.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnimalMetrics {

    private final MeterRegistry meterRegistry;

    public void incrementTotalAnimalCalls(String apiVersion) {
        Counter.builder("animal.api.calls.total")
            .description("All Animal API Counts")
            .tag("version", apiVersion)
            .register(meterRegistry)
            .increment();
    }

}
