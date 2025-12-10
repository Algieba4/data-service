package com.example.ds.idls;

import com.example.ds.models.entities.Animal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

@Component
@Slf4j
public class AnimalDataLoader implements CommandLineRunner {

    private final JsonMapper jsonMapper;
    private final ResourceLoader resourceLoader;

    public AnimalDataLoader(JsonMapper jsonMapper, ResourceLoader resourceLoader) {
        this.jsonMapper = jsonMapper;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void run(String... args) {
        log.debug("Loading Animal Data...");

        try {
            Resource resource = resourceLoader.getResource("classpath:/data/animals.json");

            if(!resource.exists()) {
                log.error("Animal data file not found: {}", resource.getFilename());
                return;
            }

            // Using TypeReference here because we're reading in a list of Animals.
            jsonMapper.readValue(resource.getInputStream(), new TypeReference<Animal>() {});

        } catch (JacksonException jacksonException) {
            log.error("Error loading Animal Data: {}", jacksonException.getMessage(),  jacksonException);
        } catch (Exception exception) {
            log.error("Uncaught exception: {}", exception.getMessage(), exception);
        }

        log.debug("Finished loading Animal Data...");
    }

}
