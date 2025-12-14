package com.example.ds.controllers.enclosures;

import com.example.ds.enumerations.Biome;
import com.example.ds.metrics.EnclosureMetrics;
import com.example.ds.models.dtos.enclosures.EnclosureDTOV1;
import com.example.ds.models.entities.Enclosure;
import com.example.ds.services.enclosures.EnclosureService;
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
@WebMvcTest(EnclosureControllerV1.class)
class EnclosureControllerV1Test {

    @MockitoBean
    EnclosureMetrics enclosureMetrics;

    @MockitoBean
    EnclosureService enclosureService;

    @Autowired
    MockMvc mockMvc;

    RestTestClient restTestClient;

    @BeforeEach
    void setUp() {
        restTestClient = RestTestClient.bindTo(mockMvc).build();
    }

    @Test
    void test_create_and_delete_enclosure() {
        when(enclosureService.createEnclosure(any()))
            .thenReturn(new EnclosureDTOV1(1, Biome.JUNGLE, 100.00, 100.00));

        restTestClient.post()
            .uri("/api/v1/enclosure/")
            .contentType(MediaType.APPLICATION_JSON)
            .body(new EnclosureDTOV1(1, Biome.JUNGLE, 100.00, 100.00))
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
            .expectBody()
                .jsonPath("$.biome").isEqualTo(Biome.JUNGLE.toString())
                .jsonPath("$.length").isEqualTo(100.00)
                .jsonPath("$.width").isEqualTo(100.00);

        restTestClient.delete()
            .uri("/api/v1/enclosure/1")
            .exchange()
            .expectStatus().isOk();


    }

    @Test
    void test_get_all_enclosures() {
        when(enclosureService.getAllEnclosures()).thenReturn(
            List.of(new EnclosureDTOV1(1, Biome.JUNGLE, 100.00, 100.00))
        );

        var enclosures = restTestClient.get()
            .uri("/api/v1/enclosure/")
            .exchange()
            .expectStatus().isOk()
            .expectBody(new ParameterizedTypeReference<List<Enclosure>>() {})
            .returnResult()
            .getResponseBody();

        assertNotNull(enclosures);
        assertEquals(1, enclosures.size());
        assertEquals(Biome.JUNGLE, enclosures.getFirst().getBiome());
    }

    @Test
    void test_get_enclosure() {
        when(enclosureService.getEnclosure(1)).thenReturn(
            new EnclosureDTOV1(1, Biome.JUNGLE, 100.00, 100.00)
        );

        restTestClient.get()
            .uri("/api/v1/enclosure/{id}", 1)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
                .jsonPath("$.biome").isEqualTo(Biome.JUNGLE.toString())
                .jsonPath("$.length").isEqualTo(100.00)
                .jsonPath("$.width").isEqualTo(100.00);

    }

    @Test
    void test_update_enclosure() {
        var updatedEnclosure = new EnclosureDTOV1(1, Biome.JUNGLE, 150.00, 150.00);

        when(enclosureService.updateEnclosure(eq(1), any(EnclosureDTOV1.class)))
            .thenReturn(updatedEnclosure);

        restTestClient.put()
            .uri("/api/v1/enclosure/{id}", 1)
            .contentType(MediaType.APPLICATION_JSON)
            .body(updatedEnclosure)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
            .expectBody()
                .jsonPath("$.biome").isEqualTo(Biome.JUNGLE.toString())
                .jsonPath("$.length").isEqualTo(150.00)
                .jsonPath("$.width").isEqualTo(150.00);
    }

}
