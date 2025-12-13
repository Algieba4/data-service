package com.example.ds.idls;

import com.example.ds.models.entities.Animal;
import com.example.ds.repositories.AnimalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class AnimalDataLoader {

    private final AnimalRepository animalRepository;
    private final JsonMapper jsonMapper;
    private final ResourceLoader resourceLoader;

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        log.debug("Deleting Existing Animal Data...");
        animalRepository.deleteAll();
        log.debug("Existing Animal Data Deleted");

        log.debug("Loading Animal Data...");

        try {
            Resource resource = resourceLoader.getResource("classpath:/data/animals.json");

            if(!resource.exists()) {
                log.error("Animal data file not found: {}", resource.getFilename());
                return;
            }

            // Using TypeReference here because we're reading in a list of Animals.
            var animals = jsonMapper.readValue(resource.getInputStream(), new TypeReference<List<Animal>>() {});
            animalRepository.saveAll(animals);

        } catch (JacksonException jacksonException) {
            log.error("Error loading Animal Data: {}", jacksonException.getMessage(),  jacksonException);
        } catch (Exception exception) {
            log.error("Uncaught exception: {}", exception.getMessage(), exception);
        }

        log.debug("Finished loading Animal Data");
    }

}
