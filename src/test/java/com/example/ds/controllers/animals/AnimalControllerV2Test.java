package com.example.ds.controllers.animals;

import com.example.ds.enumerations.AnimalStatus;
import com.example.ds.models.dtos.animals.AnimalDTOV2;
import com.example.ds.models.entities.Animal;
import com.example.ds.services.animals.AnimalServiceV2;
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
@WebMvcTest(AnimalControllerV2.class)
class AnimalControllerV2Test {

    AnimalDTOV2 animalDTOV2;

    @MockitoBean
    AnimalServiceV2 animalServiceV2;

    @Autowired
    MockMvc mockMvc;

    RestTestClient restTestClient;

    @BeforeEach
    void setUp() {
        animalDTOV2 = new AnimalDTOV2(
            1, "Tundra", "Female","Tiger", 5, 1
        );
        restTestClient = RestTestClient.bindTo(mockMvc).build();
    }

    @Test
    void test_create_and_delete_animal() {
        when(animalServiceV2.createAnimal(any()))
            .thenReturn(animalDTOV2);

        restTestClient.post()
            .uri("/api/v2/animal/")
            .contentType(MediaType.APPLICATION_JSON)
            .body(new AnimalDTOV2(null, "Tundra", "Female", "Tiger", 5, 1))
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
            .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("Tundra")
                .jsonPath("$.species").isEqualTo("Tiger")
                .jsonPath("$.sex").isEqualTo("Female")
                .jsonPath("$.age").isEqualTo(5)
                .jsonPath("$.enclosure").isEqualTo(1);

        restTestClient.delete()
            .uri("/api/v2/animal/1")
            .exchange()
            .expectStatus().isOk();

    }

    @Test
    void test_get_all_animals() {
        when(animalServiceV2.getAllAnimals()).thenReturn(
            List.of(animalDTOV2)
        );

        var animals = restTestClient.get()
            .uri("/api/v2/animal/")
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
        when(animalServiceV2.getAnimal(1)).thenReturn(animalDTOV2);

        restTestClient.get()
            .uri("/api/v2/animal/{id}", 1)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("Tundra")
                .jsonPath("$.species").isEqualTo("Tiger")
                .jsonPath("$.sex").isEqualTo("Female")
                .jsonPath("$.age").isEqualTo(5)
                .jsonPath("$.enclosure").isEqualTo(1);
    }

    @Test
    void test_update_animal() {
        var updatedAnimal = new AnimalDTOV2(
            1, "Tundra", "Female", "Tiger", 6, 1
        );

        when(animalServiceV2.updateAnimal(eq(1), any(AnimalDTOV2.class)))
            .thenReturn(updatedAnimal);

        restTestClient.put()
            .uri("/api/v2/animal/{id}", 1)
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
                .jsonPath("$.age").isEqualTo(6)
                .jsonPath("$.enclosure").isEqualTo(1);
    }

}
