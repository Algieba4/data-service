package com.example.ds.controllers;

import com.example.ds.models.dtos.AnimalDTO;
import com.example.ds.models.entities.Animal;
import com.example.ds.services.AnimalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    void test_get_all_animals_webservice_v1() {
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

}
