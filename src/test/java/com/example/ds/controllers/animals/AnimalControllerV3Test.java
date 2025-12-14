package com.example.ds.controllers.animals;

import com.example.ds.enumerations.AnimalStatus;
import com.example.ds.models.dtos.animals.AnimalDTOV3;
import com.example.ds.models.entities.Animal;
import com.example.ds.services.animals.AnimalServiceV3;
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
@WebMvcTest(AnimalControllerV3.class)
class AnimalControllerV3Test {

    AnimalDTOV3 animalDTOV3;

    @MockitoBean
    AnimalServiceV3 animalServiceV3;

    @Autowired
    MockMvc mockMvc;

    RestTestClient restTestClient;

    @BeforeEach
    void setUp() {
        animalDTOV3 = new AnimalDTOV3(
            1, "Tundra", "Female", "Tiger", AnimalStatus.HEALTHY, 5, 1
        );
        restTestClient = RestTestClient.bindTo(mockMvc).build();
    }

    @Test
    void test_create_and_delete_animal() {
        when(animalServiceV3.createAnimal(any()))
            .thenReturn(animalDTOV3);

        restTestClient.post()
            .uri("/api/v3/animal/")
            .contentType(MediaType.APPLICATION_JSON)
            .body(new AnimalDTOV3(null, "Tundra", "Female",  "Tiger",  AnimalStatus.HEALTHY, 5, 1))
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
            .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("Tundra")
                .jsonPath("$.species").isEqualTo("Tiger")
                .jsonPath("$.sex").isEqualTo("Female")
                .jsonPath("$.status").isEqualTo(AnimalStatus.HEALTHY.toString())
                .jsonPath("$.age").isEqualTo(5)
                .jsonPath("$.enclosure").isEqualTo(1);

        restTestClient.delete()
            .uri("/api/v3/animal/1")
            .exchange()
            .expectStatus().isOk();

    }

    @Test
    void test_get_all_animals() {
        when(animalServiceV3.getAllAnimals()).thenReturn(
            List.of(animalDTOV3)
        );

        var animals = restTestClient.get()
            .uri("/api/v3/animal/")
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
        when(animalServiceV3.getAnimal(1)).thenReturn(animalDTOV3);

        restTestClient.get()
            .uri("/api/v3/animal/{id}", 1)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("Tundra")
                .jsonPath("$.species").isEqualTo("Tiger")
                .jsonPath("$.sex").isEqualTo("Female")
                .jsonPath("$.status").isEqualTo(AnimalStatus.HEALTHY.toString())
                .jsonPath("$.age").isEqualTo(5)
                .jsonPath("$.enclosure").isEqualTo(1);
    }

    @Test
    void test_update_animal() {
        var updatedAnimal = new AnimalDTOV3(
            1, "Tundra", "Female", "Tiger", AnimalStatus.HEALTHY, 6, 1
        );

        when(animalServiceV3.updateAnimal(eq(1), any(AnimalDTOV3.class)))
            .thenReturn(updatedAnimal);

        restTestClient.put()
            .uri("/api/v3/animal/{id}", 1)
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
                .jsonPath("$.status").isEqualTo(AnimalStatus.HEALTHY.toString())
                .jsonPath("$.age").isEqualTo(6)
                .jsonPath("$.enclosure").isEqualTo(1);
    }

}
