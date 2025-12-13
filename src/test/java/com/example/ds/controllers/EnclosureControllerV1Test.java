package com.example.ds.controllers;

import com.example.ds.models.dtos.EnclosureDTO;
import com.example.ds.models.entities.Enclosure;
import com.example.ds.services.EnclosureService;
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
@WebMvcTest(EnclosureControllerV1.class)
class EnclosureControllerV1Test {

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
    void test_get_all_enclosures_webservice_v1() {
        when(enclosureService.getAllEnclosures()).thenReturn(
            List.of(new EnclosureDTO(1, "Jungle", 100.00, 100.00))
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
        assertEquals("Jungle", enclosures.getFirst().getHabitat());
    }

}
