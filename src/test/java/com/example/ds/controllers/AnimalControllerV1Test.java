package com.example.ds.controllers;

import com.example.ds.models.dtos.AnimalDTO;
import com.example.ds.models.entities.Animal;
import com.example.ds.services.AnimalService;
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
@WebMvcTest(AnimalControllerV1.class)
class AnimalControllerV1Test {

    @MockitoBean
    AnimalService animalService;

    @Autowired
    MockMvc mockMvc;

    RestTestClient restTestClient;

    @BeforeEach
    void setUp() {
        restTestClient = RestTestClient.bindTo(mockMvc).build();
    }

    @Test
    void test_create_and_delete_animal() {
        when(animalService.createAnimal(any()))
            .thenReturn(new AnimalDTO(
                1, "Tundra", "Tiger", "Female", 5, 1
            ));

        restTestClient.post()
            .uri("/api/v1/animal/")
            .contentType(MediaType.APPLICATION_JSON)
            .body(new AnimalDTO(null, "Tundra", "Tiger", "Female", 5, 1))
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
        when(animalService.getAllAnimals()).thenReturn(
            List.of(new AnimalDTO(1, "Tundra", "Tiger", "Female", 5, 1))
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
        when(animalService.getAnimal(1)).thenReturn(
            new AnimalDTO(1, "Tundra", "Tiger", "Female", 5, 1)
        );

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
        var updatedAnimal = new AnimalDTO(
            1, "Tundra", "Tiger", "Female", 6, 1
        );

        when(animalService.updateAnimal(eq(1), any(AnimalDTO.class)))
            .thenReturn(updatedAnimal);

        restTestClient.put()
            .uri("/api/v1/animal/{id}", 1)
            .contentType(MediaType.APPLICATION_JSON)
            .body(updatedAnimal)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
            .expectBody()
                .jsonPath("$.age").isEqualTo(6)
                .jsonPath("$.id").isEqualTo(1);
    }

}
