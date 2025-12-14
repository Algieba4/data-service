package com.example.ds.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EnclosureMetricsTest {

    private SimpleMeterRegistry meterRegistry;
    private EnclosureMetrics enclosureMetrics;

    @BeforeEach
    void setUp() {
        meterRegistry = new SimpleMeterRegistry();
        enclosureMetrics = new EnclosureMetrics(meterRegistry);
    }

    @Test
    void test_increment_total_enclosure_calls_single() {
        // given
        String version = "v1";

        // when
        enclosureMetrics.incrementTotalEnclosureCalls(version);

        // then
        Counter counter = meterRegistry
            .find("enclosure.api.calls.total")
            .tag("version", version)
            .counter();

        assertThat(counter).isNotNull();
        assertThat(counter.count()).isEqualTo(1.0);
    }

    @Test
    void test_increment_total_enclosure_calls_multiple() {
        // given
        String version = "v1";

        // when
        enclosureMetrics.incrementTotalEnclosureCalls(version);
        enclosureMetrics.incrementTotalEnclosureCalls(version);

        // then
        Counter counter = meterRegistry
            .find("enclosure.api.calls.total")
            .tag("version", version)
            .counter();

        assertThat(counter).isNotNull();
        assertThat(counter.count()).isEqualTo(2.0);
    }

}
