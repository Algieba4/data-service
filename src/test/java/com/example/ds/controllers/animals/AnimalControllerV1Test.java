package com.example.ds.controllers.animals;

import com.example.ds.metrics.AnimalMetrics;
import com.example.ds.models.dtos.animals.AnimalDTOV1;
import com.example.ds.models.entities.Animal;
import com.example.ds.services.animals.AnimalServiceV1;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * In order for these test cases to work, you have to run with WebMvcTest
 * since Mocking the Controller class won't load the WebConfig configuration class.
 * This will run slightly slower, but more accurate, tests.
 */
@WebMvcTest(AnimalControllerV1.class)
class AnimalControllerV1Test {

    AnimalDTOV1 animalDTOV1;

    @MockitoBean
    AnimalMetrics animalMetrics;

    @MockitoBean
    AnimalServiceV1 animalServiceV1;

    @Autowired
    MockMvc mockMvc;

    RestTestClient restTestClient;

    @BeforeEach
    void setUp() {
        animalDTOV1 = new AnimalDTOV1(1, "Tundra", "Female", "Tiger", 5);
        restTestClient = RestTestClient.bindTo(mockMvc).build();
    }

    @Test
    void test_create_and_delete_animal() {
        when(animalServiceV1.createAnimal(any()))
            .thenReturn(animalDTOV1);

        restTestClient.post()
            .uri("/api/v1/animal/")
            .contentType(MediaType.APPLICATION_JSON)
            .body(new AnimalDTOV1(null, "Tundra", "Female", "Tiger", 5))
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
            .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("Tundra")
                .jsonPath("$.species").isEqualTo("Tiger")
                .jsonPath("$.sex").isEqualTo("Female")
                .jsonPath("$.age").isEqualTo(5);

        restTestClient.delete()
            .uri("/api/v1/animal/1")
            .exchange()
            .expectStatus().isOk();

    }

    @Test
    void test_get_all_animals() {
        when(animalServiceV1.getAllAnimals()).thenReturn(
            List.of(animalDTOV1)
        );

        var animals = restTestClient.get()
            .uri("/api/v1/animal/")
            .exchange()
            .expectStatus().isOk()
            .expectBody(new ParameterizedTypeReference<List<Animal>>() {})
            .returnResult()
            .getResponseBody();

        assertNotNull(animals);
        assertEquals(1, animals.size());
        assertEquals("Tundra", animals.getFirst().getName());
    }

    @Test
    void test_get_animal() {
        when(animalServiceV1.getAnimal(1)).thenReturn(animalDTOV1);

        restTestClient.get()
            .uri("/api/v1/animal/{id}", 1)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("Tundra")
                .jsonPath("$.species").isEqualTo("Tiger")
                .jsonPath("$.sex").isEqualTo("Female")
                .jsonPath("$.age").isEqualTo(5);
    }

    @Test
    void test_update_animal() {
        var updatedAnimal = new AnimalDTOV1(1, "Tundra", "Female", "Tiger", 6);

        when(animalServiceV1.updateAnimal(eq(1), any(AnimalDTOV1.class)))
            .thenReturn(updatedAnimal);

        restTestClient.put()
            .uri("/api/v1/animal/{id}", 1)
            .contentType(MediaType.APPLICATION_JSON)
            .body(updatedAnimal)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
            .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("Tundra")
                .jsonPath("$.species").isEqualTo("Tiger")
                .jsonPath("$.sex").isEqualTo("Female")
                .jsonPath("$.age").isEqualTo(6);
    }

}
