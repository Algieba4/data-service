package com.example.ds.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnimalMetricsTest {

    private SimpleMeterRegistry meterRegistry;
    private AnimalMetrics animalMetrics;

    @BeforeEach
    void setUp() {
        meterRegistry = new SimpleMeterRegistry();
        animalMetrics = new AnimalMetrics(meterRegistry);
    }

    @Test
    void test_increment_total_animal_calls_single() {
        // given
        String version = "v1";

        // when
        animalMetrics.incrementTotalAnimalCalls(version);

        // then
        Counter counter = meterRegistry
            .find("animal.api.calls.total")
            .tag("version", version)
            .counter();

        assertThat(counter).isNotNull();
        assertThat(counter.count()).isEqualTo(1.0);
    }

    @Test
    void test_increment_total_animal_calls_multiple() {
        // given
        String version = "v1";

        // when
        animalMetrics.incrementTotalAnimalCalls(version);
        animalMetrics.incrementTotalAnimalCalls(version);

        // then
        Counter counter = meterRegistry
            .find("animal.api.calls.total")
            .tag("version", version)
            .counter();

        assertThat(counter).isNotNull();
        assertThat(counter.count()).isEqualTo(2.0);
    }

}
