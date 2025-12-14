package com.example.ds.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnclosureMetrics {

    private final MeterRegistry meterRegistry;

    public void incrementTotalEnclosureCalls(String apiVersion) {
        Counter.builder("enclosure.api.calls.total")
            .description("All Enclosure API Counts")
            .tag("version", apiVersion)
            .register(meterRegistry)
            .increment();
    }

}
